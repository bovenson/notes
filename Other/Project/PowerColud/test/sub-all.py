#!/bin/python3
# coding: utf-8

"""
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-03-27 14:59"


import paho.mqtt.subscribe as subscribe


def on_message_print(client, user_data, message):
    print("%s %s %s" % (message.topic, str(message.payload, encoding='utf-8'), user_data))


# subscribe.callback(on_message_print, "/a", hostname="localhost")
# subscribe.callback(on_message_print, "/Alert/#", hostname="202.118.26.74")
subscribe.callback(on_message_print, "/systemStatus/#", hostname="202.118.26.74")

# subscribe.callback(on_message_print, "/gateway/#", hostname="202.118.26.74")

