#!/bin/python3

"""
@author	sunzhenkai
@file	subplot03.py
@date	2019-01-16 19:26:55
@desc	
"""

import matplotlib.pyplot as plt

# from matplotlib.pyplot import figure
# figure(figsize=(20, 10), dpi=80,)

plt.figure(figsize=(15, 10), dpi=80)

y = [i for i in range(100)]
z = [i * 1.1 for i in range(100)]

plt.subplot(2, 2, 1)
plt.xlabel('x label')
plt.ylabel('y label')
plt.plot(y, label='y')
plt.plot(z, label='z')
plt.legend()

#### no legend
plt.subplot(2, 2, 2)
plt.xlabel('x label')
plt.ylabel('y label')
plt.plot(y, label='y')
plt.plot(z, label='z')

plt.subplot(2, 2, 4)
plt.xlabel('x label')
plt.ylabel('y label')
plt.plot(y, label='y')
plt.plot(z, label='z')
plt.legend()

# plt.show()
plt.savefig('subplot03.png', dpi=120)
