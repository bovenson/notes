#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
适应度计算
"""
from workflow import WorkFlow

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-13 17:34"


def fitness(wfs: list):
    """
    计算适应度
    :param wfs: 任务序列
    :return: None
    """
    start_time = 0
    cur_time = 0
    end_time = 0
    all_tasks = []
    for wf in wfs:
        all_tasks.extend(wf.task)
    pass
 