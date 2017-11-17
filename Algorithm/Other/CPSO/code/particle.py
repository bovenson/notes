#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
粒子
"""
# import copy
#
# from utils import get_swap_pair
# from velocity import Velocity, ReplacingFactor
#
# __author__ = "bovenson"
# __email__ = "szhkai@qq.com"
# __date__ = "2017-11-16 14:55"
#
#
# class Particle:
#     """粒子"""
#     def __init__(self, wf_seq: list):
#         """
#         初始化
#         :param wf_seq: 工作流序列
#         """
#         self.wf_seq = copy.deepcopy(wf_seq)
#
#     def subtraction(self, other: 'Particle')->Velocity:
#         """
#         粒子相减
#         :param other: 另一个粒子
#         :return: 速度
#         """
#         _wf_seq_len = len(self.wf_seq)
#         _v = Velocity(_wf_seq_len)
#         if _wf_seq_len != len(other.wf_seq):
#             raise Exception("工作流序列长度不一致")
#         for _left, _right in get_swap_pair(self.wf_seq, other.wf_seq):
#             _left += 1
#             _right += 1
#             _v.append_factor(left=_left, right=_right)
#         return _v
#
#
# if __name__ == "__main__":
#     p1 = Particle([1, 4, 3, 2, 6, 5])
#     p2 = Particle([4, 3, 2, 1, 6, 5])
#     v = p1.subtraction(p2)
#     print(v)
#     print(v.replacing_factor)
