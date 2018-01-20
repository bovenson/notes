---
title: 第一周
---

[TOC]

# 介绍

## 什么是机器学习

### 定义

> A computer program is said to learn from experience E with respect to some task T and some performance measure P, if its performance on T , as measured by P, improves with experience E. 

### 机器学习算法

- 监督学习（Supervised learning）
- 无监督学习（Unsupervised learning）
- 其他：
  - 强化学习（Reinforcement learning）
  - 推荐系统（Recommender systems）
  - 等等

# 监督学习(Supervised learning)

在监督学习中，我们给出的训练集通常已经给出正确的输出。

监督学习被分为：回归和分类。 

- **支持向量机算法**：用简洁的数学方法，让电脑处理无限多的特征
- **监督学习基本思想**：对于数据集中的每个数据，都有正确的答案（训练集）
- **回归问题**：通过回归预测一个连续值得输出
- **分类问题**：目标是预测离散值输出

# 无监督学习(Unsupervised learning)

无监督学习是一种学习机制，给算法大量的数据，要求它找出数据中蕴含的数据结构。

> Unsupervised learning allows us to approach problems with little or no idea what our results should look like. We can derive structure from data where we don't necessary know th effect of the variables.
>
> We can derive this structure by clustering the data based on relationships among the variables in the data.

## 线性回归

### 损失函数

> We can measure the accuracy of our hypothesis funciton by using a **cost function**. This takes an average difference (actually a fancier version of an average) of all the results of the hypothesis with inputs from x's and the actual output y's.

- 弄清楚如何把最有可能的直线与我们的数据相拟合
- 找到能使训练集中预测值和真实值的差的平方和的 $\frac{1}{2m}$ 最小的 $\theta$ 值
- 损失函数也被称作 **平方误差函数**

### 梯度下降(Gradient descent)

#### 问题描述

Have some function $J(\theta_0, \theta_1)$

want $\min \limits_{\theta_0, \theta_1} J(\theta_0, \theta_1)$

Outline:

- Start with some $\theta_0, \theta_1$
- Keep changing $\theta_0,\theta_1$ to reduce $J(\theta_0, \theta_1)$ until we hopefully end up at a minimum

如果学习因子太小，收敛速度太慢；如果太大，可能越过最小值。

> Gradient descent can converge to a local minimum, even with the learning rate $\alpha$ fixed.
>
> As we approach a local minimum, gradient descent will automatically take smaller steps. So, no need to decrease $\alpha$ over time.

#  线性代数

## 矩阵

**定义：**由数字组成的矩形阵列。

**矩阵纬度：** (number of rows) * (number of columns)

## 向量

一个 `n * 1` 的矩阵。

$y = \begin{bmatrix} 460 \\ 232 \\ 315 \\ 178  \end{bmatrix}$

