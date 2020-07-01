#!/bin/python3
# coding: utf-8

"""
测试验证
"""
from data_generator import generate_data, IndoorLocationDataSet

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-04-24 20:52"

import time
import numpy as np
import tensorflow as tf

# 加载mnist_inference.py 和 mnist_train.py中定义的常量和函数
import inference
import train

# 每10秒加载一次最新的模型， 并在测试数据上测试最新模型的正确率
EVAL_INTERVAL_SECS = 10


def evaluate(ds: IndoorLocationDataSet):
    with tf.Graph().as_default() as g:
        # 定义输入输出的格式
        x = tf.placeholder(tf.float32, [
            ds.validation.num_examples,  # 第一维表示样例的个数
            inference.IMAGE_SIZE,  # 第二维和第三维表示图片的尺寸
            inference.IMAGE_SIZE,
            inference.NUM_CHANNELS],  # 第四维表示图片的深度，对于RBG格式的图片，深度为5
                           name='x-input')
        y_ = tf.placeholder(tf.float32, [None, inference.OUTPUT_NODE], name='y-input')

        validate_feed = {x: np.reshape(ds.validation.images, (ds.validation.num_examples, inference.IMAGE_SIZE,
                                                              inference.IMAGE_SIZE, inference.NUM_CHANNELS)),
                         y_: ds.validation.labels}
        # 直接通过调用封装好的函数来计算前向传播的结果。
        # 因为测试时不关注正则损失的值，所以这里用于计算正则化损失的函数被设置为None。
        y = inference.inference(x, False, None)

        # 使用前向传播的结果计算正确率。
        # 如果需要对未知的样例进行分类，那么使用tf.argmax(y, 1)就可以得到输入样例的预测类别了。
        correct_prediction = tf.equal(tf.argmax(y, 1), tf.argmax(y_, 1))
        accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))

        # 通过变量重命名的方式来加载模型，这样在前向传播的过程中就不需要调用求滑动平均的函数来获取平局值了。
        # 这样就可以完全共用mnist_inference.py中定义的前向传播过程
        variable_averages = tf.train.ExponentialMovingAverage(train.MOVING_AVERAGE_DECAY)
        variable_to_restore = variable_averages.variables_to_restore()
        saver = tf.train.Saver(variable_to_restore)

        # 每隔EVAL_INTERVAL_SECS秒调用一次计算正确率的过程以检测训练过程中正确率的变化
        while True:
            with tf.Session() as sess:
                # tf.train.get_checkpoint_state函数会通过checkpoint文件自动找到目录中最新模型的文件名
                ckpt = tf.train.get_checkpoint_state(train.MODEL_SAVE_PATH)
                if ckpt and ckpt.model_checkpoint_path:
                    # 加载模型
                    saver.restore(sess, ckpt.model_checkpoint_path)
                    # 通过文件名得到模型保存时迭代的轮数
                    global_step = ckpt.model_checkpoint_path.split('/')[-1].split('-')[-1]
                    accuracy_score = sess.run(accuracy, feed_dict=validate_feed)
                    print("After %s training step(s), validation accuracy = %f" % (global_step, accuracy_score))
                else:
                    print("No checkpoint file found")
                    return
            time.sleep(EVAL_INTERVAL_SECS)


def main(argv=None):
    ds = generate_data()
    evaluate(ds)


if __name__ == '__main__':
    tf.app.run()
