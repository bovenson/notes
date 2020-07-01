#!/bin/python3

class A:
    def __init__(self):
        print("init class A")

g = [A()]

def fg():
    # global gb     # NameError: name 'gb' is not defined
    # print(gb)
    g.append(0)
    print("run fg, and g: ", g)
