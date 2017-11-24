---
title: 控制执行流程
tags: Java, 控制执行流程
author: bovenson
email: szhkai@qq.com
---

[TOC]

# 控制执行流程

## true和false

所有条件语句都利用条件表达式的真或假来决定执行路径。

## if-else

if-else语句是控制程序流程的最基本形式。其中的else是可选的。

```java
if (Boolean-expression)
  	Statement
// or
if (Boolean-expression) 
  	Statement
else
	Statement
	
// or
if (Boolean-expression)
  	Statement
else if (Boolean-expression)
  	Statement
else
	Statement
```

布尔表达式必须产生一个布尔结果，statement指用分号结尾的简单语句，或复合语句。

## 迭代

while、do-while、for用来控制循环，有时将它们划分为迭代语句。语句会重复执行，知道起控制作用的布尔表达式得到“假“的结果为止。

### while

```java
while(Boolean-expression)
  Statement
```

### do-while

```java
do
  statement
while(Boolean-expression)
```

do-while中的语句至少会执行一次。

### for

```java
for (initialization; Boolean-expression; step)
  statement
```

### 逗号操作符

逗号操作符和逗号分隔符是不同的，逗号分隔符用来分隔函数的不同参数。Java里唯一用到逗号操作符的地方就是for循环的控制表达式。在控制表达式的初始化和步进控制部分，可以使用一系列由逗号分隔的语句，而且那些语句均会被独立执行。

通过逗号操作符，可以在for语句内定义多个变量，但是它们必须具有相同的类型。

```java
public class CommaOperator {
    public static void main(String args[]) {
        for (int i = 1, j = i + 10; i < 5; i++, j = i*2) {
            System.out.println("i = " + i + " j = " + j);
        }
    } 
} /** Output
i = 1 j = 11
i = 2 j = 4
i = 3 j = 6
i = 4 j = 8
*/
```

## foreach语法

Java SE5引入了一种新的更加简洁的for语法用于数组和容器。

```java
import java.util.*;
public class ForEachSample {
    public static void main(String args[]) {
        Random rand = new Random(47);
        float f[] = new float[10];

        for (int i=0; i < f.length; ++i) {
            f[i] = rand.nextFloat();
        }

        for (float x : f) {
            System.out.println(x);
        }

        for (char c : "Hello, World!".toCharArray()) {
            System.out.print(c + " ");
        }
    }
} /**Output
0.72711575
0.39982635
0.5309454
0.0534122
0.16020656
0.57799757
0.18847865
0.4170137
0.51660204
0.73734957
H e l l o ,   W o r l d !
*/
```

foreach还可以用于任何Iterable对象。

## return

Java中有多个关键字表示无条件分支，它们只是表示这个分支无需任何测试即可发生。这些关键词包括return、break、continue和一种与其他语言中的goto类似的跳转到标号语句的方式。

## break和continue

在任何迭代语句的主体部分，都可用break和continue控制循环的流程。

默认情况下，break和continue控制最内层循环，可以使用标签来控制外层循环。

标签是后面跟有冒号的标识符，比如：`lable1:` 。

在Java中，标签起作用的唯一的地方刚好是在迭代语句之前。

```java
public class LabelControl {
    public static void main(String args[]) {
        int i = 0;
        outer:
        for (; true; i++) {
            inner:
            for (; i < 10; i++) {
                System.out.println("i = " + i);
                if (i == 2) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("break");
                    break;
                }
                if (i == 7) {
                    System.out.println("continue outer");
                    continue outer;
                }
                if (i == 8) {
                    System.out.println("break outer");
                    break outer;
                }
                for (int k=0; k < 5; ++k) {
                    if (k == 3) {
                        System.out.println("continue inner");
                        continue inner;
                    }
                }
            }
        }
    }
} /* Output 
i = 0
continue inner
i = 1
continue inner
i = 2
continue
i = 3
break
i = 4
continue inner
i = 5
continue inner
i = 6
continue inner
i = 7
continue outer
i = 8
break outer
*/
```

规则：

- 一般的cotinue会退回最内层循环的开头，并继续执行
- 带标签的continue会到达标签的位置，并重新进入紧接在那个标签后面的循环
- 一般的break会中断并跳出当前循环
- 带标签的break会中断并跳出标签所指的循环

**在Java里需要使用标签的唯一理由就是因为有循环嵌套存在，并且想从多层嵌套中break或continue。**

## switch

switch有时也被划分为一种选择语句。

```java
import java.util.*;

public class SwitchExample {
    public static void main(String[] args) {
        Random rand = new Random(47);
        for (int i=0; i < 100; ++i) {
            int c = rand.nextInt(26) + 'a';
            System.out.print((char)c + ", " + (int)c + ": ");
            switch(c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    System.out.println("vowel");
                    break;
                case 'y':
                case 'w':
                    System.out.println("Sometimes a vowel");
                    break;
                default: System.out.println("consonant");
            }
        }
    }
} /** Output
y, 121: Sometimes a vowel
n, 110: consonant
z, 122: consonant
b, 98: consonant
r, 114: consonant
n, 110: consonant
y, 121: Sometimes a vowel
g, 103: consonant
...
*/
```

若省略case后的break，则会继续执行后面的case语句，直到遇到一个break为止。

enum可以和switch协调工作。

case语句能够堆叠在一起，为一段代码形成多重匹配。

switch支持匹配的6中类型：

- byte
- short
- char
- int
- enum（Java SE5）
- String（Java SE7）