#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: github_resnet_mnist_1.py
DATE: 17-7-17 上午9:11
DESC: 
"""


"""
Resnet experiment on the MNIST task.
"""

import math
import numpy as np
import tensorflow as tf
import waterfall as wf

from tensorflow.examples.tutorials.mnist import input_data
mnist = input_data.read_data_sets('mnist_data', one_hot=True)

def batch_eval_accuracy(accuracy_logit, X, y, batch_size):
    accuracies = []
    num_batches = math.ceil(len(mnist.test.images)/batch_size)
    for i in range(num_batches):
        lower_bound = i*batch_size
        upper_bound = (i+1)*batch_size if i < num_batches-1 else len(mnist.test.images)
        feed_dict = {X: mnist.test.images[lower_bound:upper_bound], y: mnist.test.labels[lower_bound:upper_bound], keep_prob: 1.0}
        accuracies.append(accuracy_logit.eval(feed_dict=feed_dict))
    return np.mean(accuracies)

X = tf.placeholder(tf.float32, [None, 784], 'X')
y = tf.placeholder(tf.float32, [None, 10], 'y')
keep_prob = tf.placeholder(tf.float32)

with tf.variable_scope('transform') as scope:
    transform_layer = tf.reshape(X, [-1, 28, 28, 1])
with tf.variable_scope('conv1') as scope:
    conv1 = wf.conv.conv2d(transform_layer, ksize=7, stride=1, nfilters=16, padding='SAME')
    conv1 = wf.norm.batch_norm(conv1)
    conv1 = wf.act.relu(conv1)
    conv1 = wf.reg.dropout(conv1, keep_prob)
    conv1 = wf.conv.conv2d(conv1, ksize=5, stride=2, nfilters=16, padding='SAME')
with tf.variable_scope('res2') as scope:
    res2 = wf.residual.conv_bottleneck_block(conv1, 3, 32, 64)
    res2 = wf.residual.conv_bottleneck_block(res2, 3, 32, 64)
    res2 = wf.residual.conv_bottleneck_block(res2, 3, 32, 64)
    res2 = wf.residual.conv_bottleneck_block(res2, 3, 32, 64)
    res2 = wf.residual.conv_bottleneck_block(res2, 3, 32, 64)
    res2 = wf.residual.conv_bottleneck_block(res2, 3, 32, 64)
with tf.variable_scope('res3') as scope:
    res3 = wf.residual.conv_bottleneck_block(res2, 3, 64, 128)
    res3 = wf.residual.conv_bottleneck_block(res3, 3, 64, 128)
    res3 = wf.residual.conv_bottleneck_block(res3, 3, 64, 128)
    res3 = wf.residual.conv_bottleneck_block(res3, 3, 64, 128)
    res3 = wf.residual.conv_bottleneck_block(res3, 3, 64, 128)
    res3 = wf.residual.conv_bottleneck_block(res3, 3, 64, 128)
with tf.variable_scope('ouput') as scope:
    output = wf.trans.flatten(res3)
    output = wf.dense.linear_transform(output, 1000)
    output = wf.norm.batch_norm(output)
    output = wf.act.relu(output)
    output = wf.reg.dropout(output, keep_prob)
    output = wf.dense.linear_transform(output, 10)

cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(output, y))
optimizer = tf.train.AdamOptimizer(1e-4).minimize(cost)
correct_predictions = tf.equal(tf.argmax(output, 1), tf.argmax(y, 1))
accuracy = tf.reduce_mean(tf.cast(correct_predictions, tf.float32))

init = tf.initialize_all_variables()
with tf.Session() as sess:
    sess.run(init)
    for i in range(20000):
        batch_x, batch_y = mnist.train.next_batch(128)
        sess.run(optimizer, feed_dict={X:batch_x, y: batch_y, keep_prob: 0.5})
        if i%100 == 0:
            loss, acc = sess.run([cost, accuracy], feed_dict={X:batch_x, y: batch_y, keep_prob: 1.0})
            print(i, loss, acc)
    print("test accuracy %g" % batch_eval_accuracy(accuracy, X, y, 200))
