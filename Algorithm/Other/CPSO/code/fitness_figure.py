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


def fitness(wfs: list, resources: list):
    """
    计算适应度
    :param resources: 资源
    :param wfs: 任务序列
    :return: None
    """
    start_time = 0
    cur_time = 0
    end_time = 0
    all_tasks = []
    task_len_of_wf = len(wfs[0].tasks)
    for i in range(task_len_of_wf):
        _resources = resources[i]
        _tasks = [_wf.tasks[i] for _wf in wfs]
        for j in range(len(_tasks)):     # 做任务
            # 找到空闲资源
            _leisure_resource = None
            _end_time = None
            _task = _tasks[j]
            for _t_resource in _resources:
                if _t_resource.end_time is None:
                    _leisure_resource = _t_resource
                else:
                    if _end_time is None or _end_time > _t_resource.end_time:
                        _leisure_resource = _t_resource
                        _end_time = _t_resource.end_time
            # 完成任务
            _time = _task.words / _leisure_resource.speed
            _task.done = True
            _task.start_time = _leisure_resource.end_time
            _task.end_time = _task.start_time + _time
            _leisure_resource.end_time

            pass
    pass


if __name__ == "__main__":
    pass
