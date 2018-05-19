"""
Name:       src.py
Purpose:    Test script to push data into kafka for consumption by the example car top speed windowing using flink streaming.
            Not intended for any kind of serious purpose.
            usage: python src.py kafka_broker number_of_records car_count
             e.g.: python src.py 192.168.12.24:9092 200 3
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

# Path to user AVRO schema file
HERE = os.path.abspath(os.path.dirname(__file__))
SCHEMA_PATH = HERE + "/dataplatform-raw.avsc"

# Kafka topic
TOPIC = "avro.flink.streaming"

# lambda functions
current_milli_time = lambda: int(round(time.time() * 1000))


def run(brokers, record_count, host_count, int_per_host):
    """
    Run the test
    """
    try:
        schema = avro.schema.parse(open(SCHEMA_PATH).read())
        producer = KafkaProducer(bootstrap_servers=[brokers])
        writer = avro.io.DatumWriter(schema)
    except:
        print("Exception: ", sys.exc_info())
        sys.exit(1)

    bytes_writer = io.BytesIO()
    encoder = avro.io.BinaryEncoder(bytes_writer)
    extra_bytes = bytes('')

    all_hosts = dict()
    for x_host in range(1, host_count+1):
        all_hosts['host' + str(x_host)] = dict()
        current_host = all_hosts['host' + str(x_host)]

        # Create Interface
        for x_int in range(int_per_host):
            current_host['eth' + str(x_int)] = {}
            current_int = current_host['eth' + str(x_int)]

            # Add Stats
            current_int['RX-Bytes'], current_int['TX-Bytes'] = 0, 0
            current_int['RX-Dropped'], current_int['TX-Dropped'] = 0, 0
            current_int['RX-Errors'], current_int['TX-Errors'] = 0, 0
            current_int['upload-speed-mb'], current_int['download-speed-mb'] = 50, 100

    # Triggers 10 records per second
    records_per_second = 1.0

    # Network fluctuation Configuration
    network_fluctuation = [i for i in range(-20, 21, 5)]

    # Packet drop and error variation
    packet_drop_error_variation = [0, 0, 0, 0, 0, 1, 2, 0, 1, 1]

    # Generate Records
    start_time = time.time()
    for item in range(0, record_count):
        time_sec = current_milli_time()

        # Random host and interface selection
        host_name = random.choice(all_hosts.keys())
        interface_name = random.choice(all_hosts.get(host_name).keys())

        # Select the interface to generate data
        interface = all_hosts.get(host_name).get(interface_name)

        # Generate random stable/increase/decrease(0,5,10,15,20) in network speed
        interface['upload-speed-mb'] += random.choice(network_fluctuation)
        interface['download-speed-mb'] += random.choice(network_fluctuation)

        if interface['upload-speed-mb'] < 0:
            interface['upload-speed-mb'] = 0
        if interface['download-speed-mb'] < 0:
            interface['download-speed-mb'] = 0

        # Generate received and transmitted bytes
        interface['RX-Bytes'] += long(interface['download-speed-mb'] * records_per_second)
        interface['TX-Bytes'] += long(interface['upload-speed-mb'] * records_per_second)

        # Generate received and transmitted packet drops
        interface['RX-Dropped'] += long(random.choice(packet_drop_error_variation) * records_per_second)
        interface['TX-Dropped'] += long(random.choice(packet_drop_error_variation) * records_per_second)

        # Generate received and transmitted packet error
        interface['RX-Errors'] += long(random.choice(packet_drop_error_variation) * records_per_second)
        interface['TX-Errors'] += long(random.choice(packet_drop_error_variation) * records_per_second)

        # Write data to the kafka topic
        print "host: %s, interface: %s, " \
              "RX-Bytes: %s, TX-Bytes: %s, " \
              "RX-Dropped: %s, TX-Dropped: %s, " \
              "RX-Errors: %s, TX-Errors: %s, " \
              "Timestamp: %s" \
              % (host_name, interface_name,
                 str(interface.get('RX-Bytes')), str(interface.get('TX-Bytes')),
                 str(interface.get('RX-Dropped')), str(interface.get('TX-Dropped')),
                 str(interface.get('RX-Errors')), str(interface.get('TX-Errors')),
                 str(time_sec))

        writer.write({"hostname": host_name,
                      "nw_interface": interface_name,
                      "RX_Bytes": interface.get('RX-Bytes'),
                      "RX_Dropped": interface.get('RX-Dropped'),
                      "RX_Errors": interface.get('RX-Errors'),
                      "TX_Bytes": interface.get('TX-Bytes'),
                      "TX_Dropped": interface.get('TX-Dropped'),
                      "TX_Errors": interface.get('TX-Errors'),
                      "timestamp":  time_sec}, encoder)
        raw_bytes = bytes_writer.getvalue()

        # reset buffer to start index
        bytes_writer.seek(0)
        # print('AVRO Data: ' + extra_bytes + raw_bytes)
        producer.send(TOPIC, extra_bytes + raw_bytes)

        time.sleep(1/records_per_second)

    print("Time Taken for execution : %s SECONDS" % (time.time() - start_time) )


if __name__ == '__main__':

    broker_list = None
    if len(sys.argv) != 5:
        print "Usage: python src.py kafka_broker number_of_records total_hosts interfaces_per_host"
        print "Ex - python src.py 192.168.12.24:9092 100 2 1"
        sys.exit(1)

    broker_list, total_records, total_hosts, interfaces_per_host = sys.argv[1], int(sys.argv[2]), int(sys.argv[3]), int(sys.argv[4])
    run(broker_list, total_records, total_hosts, interfaces_per_host)
