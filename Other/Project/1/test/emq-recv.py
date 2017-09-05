#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: emq-test.py
DATE: 17-8-25 下午4:27
DESC: 
"""
import paho.mqtt.client as mqtt
from confluent_kafka import Producer

# kafka config
conf = {'test.servers': 'localhost:9092',
        'group.id': 'test',
        'default.topic.config': {'auto.offset.reset': 'smallest'}}

kafka_producer = Producer({'bootstrap.servers': 'localhost:9092'})


def on_connect(client, userdata, flags, rc):
    print("Connected with result code " + str(rc))
    client.subscribe("/test")


def on_message(client, userdata, msg):
    """消息到达"""
    # print(str(msg.payload))
    # print(type(msg.payload))
    # print(str(msg.payload.decode('utf-8')))
    recv_msg = str(msg.payload.decode('utf-8'))
    kafka_producer.produce('test', recv_msg)
    kafka_producer.flush()
    print('Topic: ', msg.topic + ", Message: " + str(msg.payload))


client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message

client.connect("localhost", 1883, 60)

client.loop_forever()
