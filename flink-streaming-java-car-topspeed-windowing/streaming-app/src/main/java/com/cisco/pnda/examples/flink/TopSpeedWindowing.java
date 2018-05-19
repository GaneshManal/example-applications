package com.cisco.pnda.examples.flink;

import java.util.Properties;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.delta.DeltaFunction;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.evictors.TimeEvictor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.DeltaTrigger;
import java.util.concurrent.TimeUnit;


import com.cisco.pnda.examples.avro.AvroDeserializationSchema;
import com.cisco.pnda.examples.avro.AvroSerializationSchema;
import com.cisco.pnda.examples.util.Car;


/**
 * An example of grouped stream windowing where different eviction and trigger
 * policies can be used. A source fetches events from cars every 100 millisecond
 * containing their id, their current speed (km/h), overall elapsed distance (m)
 * and a time stamp. The streaming example triggers the top speed of each car
 * every x meters elapsed for the last y seconds.
 */
@SuppressWarnings({ "deprecation", "deprecation" })
public class TopSpeedWindowing {

	static String topic = "test";
	static String kafkaPort = "localhost:9092";
	static String zkPort = "localhost:2181";

	static SerializationSchema<Car> ser = new AvroSerializationSchema<Car>(Car.class);
	static DeserializationSchema<Car> deser = new AvroDeserializationSchema<Car>(Car.class);


	public static void main(String[] args) throws Exception {		
		
		final ParameterTool params = ParameterTool.fromArgs(args);

        if (params.has("topic")) {
        	topic = params.get("topic");
        }

        if (params.has("bootstrap-server")) {
        	zkPort = params.get("bootstrap-server");
        }

        if (params.has("broker-list")) {
        	kafkaPort = params.get("broker-list");
        }

        
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", kafkaPort);
		properties.setProperty("group.id", topic);
		properties.setProperty("zookeeper.connect", zkPort);
		properties.setProperty("batch.size", "0");

		/*
		Car car = new Car();
		car.setId(42);
		car.setSpeed((double) 60.5);
		car.setDistance((double) 100);
		TypeInformation<Car> typeInfo = TypeExtractor.getForClass(Car.class);
		DataStream<Car> stream = env.fromCollection(Arrays.asList(car), typeInfo);
		stream.addSink(new FlinkKafkaProducer010<Car>(topic, ser, properties));
		*/

		DataStreamSource<Car> kafkaData = env
				.addSource(new FlinkKafkaConsumer010<Car>(topic, deser, properties));
		// kafkaData.print();

		DataStream<Tuple4<Integer, Double, Double, Long>> carData = kafkaData.map(new ParseCarData());
				
		int evictionSec = 30;
		double triggerMeters = 200;
        if (params.has("trigger-meter")) {
        	triggerMeters = Double.valueOf(params.get("trigger-meter"));
        }
        if (params.has("eviction-window")) {
        	evictionSec = Integer.valueOf(params.get("eviction-window"));
        }

		DataStream<Tuple4<Integer, Double, Double, Long>> topSpeeds = carData
				.assignTimestampsAndWatermarks(new CarTimestamp())
				.keyBy(0)
				.window(GlobalWindows.create())
				//.countWindow(10)
				//.countWindowAll(50)
				.evictor(TimeEvictor.of(Time.of(evictionSec, TimeUnit.SECONDS)))
				.trigger(DeltaTrigger.of(triggerMeters,
						new DeltaFunction<Tuple4<Integer, Double, Double, Long>>() {
							private static final long serialVersionUID = 1L;

							@Override
							public double getDelta(
									Tuple4<Integer, Double, Double, Long> oldDataPoint,
									Tuple4<Integer, Double, Double, Long> newDataPoint) {
								return newDataPoint.f2 - oldDataPoint.f2;
							}
						}, carData.getType().createSerializer(env.getConfig())))
				.maxBy(1);

		if (params.has("output")) {
			topSpeeds.writeAsText(params.get("output"));
			topSpeeds.print();
		} else {
			System.out.println("Printing result to stdout. Use --output to specify output path.");
			topSpeeds.print();
		}
		env.execute("CarTopSpeedWindowingExample");
	}
	
	private static class CarTimestamp extends AscendingTimestampExtractor<Tuple4<Integer, Double, Double, Long>> {
		private static final long serialVersionUID = 1L;

		@Override
		public long extractAscendingTimestamp(Tuple4<Integer, Double, Double, Long> element) {
			return element.f3;
		}
	}
	
	private static class ParseCarData extends RichMapFunction<Car, Tuple4<Integer, Double, Double, Long>> {
		private static final long serialVersionUID = 1L;

		@Override
		public Tuple4<Integer, Double, Double, Long> map(Car car) {
			Integer carId = Integer.valueOf(car.id);
			Double speed = Double.valueOf(car.speed);
			Double distance = Double.valueOf(car.distance);
			Long timestamp = Long.valueOf(car.timestamp);
			return new Tuple4<>(carId, speed, distance, timestamp);
		}
	}
}