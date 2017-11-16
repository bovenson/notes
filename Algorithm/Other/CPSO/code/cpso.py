#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
"""
import copy

from fitness_figure import fitness
from workflow import WorkFlow, Translator, Auditor
import random

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-12 12:00"

# 初始化变量
L = 20  # 最大迭代次数
N = 10  # 初始粒子数量


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

    # 初始化资源
    resources = [[] for _ in range(2)]
    resources[0] = []
    resources[1] = []
    resources[0].append(Translator(speed=400))
    resources[0].append(Translator(speed=400))
    resources[0].append(Translator(speed=400))
    resources[0].append(Translator(speed=400))
    resources[0].append(Translator(speed=400))
    resources[1].append(Auditor(speed=800))
    resources[1].append(Auditor(speed=800))
    # resources[1].append(Auditor(speed=800))

    # 初始化粒子数
    particles = [random.sample(wfs, len(wfs)) for _ in range(N)]

    # 初始化速度集合

    # print(id(particles))
    # print(id(particles[0]))
    # particles_copy = copy.deepcopy(particles)
    # print(id(particles_copy[0]))
    # print(len(particles))
    # print(particles[0])

    # 局部最优解
    local_optimal_solution = copy.deepcopy(particles)
    local_optimal_solution_value = [None for _ in range(len(wfs))]

    # 全局最优解
    global_optimal_solution = None
    global_optimal_solution_value = None

    # 迭代
    for i in range(L):
        for j in range(len(particles)):
            _particle = particles[j]
            # 当前粒子适应度
            _res = fitness(_particle, resources)
            if local_optimal_solution_value[j] is None or local_optimal_solution_value > _res:  # 更新局部最优解
                local_optimal_solution[j] = copy.deepcopy(_particle)
                local_optimal_solution_value[j] = _res

            if global_optimal_solution_value is None or global_optimal_solution_value > _res:   # 更新全局最优解
                global_optimal_solution = copy.deepcopy(_particle)
                global_optimal_solution_value = _res

        # 使用速度集合更新位置

        # 更新速度
        # 计算适应度

        # 打印全局最优解
        print(global_optimal_solution)
        print(global_optimal_solution)


if __name__ == "__main__":
    wf_words_input = "579	276	7	408	408	243	422	20	650	839	717	283	249	865	925	606	54	825	513	440	786	225	214	" \
                      "740	719	614	760	2000 816	443	766	611	519	207	820	483	703	740	294	274	608	586	887	841	798	" \
                      "229	247	318	829	978"
    # wf_words_input = "400 400 800 800 1200 1200 800"
    # wf_words_input = "800 800 400 400"
    cpso([int(i) for i in wf_words_input.split()])
