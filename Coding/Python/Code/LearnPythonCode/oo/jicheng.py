#!/bin/python3
# coding: utf-8

"""
FILE: 
DESC: 
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-07-20 10:51"


class A:
    def f(self):
        print('A.f()')


class B:
    def f(self):
        print('B.f()')


class C(B, A):
    pass


c = C()
c.f()

cs = C()
super(C, cs).f()
print(C.mro())