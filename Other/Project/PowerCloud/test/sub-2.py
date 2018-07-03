#!/bin/python3
# coding: utf-8

"""
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-03-27 14:59"


import paho.mqtt.subscribe as subscribe

auth = {
    'username': 'test',
    'password': 'test',
}


def on_message_print(client, user_data, message):
    print("%s %s %s" % (message.topic, message.payload, user_data))


# subscribe.callback(on_message_print, "/a", hostname="localhost")
# subscribe.callback(on_message_print, "/systemStatus/#", hostname="202.118.26.74")
# subscribe.callback(on_message_print, "/gateway/#", hostname="localhost")

subscribe.callback(on_message_print, "/gateway/5", hostname="202.118.26.48", auth=auth)

# subscribe.callback(on_message_print, '$SYS/brokers/f7d95bf8a560@172.17.0.2/clients/#', hostname="202.118.26.74")

