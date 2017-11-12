#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-12 12:00"


# 初始化变量
L = 20  # 最大迭代次数
N = 10  # 初始粒子数量
C1 = 2  # 学习因子
C2 = 2  # 学习因子
W_MAX = 0.9     # 惯性因子
W_MIN = 0.4     # 惯性因子



