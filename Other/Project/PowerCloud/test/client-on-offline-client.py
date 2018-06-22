#!/bin/python3
# coding: utf-8

"""
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-03-20 09:41"

import paho.mqtt.client as mqtt


client = mqtt.Client(client_id="testid")


def on_connect(client_msg, user_data, flags, rc):
    print("Connection returned result: " + str(rc))
    print(client_msg, user_data, flags)
    client.subscribe("$SYS/#")


client.on_connect = on_connect
client.connect("localhost", 1883, 60)
client.publish("hello", "hello")
client.loop_forever()

