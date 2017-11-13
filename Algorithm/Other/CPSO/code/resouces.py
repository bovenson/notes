#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
资源类
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-12 12:06"


class Translator:
    """翻译人员"""
    def __int__(self, speed):
        """
        初始化
        :param speed: 该翻译人员每分钟翻译单词次数
        :return: None
        """
        self.speed = speed


class Auditor:
    """审核人员"""
    def __init__(self, speed):
        """
        初始化
        :param speed: 该审核人员每分钟次数
        """
        self.speed = speed
