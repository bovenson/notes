#!/bin/bash
# coding: utf-8

"""
希尔排序
"""


def ShellSort(l):
    """希尔排序
    :param l:   带排序序列
    :return:    排序后序列
    """
    
    n = len(l)
    h = len(l)  # 步长
    while h > 1:
        h = h / 3 if h / 3 >= 1 else 1  # 更新步长
        gap = n / h
        # 分表排序
        for i in range(gap):

            for j in range(gap, n, gap):


