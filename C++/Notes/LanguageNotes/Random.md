---
title: 随机数
tags:
	- 随机数
categories:
	- C++
---

```c++
#include <random>

std::random_device rd;     // only used once to initialise (seed) engine
std::mt19937 rng(rd());    // random-number engine used (Mersenne-Twister in this case)
std::uniform_int_distribution<int> uni(min,max); // guaranteed unbiased

auto random_integer = uni(rng);
```