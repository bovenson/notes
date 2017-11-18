#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
"""
import copy

from fitness_figure import fitness
from velocity import Particle, Velocity
from workflow import WorkFlow, Translator, Auditor
import random

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-12 12:00"

# 初始化变量
W_MAX = 0.9  # 惯性因子
W_MIN = 0.4  # 惯性因子
L = 200  # 最大迭代次数
N = 15  # 初始粒子数量


def get_w(cur_l: int)->float:
    _res = W_MAX - cur_l * (W_MAX - W_MIN) / L
    # print('W: ', _res)
    return _res


def cpso(wf_words: list):
    """
    cpso算法
    :param wf_words: 工作流单词数
    :return: None
    """
    # 初始化工作流
    fitness_res = []
    particle_res = []

    wfs = []
    for word_cnt in wf_words:
        wfs.append(WorkFlow(word_cnt))

    # 初始化资源
    resources = [[] for _ in range(2)]
    resources[0] = []
    resources[1] = []
    # resources[0].append(Translator(speed=400))
    # resources[0].append(Translator(speed=400))
    resources[0].append(Translator(speed=400))
    resources[0].append(Translator(speed=400))
    resources[0].append(Translator(speed=400))
    resources[0].append(Translator(speed=500))
    # resources[0].append(Translator(speed=450))
    # resources[0].append(Translator(speed=500))
    # resources[0].append(Translator(speed=600))
    # resources[0].append(Translator(speed=700))
    resources[1].append(Auditor(speed=800))
    resources[1].append(Auditor(speed=800))
    # resources[1].append(Auditor(speed=800))
    # resources[2].append(Auditor(speed=600))
    # resources[2].append(Auditor(speed=700))
    # resources[2].append(Auditor(speed=800))
    # resources[1].append(Auditor(speed=800))

    # 初始化粒子数
    particles = [Particle(random.sample(wfs, len(wfs))) for _ in range(N)]
    # for _particle in particles:
    #     print(_particle)
    # return

    # 初始化速度集合
    velocities = [Velocity(len(wf_words), True, int(len(wf_words) * 0.3)) for _ in range(N)]
    # for _v in velocities:
    #     _v.print_rf()
    # return

    # print(id(particles))
    # print(id(particles[0]))
    # particles_copy = copy.deepcopy(particles)
    # print(id(particles_copy[0]))
    # print(len(particles))
    # print(particles[0])

    # 局部最优解
    local_optimal_solution = copy.deepcopy(particles)
    # local_optimal_solution_value = [None for _ in range(len(wfs))]

    # 全局最优解
    global_optimal_solution = None
    # global_optimal_solution_value = None

    # 迭代
    for i in range(L):
        for j in range(len(particles)):
            _particle = particles[j]
            # 当前粒子适应度
            _res = fitness(_particle.wf_seq, resources)
            # print('FITNESS: ', _res)
            if local_optimal_solution[j].fitness is None or local_optimal_solution[j].fitness > _res:  # 更新局部最优解
                local_optimal_solution[j] = copy.deepcopy(_particle)
                local_optimal_solution[j].fitness = _res

            if global_optimal_solution is None or (global_optimal_solution.fitness is not None
                                                   and global_optimal_solution.fitness > _res):  # 更新全局最优解
                global_optimal_solution = copy.deepcopy(_particle)
                global_optimal_solution.fitness = _res
                # global_optimal_solution_value = _res
            # print(_res)

            # 使用速度集合更新位置
            if isinstance(_particle, Particle):
                # print('Before update: ', _particle)
                # print('Velocity: ', velocities[j])
                _particle.update(velocities[j])
                # print('After update: ', _particle)

        # 更新速度
        for _i in range(len(velocities)):
            # print('Before update velocity: ', velocities[_i])
            _v = velocities[_i]
            _v.update(particles[_i], local_optimal_solution[_i], global_optimal_solution, get_w(i))
            # print('After update velocity: ', velocities[_i])
        print(velocities[0])
        # print(particles[0])

        # 打印全局最优解
        # print('全局最优解: ', global_optimal_solution)
        fitness_res.append(global_optimal_solution.fitness)
        particle_res.append(global_optimal_solution)
        # print(global_optimal_solution)

    # for _v in velocities: print(_v)
    # for f in fitness_res:
    #     print(f)

    for f in sorted(list(set(fitness_res)), reverse=True):
        print(f)
    #
    # for f in particle_res:
    #     print(f)


if __name__ == "__main__":
    wf_words_input = "579	276	7	1	408	243	422	20	650	839	717	283	249	865	925	606	54	825	513	440	786	225	214	" \
                     "740	719	614	760	2000 816	443	766	611	519	207	820	483	703	740	294	274	608	586	887	841	798	" \
                     "229	247	318	829	978"

    # sample_input = [33, 25, 29, 32, 17, 43, 37, 28, 34, 27, 47, 40, 46, 35, 12, 36, 19, 13, 22, 20]
    # random.sample(range(10, 50), 20)
    sample_input = random.sample(range(100, 5000), 200)
    # sample_input.extend(random.sample(range(2001, 8000), 10))
    # sample_input.extend(random.sample(range(8001, 10000), 70))
    print(sample_input)
    # wf_words_input = "400 400 800 800 1200 1200 800"
    # wf_words_input = "800 800 400 400"
    # cpso([int(i) for i in wf_words_input.split()])
    cpso(sample_input)
