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
    all_tasks = []

    # 初始化信息
    for _resource_level in resources:
        for _resource in _resource_level:
            _resource.reset()
    for _wf in wfs:
        for _task in _wf.tasks:
            _task.reset()
            all_tasks.append(_task)

    _end_time = 0
    task_len_of_wf = len(wfs[0].tasks)  # 每个工作流中任务数
    for i in range(task_len_of_wf):
        _resources = resources[i]
        _cur_level_tasks_list = [_wf.tasks[i] for _wf in wfs]
        _cur_level_tasks = set(_cur_level_tasks_list)

        while len(_cur_level_tasks) > 0:
            # 获取最先执行的任务
            _selected_task = None
            for _task in _cur_level_tasks:
                if _task.previous is not None and _task.previous.done is False:
                    continue
                if _selected_task is None or _task.previous is None or \
                                _task.get_previous_end_time() < _selected_task.get_previous_end_time():
                    _selected_task = _task

            _cur_level_tasks.remove(_selected_task)
            # 计算任务最早可以开始的时间
            _earliest_start_time = _task.previous.end_time if _task.previous is not None else 0
            # 找到空闲资源
            _selected_resource = None
            for _resource in _resources:
                if _resource.end_time is None or _selected_resource is None or \
                                _resource.end_time < _selected_resource.end_time:
                    _selected_resource = _resource
            # 添加任务
            _selected_resource.append_task(_task,
                                           max(_earliest_start_time, _resource.end_time if _resource.end_time else 0))
            _end_time = max(_end_time, _task.end_time)

    # for _task in all_tasks:
    #     print(_task, ' start_time:', _task.start_time, ' end_time:', _task.end_time,
    # ' resource:', _task.process_resource)
    return _end_time


if __name__ == "__main__":
    pass
