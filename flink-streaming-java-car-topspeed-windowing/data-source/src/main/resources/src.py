"""
Name:       cts-src.py
Purpose:    Test script to push data into kafka for consumption by the example car top speed windowing using flink streaming.
            Not intended for any kind of serious purpose.
            usage: cts-src.py kafka_broker number_of_records car_count
             e.g.: cts-src.py 192.168.12.24:9092 200 1000
Author:     PNDA team
Created:    21/05/2018
Copyright (c) 2018 Cisco and/or its affiliates.
This software is licensed to you under the terms of the Apache License, Version 2.0 (the "License").
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
The code, technical concepts, and all information contained herein, are the property of Cisco Technology, Inc.
and/or its affiliated entities, under various laws including copyright, international treaties, patent, and/or contract.
Any use of the material herein must be in accordance with the terms of the License. All rights not expressly granted by the License are reserved.
Unless required by applicable law or agreed to separately in writing, software distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
"""
import io
import os
import sys
import random
import time
import avro.schema
import avro.io
from kafka import KafkaProducer

# Path to user.avsc avro schema
HERE = os.path.abspath(os.path.dirname(__file__))
SCHEMA_PATH = HERE + "/dataplatform-raw.avsc"

# Kafka topic
TOPIC = "avro.flink.streaming"

# lambda functions
current_milli_time = lambda: int(round(time.time() * 1000))


def run(brokers, record_count, car_count):
    """
    Run the test
    """
    schema = avro.schema.parse(open(SCHEMA_PATH).read())
    producer = KafkaProducer(bootstrap_servers=[brokers])
    writer = avro.io.DatumWriter(schema)
    bytes_writer = io.BytesIO()
    encoder = avro.io.BinaryEncoder(bytes_writer)
    extra_bytes = bytes('')

    speed = [50 for _ in range(car_count)]
    distance = [0 for _ in range(car_count)]

    for i in range(0, record_count):
        gen_data = {}

        time_sec = current_milli_time()
        car_id = random.randint(1, 1)
        gen_data['car_id'] = car_id

        all_speeds = [i for i in range(-5, 6, 5)]
        speed[car_id-1] += random.choice(all_speeds)
        temp_speed = speed[car_id-1]
        gen_data['speed'] = temp_speed if temp_speed > 0 else 0

        distance[car_id-1] += speed[car_id-1] / 3.6
        gen_data['distance'] = distance[car_id-1]

        # gen_data = "+"*100
        print ','.join([str(car_id), str(temp_speed), str(distance[car_id-1]), str(time_sec)])
        writer.write({"id": car_id,
                      "speed": temp_speed,
                      "distance": distance[car_id-1],
                      "timestamp":  time_sec
                      }, encoder)
        raw_bytes = bytes_writer.getvalue()

        # reset buffer to start index
        bytes_writer.seek(0)
        print(extra_bytes + raw_bytes)
        producer.send(TOPIC, extra_bytes + raw_bytes)

        # Sleep for 1 second before triggering new data
        time.sleep(1)


if __name__ == '__main__':

    broker_list = None
    if len(sys.argv) != 4:
        print "Usage: cts-src.py kafka_broker number_of_records car_count"
        print "Ex - cts-src.py 192.168.12.24:9092 200 1000"
        sys.exit(1)

    broker_list, total_records, total_cars = sys.argv[1], sys.argv[2], sys.argv[3]
    run(broker_list, total_records, total_cars)
