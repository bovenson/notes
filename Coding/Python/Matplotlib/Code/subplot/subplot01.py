#!/bin/python
import matplotlib.pyplot as plt

# add a subplot
ax1=plt.subplot(2, 2, 1)
# add a subplot with no frame
ax2=plt.subplot(2, 2, 2, frameon=False)
# add a polar subplot
plt.subplot(2, 2, 3, projection='polar')
# add a red subplot that shares the x-axis with ax1
plt.subplot(2, 2, 4, sharex=ax1, facecolor='red')

#delete ax2 from the figure
plt.delaxes(ax2)
#add ax2 to the figure again
plt.subplot(ax2)

plt.show()