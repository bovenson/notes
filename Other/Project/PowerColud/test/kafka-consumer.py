#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: kafka-consumer.py
DATE: 17-8-25 下午5:00
DESC: 
"""
from confluent_kafka import Consumer

# kafka config
conf = {'bootstrap.servers': 'localhost:9092',
        'group.id': 'test',
        'default.topic.config': {'auto.offset.reset': 'smallest'}}

kafka_consumer = Consumer(conf)
kafka_consumer.subscribe(['test'])

running = True

try:
    msg_cnt = 0
    while running:
        msg = kafka_consumer.poll(timeout=1.0)
        if msg is None: continue

        print(msg.value().decode('utf-8'))
        msg_cnt += 1
except Exception as e:
    print(e)
finally:
    kafka_consumer.close()
