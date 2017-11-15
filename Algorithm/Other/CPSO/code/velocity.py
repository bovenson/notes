#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
粒子群速度
"""
import random

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-15 14:49"


class ReplacingFactor:
    """置换因子"""
    def __init__(self, n: int = 0):
        self.n = n
        if n > 0:
            _random = random.sample(range(1, n+1), 2)
            self.left = _random[0]
            self.right = _random[1]

    def __eq__(self, other):
        return self.left == other.left and self.right == other.right

    def __str__(self):
        return str('(' + str(self.left) + ', ' + str(self.right) + ')')


class Velocity:
    """速度"""
    def __int__(self, wf_cnt, random_generate_rf=False, max_cnt=0):
        self.replacing_factor = []
        self.wf_cnt = wf_cnt
        if random_generate_rf:
            self.random_generate(max_cnt)

    def random_generate(self, max_cnt: int):
        """
        随机生成速度
        :param max_cnt:
        :return:
        """
        _cnt = random.randint(1, max_cnt)
        _i = 0
        while _i < _cnt:
            _rf = ReplacingFactor(self.wf_cnt)
            if _rf in self.replacing_factor:
                continue
            else:
                self.replacing_factor.append(_rf)
                _i += 1


def init_velocity_set(n: int):
    pass


if __name__ == "__main__":
    li = []
    for i in range(100):
        rf = ReplacingFactor(10)
        if rf in li:
            print("IN")
            print(rf)
        else:
            li.append(rf)
    print(li)
