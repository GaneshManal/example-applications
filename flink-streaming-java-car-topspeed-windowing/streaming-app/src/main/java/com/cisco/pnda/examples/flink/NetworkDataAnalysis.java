/**
  * Name:       TopSpeedWindowing
  * Purpose:    Application entry point to create, configure and start flink streaming job.
  * Author:     PNDA team
  *
  * Created:    24/05/2018
  */

/*
Copyright (c) 2018 Cisco and/or its affiliates.
This software is licensed to you under the terms of the Apache License, Version 2.0 (the "License").
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
The code, technical concepts, and all information contained herein, are the property of Cisco Technology, Inc.
and/or its affiliated entities, under various laws including copyright, international treaties, patent,
and/or contract. Any use of the material herein must be in accordance with the terms of the License.
All rights not expressly granted by the License are reserved.
Unless required by applicable law or agreed to separately in writing, software distributed under the
License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
express or implied.
*/

package com.cisco.pnda.examples.flink;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple9;
import org.apache.flink.api.java.tuple.Tuple10;
import org.apache.flink.api.java.tuple.Tuple11;
import org.apache.flink.api.java.tuple.Tuple12;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.delta.DeltaFunction;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.evictors.TimeEvictor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.DeltaTrigger;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;

import com.cisco.pnda.examples.avro.AvroDeserializationSchema;
import com.cisco.pnda.examples.avro.AvroSerializationSchema;
import com.cisco.pnda.examples.util.NetworkInterface;


/**
 * An example of grouped stream windowing where different eviction and trigger
 * policies can be used. A source fetches events from cars every 100 millisecond
 * containing their id, their current speed (km/h), overall elapsed distance (m)
 * and a time stamp. The streaming example triggers the top speed of each car
 * every x meters elapsed for the last y seconds.
 */
@SuppressWarnings({ "deprecation", "deprecation" })
public class NetworkDataAnalysis {

	static String topic = "avro.flink.streaming";
	static String kafkaPort = "localhost:9092";
	static String zkPort = "localhost:2181";
	private static String TRIGGER_RECEIVED="RX";
	private static String TRIGGER_TRANSMITED="TX";

	static SerializationSchema<NetworkInterface> ser = new AvroSerializationSchema<NetworkInterface>(NetworkInterface.class);
	static DeserializationSchema<NetworkInterface> deser = new AvroDeserializationSchema<NetworkInterface>(NetworkInterface.class);


	public static void main(String[] args) throws Exception {		
		
		final ParameterTool params = ParameterTool.fromArgs(args);

		// Read input parameters and configure if available.
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

		// Data-Source
		DataStreamSource<NetworkInterface> kafkaData = env
				.addSource(new FlinkKafkaConsumer010<NetworkInterface>(topic, deser, properties));

		DataStream<Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>> networkData = kafkaData.map(new ParseNetworkData());
		// networkData.print();

		@SuppressWarnings("serial")
		DataStream<Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>> byteRateNetworkData = networkData
				.keyBy(0, 1)
				.reduce(new ReduceFunction<Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>>(){
					@Override
					public Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double> reduce(
							Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double> value1,
							Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double> value2)
									throws Exception {
						
								value2.f9 = (double)(value2.f2 - value1.f2)/((value2.f8-value1.f8)/1000.00);
								value2.f10 = (double)(value2.f5 - value1.f5)/((value2.f8-value1.f8)/1000.00);
						return new Tuple11<>(value2.f0, value2.f1, value2.f2, value2.f3, value2.f4, value2.f5, value2.f6, value2.f7, value2.f8, value2.f9, value2.f10); 
					}
				});
		byteRateNetworkData.print();	

		int evictionSec = 10;
		double triggerBytes = 1024;
        if (params.has("trigger-meter")) {
        	triggerBytes = Double.valueOf(params.get("trigger-meter"));
        }
        if (params.has("eviction-window")) {
        	evictionSec = Integer.valueOf(params.get("eviction-window"));
        }

        // Transformation-1
		@SuppressWarnings("serial")
		DataStream<Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>> topUplinks = byteRateNetworkData
				.assignTimestampsAndWatermarks(new InterfaceTimestamp())
				.keyBy(0,1)
				.window(GlobalWindows.create())
				//.countWindow(10)
				//.countWindowAll(50)
				.evictor(TimeEvictor.of(Time.of(evictionSec, TimeUnit.SECONDS)))
				.trigger(DeltaTrigger.of(triggerBytes,
						new DeltaFunction<Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>>() {
							private static final long serialVersionUID = 1L;

							@Override
							public double getDelta(
									Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double> oldDataPoint,
									Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double> newDataPoint) {
								return newDataPoint.f2 - oldDataPoint.f2;
							}
						}, networkData.getType().createSerializer(env.getConfig())))
				.maxBy(9);

		topUplinks.print();
		DataStream<Tuple12<String, String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>> RxTriggerData = topUplinks.map(new ParseReceivedNetworkData(TRIGGER_RECEIVED));

		// Transformation-2
		DataStream<Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>> topDownlinks = networkData
				.assignTimestampsAndWatermarks(new InterfaceTimestamp())
				.keyBy(0,1)
				.window(GlobalWindows.create())
				//.countWindow(10)
				//.countWindowAll(50)
				.evictor(TimeEvictor.of(Time.of(evictionSec, TimeUnit.SECONDS)))
				.trigger(DeltaTrigger.of(triggerBytes,
						new DeltaFunction<Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>>() {
							private static final long serialVersionUID = 1L;

							@Override
							public double getDelta(
									Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double> oldDataPoint,
									Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double> newDataPoint) {
								return newDataPoint.f5 - oldDataPoint.f5;
							}
						}, networkData.getType().createSerializer(env.getConfig())))
				.maxBy(10);
		DataStream<Tuple12<String, String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>> TxTriggerData = topDownlinks.map(new ParseReceivedNetworkData(TRIGGER_TRANSMITED));
		
		// Data Sink
		if (params.has("output")) {
			RxTriggerData.writeAsText(params.get("output"));
			RxTriggerData.print();

			TxTriggerData.writeAsText(params.get("output"));
			TxTriggerData.print();

		} else {
			System.out.println("Printing result to stdout. Use --output to specify output path.");
			RxTriggerData.print();
			TxTriggerData.print();
		}
		env.execute("NetworkDataAnalysisWindowingExample");
	}

	private static class InterfaceTimestamp extends AscendingTimestampExtractor<Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>> {
		private static final long serialVersionUID = 1L;

		@Override
		public long extractAscendingTimestamp(Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double> element) {
			return element.f8;
		}
	}

	private static class ParseNetworkData extends RichMapFunction<NetworkInterface, Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>> {
		private static final long serialVersionUID = 1L;

		@Override
		public Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double> map(NetworkInterface eth) {
			String hostname = String.valueOf(eth.hostname);
			String nw_interface = String.valueOf(eth.nw_interface);
			Long rx_bytes = Long.valueOf(eth.rx_bytes);
			Integer rx_dropped = Integer.valueOf(eth.rx_dropped);
			Integer rx_errors = Integer.valueOf(eth.rx_errors);
			Long tx_bytes = Long.valueOf(eth.tx_bytes);
			Integer tx_dropped = Integer.valueOf(eth.tx_dropped);
			Integer tx_errors = Integer.valueOf(eth.tx_errors);
			Long timestamp = Long.valueOf(eth.timestamp);
			Double rx_byte_rate = Double.valueOf(0);
			Double tx_byte_rate = Double.valueOf(0);
			return new Tuple11<>(hostname, nw_interface, rx_bytes, rx_dropped, rx_errors, tx_bytes, tx_dropped, tx_errors, timestamp, rx_byte_rate, tx_byte_rate);
		}
	}
	
	private static class ParseReceivedNetworkData extends RichMapFunction<Tuple11<String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>, Tuple12<String, String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double>> {
		private static final long serialVersionUID = 1L;

		private String triggerType;
		public ParseReceivedNetworkData(String triggerType) {
			this.triggerType = triggerType;
		}
		@Override
		public Tuple12<String, String, String, Long, Integer, Integer, Long, Integer, Integer, Long, Double, Double> map(Tuple11 abc) {
			String trigger = null;
			if(TRIGGER_RECEIVED.equalsIgnoreCase(this.triggerType)) {
				 trigger = "RX-Bytes-Trigger";				
			} else {
				trigger = "TX-Bytes-Trigger";								
			}
			String hostname = String.valueOf(abc.f0);
			String nw_interface = String.valueOf(abc.f1);
			Long rx_bytes = java.lang.Long.valueOf((Long)abc.f2);
			Integer rx_dropped = Integer.valueOf((Integer)abc.f3);
			Integer rx_errors = Integer.valueOf((Integer)abc.f4);
			Long tx_bytes = Long.valueOf((Long) abc.f5);
			Integer tx_dropped = Integer.valueOf((Integer) abc.f6);
			Integer tx_errors = Integer.valueOf((Integer) abc.f7);
			Long timestamp = Long.valueOf((Long) abc.f8);
			Double rx_byte_rate = Double.valueOf((Double)abc.f9);
			Double tx_byte_rate = Double.valueOf((Double)abc.f10);
			return new Tuple12<>(trigger, hostname, nw_interface, rx_bytes, rx_dropped, rx_errors, tx_bytes, tx_dropped, tx_errors, timestamp, rx_byte_rate, tx_byte_rate);
		}
	}
}