#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Fri Jul 14 10:23:53 2017

@author: bovenson
"""
import tensorflow as tf


hello = tf.constant('Hello')
sess = tf.Session()
print(sess.run(hello))
