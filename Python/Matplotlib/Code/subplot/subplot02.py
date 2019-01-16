#!/bin/python3

"""
@author	sunzhenkai
@file	subplot02.py
@date	2019-01-16 09:20:20
@desc
"""

import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(0, 2*np.pi, 100)
xx = np.linspace(0, 2*np.pi + 0.5, 100)
y = 2*np.sin(x)
plt.plot(x, label="x")
plt.plot(xx, label="xx")
plt.legend()
plt.show()
