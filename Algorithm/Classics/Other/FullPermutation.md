---
title: 全排列问题
tags: 算法, 全排列
author: bovenson
email: szhkai@qq.com
---

[TOC]

# 全排列

从n个不同元素中任取m（m≤n）个元素，按照一定的顺序排列起来，叫做从n个不同元素中取出m个元素的一个排列。当m=n时所有的排列情况叫全排列。

公式：全排列数f(n)=n!(定义0!=1)

## 四种全排列算法

- 字典序法
- 递增进位制数法
- 递减进位制数法
- 临位对换法

### 字典序法

对给定的字符集中的字符规定了一个先后关系，在此基础上规定两个全排列的先后是从左到右逐个比较对应的字符的先后。

字典序法的起始序列是元素按照字典序排列的序列, 例如对于{1, 2, 3}， 起始序列是[1, 2, 3]。

字典序法的关键是**生成给定排列的下一个排列**。所谓一个排列的下一个排列就是这一个与下一个之间没有其他的，这就要求这一个与下一个有尽可能长的共同前缀，也即变化限制在尽可能短的后缀上。

#### 获取下一个排列

- 从数列的右边向左寻找连续递增序列，例如对于：[1, 3, 5, 4, 2]，其中[5, 4, 2]即为从右至左最长递增序列，记为序列L
- ​

# 递归获取全排列

```java
//: FullPermutation.java
import java.util.*;

public class FullPermutation {

    public static void swap(int[] array, int a, int b) {
        int t = array[a];
        array[a] = array[b];
        array[b] = t;
    }

    public static void FullPermutationRecursion(int[] array, int index) {
        if (index >= array.length) {
            System.out.println(Arrays.toString(array));
        } else {
            for (int i=index; i < array.length; ++i) {
                swap(array, i, index);
                FullPermutationRecursion(array, index+1);
                swap(array, i, index);
            }
        }
    }

    public static void main(String args[]) {
        int[] array = {1, 2, 3};
        FullPermutationRecursion(array, 0);
    }
} /*Output
[1, 2, 3]
[1, 3, 2]
[2, 1, 3]
[2, 3, 1]
[3, 2, 1]
[3, 1, 2]
*/
```

# 参考

- [参考一](http://blog.csdn.net/joylnwang/article/details/7064115)
- [参考二](https://www.cnblogs.com/houkai/p/3675270.html)
- [全排列](https://baike.baidu.com/item/%E5%85%A8%E6%8E%92%E5%88%97/4022220?fr=aladdin)