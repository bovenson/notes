---
title: JVM - GC
tags:
    - GC
categories:
    - JVM
---

# Description

## 对象存活判定算法

- 引用计数算法
  - 缺点
    - 很难判断循环依赖问题
- 可达性分析算法

# 内存分区

![](imgs/004.jpg)

## 分区

- 新生代（Youn Generation）
  - Eden
  - Survivor
    - FromSpace
    - ToSpace
- 旧生代（Old Generation）
- 持久代（Permanent Generation）

## GC

- Minor GC
  - 新生代进行垃圾回收时进行 Minor GC / Youn GC
- Major GC
  - 旧生代进行垃圾回收时进行 Major GC / Full GC

### GC日志

**Youn GC**

![](imgs/005.jpg)

**Full GC**

![](imgs/006.jpg)

# 调优

| 参数                               | 说明                         | 示例    |
| ---------------------------------- | ---------------------------- | ------- |
| -Xmx                               | 最大堆大小                   | -Xmx58g |
| -Xms                               | 初始堆大小                   | -Xms58g |
| -Xmn                               | 年轻代大小                   |         |
| +UseCompressedOops                 |                              | -       |
| -XX:MaxPermSize=256M               | 设置持久代最大值             |         |
| -XX:SurvivorRatio=20               | Eden区与Survivor区的大小比值 |         |
| -XX:+UseParNewGC                   |                              |         |
| -XX:MaxTenuringThreshold=1         |                              |         |
| -XX:+UseConcMarkSweepGC            |                              |         |
| -XX:+UseCMSCompactAtFullCollection |                              |         |
