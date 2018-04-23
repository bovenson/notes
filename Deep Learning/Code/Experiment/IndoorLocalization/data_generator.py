#!/bin/python3
# coding: utf-8

"""
数据生成
"""
from math import sqrt, log
import numpy as np
import matplotlib.pyplot as plt
from PIL import Image
import pandas as pd
from data_rw_utils import read_from_excel
from parameters import *

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-04-20 10:50"


class Point:
    """点"""

    def __init__(self, x, y, h=1.2):
        self.x = x
        self.y = y
        self.h = h

    def distance(self, p):
        """计算两点间欧氏距离"""
        if isinstance(p, Point):
            return sqrt(pow(p.x - self.x, 2) + pow(p.y - self.y, 2) + pow(p.h - self.h, 2))
        else:
            raise TypeError('参数错误')


class DataCollection:
    def __init__(self):
        self.labels = []
        self.images = []

    def append(self, label: str, image: Image):
        self.labels.append(label)
        self.images.append(image)


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


def get_img_width(v: float) -> int:
    return max(0, min(P_img_height-1, int(abs(v * P_img_scale))))


def get_mid(a: int, b: int) -> int:
    if a == 0 or b == 0:
        return a + b
    else:
        return (a + b) // 2


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

        # print(pos)
        for j in range(1, ap_vals_length):  # 线性插值
            start = pos[j-1]
            end = pos[j]

            y = get_img_width(ap_cur_vals[j-1])
            img[y][start] = get_mid(ap_grey_value, img[y][start])

            for k in range(start+1, end):
                y3 = get_y3(start, ap_cur_vals[j-1], end, ap_cur_vals[j], k)
                y = get_img_width(y3)
                img[y][k] = get_mid(ap_grey_value, img[y][k])

        y = get_img_width(ap_cur_vals[ap_vals_length-1])
        img[y][width-1] = get_mid(ap_grey_value, img[y][width-1])

    img = img[::-1]
    # print(img)
    img = Image.fromarray(np.uint8(img), 'L')
    # img.show()
    return img


def read_data(file_name, ap_sheet_name: str, rf_sheet_name: str):
    ap_data = pd.DataFrame(read_from_excel(file_name, ap_sheet_name), columns=['label', 'x', 'y', 'h'])
    rf_data = pd.DataFrame(read_from_excel(file_name, rf_sheet_name), columns=['label', 'x', 'y', 'h'])
    return ap_data, rf_data


def get_gray_values_list(count: int) -> list:
    """
    获取给定数量等差递增灰度值列表
    :param count:   灰度值数量
    :return:
    """
    res = []
    for i in range(1, count+1):
        res.append(255 * i // count)
    return res


def generate_data_set(ap_positions: pd.DataFrame, rf_positions: pd.DataFrame,
                      sample_single_num: int, sample_single_count: int, display=False) -> DataCollection:
    """
    生成数据集
    :param display:             是否显示生成的图片
    :param ap_positions:        AP 位置列表
    :param rf_positions:        RF 位置列表
    :param sample_single_num:   单个(AP, RF)生成数据量
    :param sample_single_count: 每条训练数据包含的参考点数
    :return:                    数据集
    """
    dc = DataCollection()   # 生成的数据集
    ap_pos = []             # AP 位置
    rf_pos = []             # 参考点位置
    rss_data_set = []       # 二维数组; 第一维对应参考点索引; 第二维为AP索引
    labels = []             # 标签; 对应参考点索引

    for i in rf_positions.index:    # 遍历参考点
        label = rf_positions.loc[i].at['label']
        x, y, h = rf_positions.loc[i].values[1:]
        labels.append(label)
        rf_pos.append(Point(x, y, h))

    for i in ap_positions.index:    # 遍历 AP
        x, y, h = ap_positions.loc[i].values[1:]
        ap_pos.append(Point(x, y, h))

    for i in range(len(rf_pos)):        # 生成每个参考点对应AP的RSS数据; 第i个参考点
        ith_rss_data = []
        for j in range(len(ap_pos)):    # 第j个AP
            # 生成 RSS 数据
            t_rss_data = generate_rss_data(ap_pos[j], rf_pos[i], sample_single_count * sample_single_num, P_sigma)
            ith_rss_data.append(t_rss_data)
        rss_data_set.append(ith_rss_data)

    # 生成记录
    ap_grey_values = get_gray_values_list(len(ap_pos))
    for i in range(sample_single_num):      # 循环处理生成每条记录
        start = i * sample_single_count
        end = (i + 1) * sample_single_count

        for j in range(len(rf_pos)):        # 循环每个参考点; 每个参考点生成sample_single_num条记录
            label = labels[j]
            val_list = []                   # 索引对应AP
            for k in range(len(ap_pos)):    # 循环每个AP
                val_list.append(rss_data_set[j][k][start:end])  # 第 j 个参考点与第 k 个 AP 生成的数据

            img = generate_image(P_img_width, P_img_height, ap_grey_values, val_list)
            if display:
                img.show()
                input('pause... press enter to continue.')
            dc.append(label, img)
            # print('*', end='')
    return dc


def generate_data():
    # 生成第一组
    ap_data, rf_data = read_data('data.xlsx', 'ap_positions_1', 'rf_positions_1')
    data_set_1 = generate_data_set(ap_data, rf_data, P_sample_single_num, P_sample_single_item_count, display=True)
    print(data_set_1.labels)


if __name__ == '__main__':
    generate_data()
    # pos_ap_1 = Point(12, 0, 4)
    # pos_rf_1 = Point(2.5, 2, 1.2)
    # data = generate_rss_data(pos_ap_1, pos_rf_1, P_sample_single_num, P_sigma)
    # plt.hist(data, 30, normed=True)
    # # plt.show()
    # generate_image(90, 90, [50, 100, 200], [[2, 5, 2, 5], [3, 10, 1, 9], [1, 5, 9, 0]])
    # print(read_data('data.xlsx', 'ap_positions_1', 'rf_positions_1'))
