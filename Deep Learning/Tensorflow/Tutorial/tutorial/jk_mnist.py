#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: jk_mnist.py
DATE: 17-7-19 下午8:21
DESC: 
"""
from tensorflow.examples.tutorials.mnist import input_data
import tensorflow as tf


mnist = input_data.read_data_sets('MNIST_data/', one_hot=True)


x = tf.placeholder(tf.float32, [None, 28 * 28])
W = tf.Variable(tf.zeros([784, 10]))
b = tf.Variable(tf.zeros([10]))

y = tf.nn.softmax(tf.matmul(x, W) + b)

y_ = tf.placeholder('float', [None, 10])
cross_entropy = -tf.reduce_sum(y_ * tf.log(y))

train_step = tf.train.GradientDescentOptimizer(0.01).minimize(cross_entropy)

init = tf.initialize_all_variables()

sess = tf.Session()
sess.run(init)

summary_writer = tf.summary.FileWriter('/home/public/program/tensorboard', sess.graph)

for i in range(1000):
    batch_xs, batch_ys = mnist.train.next_batch(100)
    summary_writer.add_summary(sess.run(train_step, feed_dict={x: batch_xs, y_: batch_ys}), i)

correct_prediction = tf.equal(tf.argmax(y, 1), tf.argmax(y_, 1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, 'float'))

print(sess.run(accuracy, feed_dict={x: mnist.test.images, y_: mnist.test.labels}))