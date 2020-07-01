---
title: Octave 使用
---

# 基本语法

## 注释符

`%`

## 基本运算

```octave
octave:1> 5+6
ans =  11
octave:2> 3-2
ans =  1
octave:3> 2*7
ans =  14
octave:4> 1/3
ans =  0.33333
octave:5> 2^6
ans =  64
```

## 符号

- `==` : 相等
- `~=` : 不相等
- `&&` : and
- `||` : or
- `xor(a, b)` : 异或

## 命令

### disp

打印函数

## 函数

### 

# 示例

## 矩阵求逆

```octave
octave:1> A = [3 4; 2 16]
A =

    3    4
    2   16

octave:2> pinv(A)
ans =

   0.400000  -0.100000
  -0.050000   0.075000
```

# 技巧

```octave
octave: > (cvPredictions == 1) & (yval == 0)	# 两向量对象元素相与
```

