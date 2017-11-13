#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
"""
from workflow import WorkFlow
import random

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-12 12:00"

# 初始化变量
L = 20  # 最大迭代次数
N = 10  # 初始粒子数量
C1 = 2  # 学习因子
C2 = 2  # 学习因子
W_MAX = 0.9  # 惯性因子
W_MIN = 0.4  # 惯性因子


def cpso(wf_words: list):
    """
    cpso算法
    :param wf_words: 工作流单词数
    :return: None
    """
    # 初始化工作流
    wfs = []
    for word_cnt in wf_words:
        wfs.append(WorkFlow(word_cnt))

    # 初始化粒子数
    particles = [[random.sample(wfs, len(wfs))] for _ in range(N)]
    # for wf in particles:
    #     for _wf in wf:
    #         for __wf in _wf:
    #             print(__wf, end=' ')
    #     print()




if __name__ == "__main__":
    wf_words_input = "579	276	7	408	408	243	422	20	650	839	717	283	249	865	925	606	54	825	513	440	786	225	214	" \
                     "740	719	614	760	2000 816	443	766	611	519	207	820	483	703	740	294	274	608	586	887	841	798	" \
                     "229	247	318	829	978"
    cpso([int(i) for i in wf_words_input.split()])
