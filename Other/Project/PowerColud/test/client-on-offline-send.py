#!/bin/python3
# coding: utf-8

"""
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-03-20 09:29"

import paho.mqtt.publish as publish


publish.single("/unique", "this payload msg", hostname="localhost")
