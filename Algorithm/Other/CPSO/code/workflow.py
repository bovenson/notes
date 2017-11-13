#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
任务流
"""
from enum import Enum

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-13 17:06"


class TaskType(Enum):
    Translation = 1
    Audition = 2


class Task:
    """任务"""
    def __init__(self, words: int, previous, task_type: TaskType):
        """
        任务类
        :param words: 单词数
        :param previous: 先行任务
        """
        self.words = words
        self.previous = previous
        self.task_type = task_type
        self.done = False


class WorkFlow:
    """任务流"""
    def __init__(self, words: int):
        """
        任务流
        :param words: 单词数
        """
        self.words = words
        self.tasks = []
        self.init_tasks()

    def init_tasks(self):
        """初始化工作流的任务流"""
        self.tasks.append(Task(self.words, previous=None, task_type=TaskType.Translation))
        self.tasks.append(Task(self.words, previous=self.tasks[-1], task_type=TaskType.Audition))

    def __str__(self):
        return str(self.words)
