#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
粒子群速度
"""
import copy
import random

from utils import get_swap_pair

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-15 14:49"


C1 = 2  # 学习因子
C2 = 2  # 学习因子
V_SET_MAX_LEN_FACTOR = 1  # 速度集合交换对数最大数量比例
# V_SET_MAX_LEN = 30


class ReplacingFactor:
    """置换因子"""
    def __init__(self, left=None, right=None, random_n: int = 0):
        """
        初始化
        :param left: 左侧元素索引
        :param right: 右侧元素索引
        :param random_n: 最大随机值
        """
        # self.n = n
        if left is None and right is None:
            _random = random.sample(range(1, random_n+1), 2)
            self.left = _random[0]
            self.right = _random[1]
        else:
            self.left = left
            self.right = right

    def __eq__(self, other):
        return self.left == other.left and self.right == other.right

    def __str__(self):
        return '(' + str(self.left) + ', ' + str(self.right) + ')'

    def __repr__(self):
        return self.__str__()


class Velocity:
    """速度"""
    def __init__(self, wf_cnt, random_generate_rf=False, max_cnt=0):
        """
        初始化速度
        :param wf_cnt: 每个工作流中工作数量
        :param random_generate_rf: 是否随机生成调换序列
        :param max_cnt: 随机生成的调换序列最大数量
        """
        self.replacing_factor = []
        self.wf_cnt = wf_cnt
        if random_generate_rf:
            self.random_generate(max(max_cnt, 1))

    def append_factor(self, left, right):
        """增加对换对"""
        _rf = ReplacingFactor(left=left, right=right)
        if _rf not in self.replacing_factor:
            self.replacing_factor.append(_rf)

    def random_generate(self, max_cnt: int):
        """
        随机生成速度
        :param max_cnt:
        :return:
        """
        _cnt = random.randint(max(1, int(max_cnt*0.5)), max_cnt)
        _i = 0
        while _i < _cnt:
            _rf = ReplacingFactor(random_n=self.wf_cnt)
            if _rf in self.replacing_factor:
                _i += 1
                continue
            else:
                # print(_rf)
                self.replacing_factor.append(_rf)
                _i += 1

    def multiplication(self, vm: float):
        _remain = int(len(self.replacing_factor) * vm)
        _remain = max(_remain, 1)
        _remain = min(_remain, len(self.replacing_factor))
        self.replacing_factor = random.sample(self.replacing_factor, _remain)
        return self

    def update(self, current_position: 'Particle', local_optimal_solution: 'Particle',
               global_optimal_solution: 'Particle', w: float):
        """更新速度"""
        # W*Vi(t)
        self.multiplication(w)
        # c1(Pi - Xi(t))
        # print('A: ', local_optimal_solution)
        # print('B: ', current_position)
        # print('C: ', local_optimal_solution.subtraction(current_position))
        # _res = local_optimal_solution.subtraction(current_position).multiplication(C1)
        # print(_res)
        _res = local_optimal_solution.subtraction(current_position).multiplication(C1 * random.uniform(0, 1.0))
        # print('A: ', _res)
        self.plus(_res)
        # c2(Pg - Xi(t))
        _res = global_optimal_solution.subtraction(current_position).multiplication(C2 * random.uniform(0, 1.0))
        # print('B: ', _res)
        self.plus(_res)
        # _t_v = Velocity(wf_cnt=self.wf_cnt, random_generate_rf=True, max_cnt=V_SET_MAX_LEN)
        # self.plus(_t_v)
        _max_len = int(V_SET_MAX_LEN_FACTOR * len(self.replacing_factor) + 0.000001)
        # print(self.__str__())
        # print(len(self.replacing_factor))
        # print(_max_len)
        # _max_len = max(1, _max_len)
        # _max_len = min(_max_len, len(self.replacing_factor), V_SET_MAX_LEN)
        # _max_len = min(V_SET_MAX_LEN, len(self.replacing_factor))
        # print(_max_len)
        if len(self.replacing_factor) > _max_len:
            self.replacing_factor = random.sample(self.replacing_factor, _max_len)

    def plus(self, other: 'Velocity'):
        # print('PLUS: ', other)
        for _rf in other.replacing_factor:
            self.append_factor(_rf.left, _rf.right)
        return self

    def print_rf(self):
        for _rf in self.replacing_factor:
            print(_rf, end=' ')
        print()

    def __repr__(self):
        _res = ''
        for _rf in self.replacing_factor:
            _res += str(_rf) + ' '
        return _res


def init_velocity_set(n: int):
    """生成一个速度集合, 包含n个速度"""
    _res = []
    for _i in range(n):
        _res.append(Velocity(wf_cnt=n, random_generate_rf=True, max_cnt=n))
    return _res


class Particle:
    """粒子"""
    def __init__(self, wf_seq: list, fitness=None):
        """
        初始化
        :param wf_seq: 工作流序列
        """
        self.wf_seq = copy.deepcopy(wf_seq)
        self.fitness = fitness

    def subtraction(self, other: 'Particle')->Velocity:
        """
        粒子相减
        :param other: 另一个粒子
        :return: 速度
        """
        _wf_seq_len = len(self.wf_seq)
        _v = Velocity(_wf_seq_len)
        if _wf_seq_len != len(other.wf_seq):
            raise Exception("工作流序列长度不一致")
        for _left, _right in get_swap_pair(self.wf_seq, other.wf_seq):
            _left += 1
            _right += 1
            _v.append_factor(left=_left, right=_right)
        return _v

    def update(self, velocity: Velocity):
        """使用速度更新位置"""
        for _rf in velocity.replacing_factor:
            _t = self.wf_seq[_rf.left-1]
            self.wf_seq[_rf.left-1] = self.wf_seq[_rf.right-1]
            self.wf_seq[_rf.right-1] = _t
        return self

    def __str__(self):
        return 'fitness:' + str(self.fitness) + ' seq:' + str(self.wf_seq)

    def __repr__(self):
        return self.__str__()


if __name__ == "__main__":
    # li = []
    # for i in range(100):
    #     rf = ReplacingFactor(10)
    #     if rf in li:
    #         print("IN")
    #         print(rf)
    #     else:
    #         li.append(rf)
    # print(li)

    # v = Velocity(3, True, 10)
    # v = v.multiplication(0.1)
    # print(v.replacing_factor)
    # v.print_rf()
    # v.append_factor(1, 2)
    # v.append_factor(2, 1)
    # v.print_rf()

    p1 = Particle([1, 4, 3, 2, 6, 5])
    p2 = Particle([4, 3, 2, 1, 6, 5])
    v = p1.subtraction(p2)
    print(v)
    print(v.replacing_factor)
    # for t in v.replacing_factor:
    #     print(t)
    print(p1)
    print(p2)
    print(p2.update(v))

