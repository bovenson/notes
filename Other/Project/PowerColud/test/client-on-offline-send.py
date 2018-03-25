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
    publish.single("/gateway/domain", "this payload msg" + str(i), hostname="localhost")
    i += 1
    sleep(5)
