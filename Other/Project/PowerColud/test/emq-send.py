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
from time import sleep

import paho.mqtt.publish as publish

auth = {
    'username': 'test',
    'password': 'test',
}

msg = '''{"Data0":"0103800001010100010002000200004366ade24366bab84366a71043c7cbb943c7c8c543c7c336000000000000000000000000000000000000000000000000000000003d2000003d0000003d2000000000000000000000340000004248000041238ccdef2196ed41d3b99aa616d03a4002d70a447799f640a961488b90f267000000022d80","Data1":"0103800001010100010002000200004366adb24366b98d4366a5b643c7cb2343c7c7ad43c7c28b00000000000000000000000000000000000000003c000000000000003cc000003cc000003d4000000000000000000000340000004247ce0041238ccdef2196ed41d3b99aa616d03a4002d70a447799f640a961488b90f2670000000232c8","Data2":"01038000010101000100020002000043665cde4366687a4366552143c7850843c781ae43c77ca70000000000000000000000003c000000000000003c000000000000003cc000003cc000003d4000000000000000000000340000004247ce0041238ccdef2196ed41d3b99aa616d03a4002d70a447799f640a961488b90f26700000002c706"}'''

while True:
    # print(publish.single('/test', 'payload', hostname='202.118.26.129', port=1883, auth=auth))
    print(publish.single('/data', msg, hostname='localhost', port=1883))
    print(publish.single('/alert', "报警消息", hostname='localhost', port=1883))
    sleep(5)

