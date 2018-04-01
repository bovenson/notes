#!/bin/python3
# coding: utf-8

"""
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-03-27 14:59"


import paho.mqtt.subscribe as subscribe


def on_message_print(client, user_data, message):
    print("%s %s %s" % (message.topic, message.payload, user_data))


# subscribe.callback(on_message_print, "/a", hostname="localhost")
# subscribe.callback(on_message_print, "/systemStatus/#", hostname="202.118.26.129")
# subscribe.callback(on_message_print, "/gateway/#", hostname="localhost")

subscribe.callback(on_message_print, "/systemStatus/1", hostname="202.118.26.129")
