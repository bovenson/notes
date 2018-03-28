#!/bin/python3
# coding: utf-8

"""
"""
from time import sleep

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-03-20 09:29"


import paho.mqtt.publish as publish

i = 0
while True:
    # publish.single("/gateway/1", "this payload msg" + str(i), hostname="localhost")
    publish.single("/a", "this payload msg" + str(i), hostname="202.118.26.129")
    i += 1
    sleep(5)
