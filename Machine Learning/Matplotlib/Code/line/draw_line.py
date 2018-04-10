#!/bin/python3
# coding: utf-8

"""
划直线
"""
import matplotlib.pyplot as plt
from matplotlib.collections import PatchCollection
from matplotlib.patches import Rectangle

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-12-14 20:04"

# plt.plot([1, 2, 3, 4])
# plt.plot([1, 2, 3, 4], [1, 4, 9, 16])
# plt.plot([10, -5, -10, 15], [15, 0, 0, 15], [0, -5, -20, 15])
# 点(1, 1), 到点(3, 3) 的直线
# plt.plot([1, 3], [1, 3], marker='o')

plt.plot([10, -10], [-5, 15], [15, 0], [0, 15], [0, -20], [-5, 15])
fig, ax = plt.subplots()

patches = [Rectangle((0, 0), 10, 10)]

p = PatchCollection(patches)
ax.add_collection(p)

plt.show()
