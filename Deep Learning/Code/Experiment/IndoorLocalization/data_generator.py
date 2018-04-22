#!/bin/python3
# coding: utf-8

"""
数据生成
"""
from math import sqrt, log
import numpy as np
import matplotlib.pyplot as plt
from PIL import Image
from parameters import *

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-04-20 10:50"


class Point:
    """点"""

    def __init__(self, x, y, h):
        self.x = x
        self.y = y
        self.h = h

    def distance(self, p):
        """计算两点间欧氏距离"""
        if isinstance(p, Point):
            return sqrt(pow(p.x - self.x, 2) + pow(p.y - self.y, 2) + pow(p.h - self.h, 2))
        else:
            raise TypeError('参数错误')


def figure_rss_fix(dist):
    """计算理论场强值"""
    return P_A - 10 * P_q * log(dist, 10)


def generate_rss_data(pos_ap: Point, pos_rf: Point, num: int, sigma: float):
    """
    生成样本点相对AP的样本数据
    :param sigma:   正态分布方差
    :param pos_ap:  AP 位置
    :param pos_rf:  参考点位置
    :param num:     产生数据样本数量
    :return:        样本
    """
    d = pos_ap.distance(pos_rf)     # 参考点至AP的距离
    rss_fix = figure_rss_fix(d)     # 计算理论场强值, 也是正态分布的均值mu
    return np.random.normal(rss_fix, sigma, num)


def get_y3(x1, y1, x2, y2, x3):
    """
    给定两点确定直线, 并返回 x3 对应的 y 值
    :param x1:  点一 X 坐标
    :param y1:  点一 Y 坐标
    :param x2:  点二 X 坐标
    :param y2:  点二 Y 坐标
    :param x3:  点三 X 坐标
    :return:    y3
    """
    return (x3 - x1) / (x2 - x1) * (y2 - y1) + y1


def generate_image(width: int, height: int, ap_gray_values: list, vals: list) -> Image:
    """
    生成灰度图像
    :param width:           图像宽度
    :param height:          图像高度
    :param ap_gray_values:  AP灰度值列表
    :param vals:            每个AP对应的值, 顺序和 ap_grey_values 相同
    :return:                灰度图像
    """
    img = np.zeros((height, width), dtype='int')    # 图像矩阵
    # img = [[0 for i in range(width)] for j in range(height)]    # 图像矩阵
    for i in range(len(ap_gray_values)):            # 遍历每个 AP 的数据
        ap_grey_value = ap_gray_values[i]           # 当前 AP 的灰度值
        ap_cur_vals = vals[i]                       # 当前 AP 的值
        ap_vals_length = len(ap_cur_vals)

        pos = [0 for i in range(ap_vals_length)]

        # 绘制图像
        if ap_vals_length > width:
            raise RuntimeWarning('样本数量多于图像宽度(像素)')

        for j in range(ap_vals_length):     # 循环 AP 灰度值, 找到 x 位置
            desire_percent = j / (ap_vals_length - 1)
            diff = 1.0
            best = 0    # 第j个AP值, 对应的 X 轴坐标
            for k in range(width):
                cur_percent = k / (width - 1)
                if abs(desire_percent - cur_percent) < diff:
                    diff = abs(desire_percent - cur_percent)
                    best = k
            pos[j] = best

        print(pos)
        for j in range(1, ap_vals_length):  # 线性差值
            start = pos[j-1]
            end = pos[j]

            img[int(ap_cur_vals[j-1])][start] = ap_grey_value

            for k in range(start+1, end):
                y3 = get_y3(start, ap_cur_vals[j-1], end, ap_cur_vals[j], k)
                img[int(y3)][k] = ap_grey_value

            img[int(ap_cur_vals[j])][end] = ap_grey_value
    img = img[::-1]
    # print(img)
    img = Image.fromarray(np.uint8(img), 'L')
    img.show()
    return img


if __name__ == '__main__':
    pos_ap_1 = Point(12, 0, 4)
    pos_rf_1 = Point(2.5, 2, 1.2)
    data = generate_rss_data(pos_ap_1, pos_rf_1, P_sample_single_num, P_sigma)
    plt.hist(data, 30, normed=True)
    # plt.show()
    generate_image(90, 90, [50, 100, 200], [[2, 5, 2, 5], [3, 10, 1, 9], [1, 5, 9, 0]])
