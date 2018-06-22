#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: emq-send.py
DATE: 17-8-25 下午4:31
DESC:
"""
# from time import sleep
#
# import paho.mqtt.client as mqtt
#
# client = mqtt.Client()
#
# client.connect("localhost", 1883, 60)
#
# for i in range(10):
#     print(client.publish('/test', payload='Hello World %d' % i))
#     sleep(1)
import random
from time import sleep

import paho.mqtt.publish as publish

auth = {
    'username': 'test1',
    'password': 'test1',
}

while True:
    try:
        print(publish.single('test', '''{"msg":"电流预警","electricitySubstationID":7,"worktaskID":311,"status":1}''', hostname='202.118.26.48', port=1883, auth=auth,
                             client_id="elemeter" + str(random.random())))
        sleep(5)
        # break
    except Exception as e:
        print(e)

