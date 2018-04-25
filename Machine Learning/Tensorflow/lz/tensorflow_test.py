#!/bin/python3
# coding: utf-8

"""
tensorflow 安装测试
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-04-24 20:25"


import tensorflow as tf
hello = tf.constant('Hello, TensorFlow!')
sess = tf.Session()
print(sess.run(hello))
