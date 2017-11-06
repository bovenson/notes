#!/bin/python3
# coding: utf-8

"""
背包
背包支持的操作:
 - add() : 添加一个元素
 - is_empty() : 判断背包是否为空
 - size() : 背包中的元素数量
 - __getitem__() : 可迭代
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"


class Bag:
    items = []

    def add(self, obj):
        self.items.append(obj)

    def is_empty(self):
        return len(self.items) == 0

    def size(self):
        return len(self.items)

    def __getitem__(self, item):
        if item > len(self.items):
            raise IndexError("Out of index")
        return self.items[item]


if __name__ == "__main__":
    bag = Bag()
    bag.add("a")
    bag.add("size")
    print(bag.size())
    print(bag.is_empty())
    bag.add(1000)
    for obj in bag:
        print(obj)
