#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 002.py
DATE: 17-9-27 上午10:20
DESC: Data discretization example.
"""
import pandas as pd

data_file = './data/discretization_data.xls'  # initialize parameters
data = pd.read_excel(data_file)  # read data
data = data[u'肝气郁结证型系数']
k = 4

d1 = pd.cut(data, k, labels=range(k))  # equidistant discretization;

# equal frequency discretization
w = [1.0 * i / k for i in range(k + 1)]
w = data.describe(percentiles=w)[4:4 + k + 1]  # using describe() function to calculate the quantile
w[0] = w[0] * (1 - 1e-10)
d2 = pd.cut(data, w, labels=range(k))

from sklearn.cluster import KMeans

kmodel = KMeans(n_clusters=k, n_jobs=4)  # build model; n_jobs is the number of parallel processes
kmodel.fit(data.values.reshape((len(data)), 1))  # training model
c = pd.DataFrame(kmodel.cluster_centers_).sort_values(0)  # output sorted clustering center
# w = pd.DataFrame.rolling(c, 2).iloc[1:]  # using middle of two adjacent items as border point
# w = pd.DataFrame.rolling(window=2,center=False).mean()
w = pd.rolling_mean(c, 2).iloc[1:]  # using middle of two adjacent items as border point
w = [0] + list(w[0]) + [data.max()]  # add start/end border point
print(w)
d3 = pd.cut(data, w, labels=range(k))


def cluster_plot(d, k):  # customize the drawing function to display the clustering results
    import matplotlib.pyplot as plt
    plt.rcParams['font.sans-serif'] = ['SimHei']
    plt.rcParams['axes.unicode_minus'] = False  # used to display negative signs correctly

    plt.figure(figsize=(8, 3))
    for j in range(0, k):
        plt.plot(data[d == j], [j for i in d[d == j]], 'o')

    plt.ylim(-0.5, k-0.5)
    return plt

cluster_plot(d1, k).show()
cluster_plot(d2, k).show()
cluster_plot(d3, k).show()
