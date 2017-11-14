#!/bin/python3
# coding: utf-8

"""
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-14 20:03"


class A:
    pass


if __name__ == "__main__":
    print(A)
    a = A
    b = A()
    print(a == A)
    print(a is A)
    print(b)
    print(b == A)
    print(b is A)
    print(isinstance(b, A))
    print(isinstance(a, A))
