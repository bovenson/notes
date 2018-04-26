#!/bin/python3
# coding: utf-8

"""
tensorflow 安装测试
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-04-24 20:25"


import tensorflow as tf
from tensorflow.examples.tutorials.mnist import input_data
mnist = input_data.read_data_sets("dataset/", one_hot=False)
print(len(mnist.train.images[0]))
print(mnist.train.images)
# print(mnist.train.shape)
res = mnist.train.next_batch(10)
print(mnist.train.next_batch(3))
print()
print()
print()
print()
print()
