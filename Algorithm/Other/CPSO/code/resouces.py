#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
资源类
"""
from workflow import Task

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-12 12:06"


class TrashResource:
    def __init__(self, speed: int, name: str = ""):
        """
        初始化
        :param speed: 该翻译人员每分钟翻译单词次数
        """
        self.speed = speed
        self.tasks = []
        self.name = name

    def append_task(self, task: Task):
        self.tasks.append(task)

    def print_tasks(self):
        print(self.name, ' ', self.tasks)

    def __str__(self):
        return self.name


class TrashTranslator(TrashResource):
    """翻译人员"""
    pass


class TrashAuditor(TrashResource):
    """审核人员"""
    pass


if __name__ == "__main__":
    t = TrashTranslator(speed=400)
    a = TrashAuditor(speed=400)
    print(t.speed)
