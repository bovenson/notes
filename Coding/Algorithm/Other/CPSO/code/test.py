#!/bin/python3
# coding: utf-8

"""
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-14 20:03"


class A:
    def __init__(self, speed):
        self.speed = speed

    def __str__(self):
        return str(self.speed)

    def print_speed(self):
        print('speed: ', self.speed)


class AB(A):
    pass


if __name__ == "__main__":
    ab = AB(speed=100)
    ab.print_speed()
    # print(A)
    # a = A
    # b = A()
    # print(a == A)
    # print(a is A)
    # print(b)
    # print(b == A)
    # print(b is A)
    # print(isinstance(b, A))
    # print(isinstance(a, A))
