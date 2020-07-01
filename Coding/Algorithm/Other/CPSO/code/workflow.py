#!/bin/python3
# coding: utf-8

"""
基于粒子群优化算法的工作流中任务调度算法设计
任务流
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-13 17:06"


class Resource:
    def __init__(self, speed: int, name: str = ""):
        """
        初始化
        :param speed: 该翻译人员每分钟翻译单词次数
        """
        self.speed = speed
        self.tasks = []
        self.start_time = 0
        self.end_time = 0
        self.name = name

    def append_task(self, task: 'Task', start_time: int):
        """添加任务"""
        _time = task.words / self.speed
        task.start_time = start_time
        task.end_time = start_time + _time
        if self.start_time is None:
            self.start_time = start_time
        self.end_time = task.end_time
        self.tasks.append(task)
        task.done = True
        task.process_resource = self

        # print('resource:', self, ' task:', task)

    def print_tasks(self):
        print(self.name, ' ', self.tasks)

    def __str__(self):
        return self.name

    def reset(self):
        self.tasks = []
        self.start_time = 0
        self.end_time = 0


class Translator(Resource):
    """翻译人员"""
    pass


class Auditor(Resource):
    """审核人员"""
    pass


class Task:
    """任务"""
    def __init__(self, words: int, previous, task_type):
        """
        任务类
        :param words: 单词数
        :param previous: 先行任务
        """
        self.words = words
        self.previous = previous
        self.task_type = task_type
        self.done = False
        self.start_time = 0
        self.end_time = 0
        self.process_resource = None

    def get_previous_end_time(self):
        return 0 if self.previous is None else self.previous.end_time

    def reset(self):
        self.done = False
        self.start_time = 0
        self.end_time = 0
        self.process_resource = None

    def __str__(self):
        return str(self.words)

    def __repr__(self):
        return self.__str__()


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
        self.tasks.append(Task(self.words, previous=None, task_type=Translator))
        self.tasks.append(Task(self.words, previous=self.tasks[-1], task_type=Auditor))

    def __eq__(self, other):
        return self.words == other.words

    def __str__(self):
        return str(self.words)

    def __repr__(self):
        return str(self.words)


if __name__ == "__main__":
    t = Translator(speed=400)
    a = Auditor(speed=400)
    print(t.speed)
