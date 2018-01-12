---
title: 统计学概念
---

# 统计量与估计量

## 点估计

### 极大似然估计

设总体含有待估参数 $\theta$ ，它表示取很多值，我们要在 $\theta$ 的一切可能取值之中选出一个使样本观测值出现的概率为最大的 $\theta$ 值（记为 $\hat{\theta}$ ）作为 $\theta$ 的估计，并称 $\hat{\theta}$ 为 $\theta$ 的 **极大似然估计(MLE)**。

$L(x_1, x_2, \dots,x_n;\hat{\theta}) = \max \limits_{\theta \in \Theta} L(x_1,x_2,\dots,x_n;\theta)$

亦记作：

$L(\hat{\theta}) = \max \limits_{\theta \in \Theta} L(\theta)$

#### 求解步骤

- 写出似然函数
- 对似然函数取对数(为了方便计算)，并整理
- 求导数
- 解似然方程

#### 似然函数

##### 离散分布下的似然函数

设总体 $X$ 有如下离散分布：

$P(X=a_i) = p(a_i;\theta),i=1,2,\dots,\theta \in \Theta$

其中 $\theta$ 为未知参数，$\Theta$ 为参数空间。现从该总体抽取容量为 $n$ 的样本 $x_1,x_2,\dots,x_n$ ，这里诸 $x_i$ 可为 $a_1,a_2,\dots$ 中的某个值。显然，该样本出现的概率为

$L(x_1,x_2,\dots,x_n;\theta) = \prod \limits_{i=1} \limits^{n} p(x_i;\theta)$

它既是样本 $x_1,x_2,\dots,x_n$ 的函数，又是未知参数 $\theta$ 的函数。当获得了样本观测值后，$L$ 仅是 $\theta$ 的函数，即：

$L(\theta) =  \prod \limits_{i=1} \limits^{n} p(x_i;\theta), \theta \in \Theta$

其函数值仍是概率，它表征参数 $\theta$ 出现的可能性大小。如 $L(\theta _1) > L(\theta _2)$，则说在同一样本观测值下，出现$\theta_1$ 比出现 $\theta _2$ 的可能性大。如此定义在参数空间 $\Theta$ 上的函数 $L(\theta)$ 称为 **似然函数**。它是度量 $\theta$ 出现可能性的大小的测度。

#### 连续分布下的似然函数

当总体 $X$ 有连续分布时，其密度函数 $p(x;\theta)$ 的值虽不是概率，但是与概率成正比例的值，故其样本$x_1,x_2,\dots,x_n$ 出现的概率可用联合密度 $\prod \limits_{i=1} \limits^{n} p(x;\theta)$ 度量大小。它亦是样本 $x_1,x_2,\dots,x_n$ 和 $\theta$ 的函数，当获取样本观测值后，它仅是$\theta$ 的函数，仍极为

$L(\theta) =  \prod \limits_{i=1} \limits^{n} p(x_i;\theta), \theta \in \Theta$

它亦是度量 $\theta$ 出现可能性大小的测度。如此定义在参数空间 $\Theta$ 上的函数 $L(\theta)$ 亦称为似然函数。 

 