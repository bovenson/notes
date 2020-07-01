#!/bin/python3

"""
@author	sunzhenkai
@file	plot01.py
@date	2019-01-16 12:08:19
@desc
"""

import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(0,2*np.pi,100)
y = 2*np.sin(x)
plt.plot(x, y)
plt.show()
