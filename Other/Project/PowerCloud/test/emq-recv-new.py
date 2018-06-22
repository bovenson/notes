#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: emq-recv-new.py
DATE: 17-12-4 下午2:54
DESC: 
"""
import paho.mqtt.subscribe as subscribe

auth = {
    'username': 'test',
    'password': 'test',
}


def on_msg_recv(client, userdata, message):
    print("%s %s" % (message.topic, message.payload))


# subscribe.callback(on_msg_recv, '/ammeter', hostname='202.118.26.129', port=1883, auth=auth)
subscribe.callback(on_msg_recv, 'test', hostname='202.118.26.129', port=1883, auth=auth)
