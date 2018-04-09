---
title: Scikit-learn中Fit及Transorm比较
tags:
	- Scikit-Learn
	- 机器学习
categories:
	- 机器学习
---

# 比较

## `fit`

fit原义指的是安装、使适合的意思，其实有点train的含义但是和train不同的是，它并不是一个训练的过程，而是一个适配的过程，过程都是定死的，最后只是得到了一个统一的转换的规则模型。

## `transform`

transform则指的是转换。从可利用信息的角度来说，转换分为无信息转换和有信息转换。无信息转换是指不利用任何其他信息进行转换，比如指数、对数函数转换等。有信息转换从是否利用目标值向量又可分为无监督转换和有监督转换。无监督转换指只利用特征的统计信息的转换，统计信息包括均值、标准差、边界等等，比如标准化、PCA法降维等。有监督转换指既利用了特征信息又利用了目标值信息的转换，比如通过模型选择特征、LDA法降维等。

# 参考

- [参考一](https://datascience.stackexchange.com/questions/12321/difference-between-fit-and-fit-transform-in-scikit-learn-models)
- [参考二](https://stackoverflow.com/questions/46691596/why-does-sklearn-imputer-need-to-fit)
- [参考三](http://scikit-learn.org/stable/modules/cross_validation.html)
- [参考四](https://blog.csdn.net/qq_35082030/article/details/70338654)