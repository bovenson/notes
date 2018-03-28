---
title: KKT条件
tags:
	- 数学
	- 机器学习
	- 最优化
categories:
	- 数学
	- 机器学习
	- 最优化
---

# 简介

KKT条件是解决最优化问题的时用到的一种方法。我们这里提到的最优化问题通常是指对于给定的某一函数，求其在指定作用域上的全局最小值。

KKT条件是在给定一组约束条件下的最小值(或者最大值)的数学问题。

# 详细介绍

-   设目标函数 $$f(x)$$，不等式约束为$$g(x)$$，有时会有等式约束

-   此时约束优化问题描述如下

    -   $$
        \min f(x)	\\
        s.t. \ \ h_j(x) = 0,j=1,2,\cdots,p	\\
        	g_k(x) \leqslant 0, q=1,2,\cdots,q
        $$

-   定义不等式约束下的拉格朗日函数

-   $$
    L(x,\lambda, \mu) = f(x)+\sum\limits_{j=1}^{p}\lambda_jh_j(x) + \sum\limits_{k=1}^{q}\lambda_kg_k(x)
    $$

-   其中 $$f(x)$$ 是原目标函数，$$hj(x)$$ 是第 $$j$$ 个等式约束条件，$$\lambda_j$$ 是对应的约束系数，$$g_k$$是不等式约束，$$\mu_k$$是对应的约束系数

-   此时若要求解上述优化问题，必须满足下述条件
    $$
    \begin{equation}
      \left\{
       \begin{aligned}
      	\left. \frac{\partial L}{\partial x} \right| _{x=x^*} =0 \hspace{10mm} (1)\\
      	\lambda i \neq 0\hspace{10mm} (2) \\
      	\mu_k \geqslant 0  \hspace{10mm} (3) \\
      	\mu_k g_k (x^*) = 0 \hspace{10mm} (4) \\
      	h_j(x^*) = 0,j=1,2,\cdots,p \hspace{10mm} (5) \\
      	g_k(x^*) \leqslant 0,k=1,2,\cdots,q \hspace{10mm} (6)
       \end{aligned}
       \right.
      \end{equation}
    $$

-   这些求解条件就是KKT条件

    -   `(1)`是对拉格朗日函数取极值时候带来的一个必要条件

    -   `(2)`是拉格朗日系数约束（同等式情况）

    -   `(3)`是不等式约束情况

        >   我们构造 $$L(x,\lambda,\mu)$$ 函数，是希望 $$L(x,\lambda,\mu)<=f(x)$$ 的（$$min$$表示求最小值）。在$$L(x,\lambda,\mu)$$表达式中第二项为0，若使得第三项小于等于0就必须使得系数$$\mu>=0$$，这也就是条件`(3)`

    -   `(4)`是互补松弛条件

        >   直观的解释可以这么看：要求得 $$L(x,\lambda,\mu)$$ 的最小值一定是三个公式项中取得最小值，此时第三项最小就是等于0值的时候。稍微正式一点的解释，是由松弛变量推导而来。

    -   `(5) (6)`是原约束条件

-   对于一般的任意问题而言，KKT条件是使一组解成为最优解的必要条件，当原问题是凸问题的时候，KKT条件也是充分条件

# 参考

-   [参考一](http://www.cnblogs.com/zhangchaoyang/articles/2726873.html)
-   [参考二](http://blog.csdn.net/johnnyconstantine/article/details/46335763)