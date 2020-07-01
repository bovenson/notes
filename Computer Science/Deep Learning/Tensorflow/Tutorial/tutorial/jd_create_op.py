#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: jd_create_op.py
DATE: 17-7-21 上午9:23
DESC: 
"""
import tensorflow as tf


matrix1 = tf.constant([[3., 3.]])
matrix2 = tf.constant([[2.], [2.]])

product = tf.matmul(matrix1, matrix2)

sess = tf.Session()

result = sess.run(product)
# print(result)

# state = tf.Variable(0, name='counter')
# one = tf.constant(1)
# new_value = tf.add(state, one)
# update = tf.assign(state, new_value)
#
# init_op = tf.initialize_all_variables()
#
# with tf.Session() as sess:
#     sess.run(init_op)
#     print(sess.run(state))
#
#     for _ in range(3):
#         sess.run(update)
#         print(sess.run(state))

input1 = tf.constant(3.0)
input2 = tf.constant(2.0)
input3 = tf.constant(5.0)
inter_med = tf.add(input2, input3)
add = tf.multiply(input1, input2)

with tf.Session() as sess:
    result = sess.run([add, inter_med])
    print(result)
