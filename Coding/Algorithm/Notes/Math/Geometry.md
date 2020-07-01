---
title: 几何
tags:
	- Math
categories:
	- 算法
---

# 两点计算直线方程

求直线表达式

- 点斜式

$$
y - y_1 = k(x - x_1)
$$

- 斜截式

$$
y = kx + b
$$

- 两点式

$$
\frac{x - x_1}{x2 - x1} = \frac{y - y_1}{y_2 - y_1}
$$

- 截距式

$$
\frac{x}{a} + \frac{y}{b} = 1
$$

```c++
// 两点式计算直线方程
void pointToLine(int lax, int lay, int lbx, int lby, int &a, int &b, int &c) {
    // 获取直线方程
    a = lby - lay;
    b = lax - lbx;
    c = b * lay - a * lax;
    cout << a << " " << b << " " << c << endl;
}
```

## 参考

- [Ref 1](https://www.geeksforgeeks.org/program-find-line-passing-2-points/)