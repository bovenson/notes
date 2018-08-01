---
title: Java 字符串
tags: Java, String
---

# 概述

- String对象是不可变的
- 每当把String对象作为方法的参数/返回值时, 都会复制一份引用, 而该引用所指的对象其实一直待在单一的物理位置上

# String上的操作

| 方法                               | 参数, 重载版本                                 | 应用                                       | 示例   |
| -------------------------------- | ---------------------------------------- | ---------------------------------------- | ---- |
| 构造器                              | 重载版本: 默认版本, String, StringBuilder, StringBuffer, char数组, byte数组 | 创建String对象                               |      |
| `length()`                       |                                          | String中字符个数                              |      |
| `charAt()`                       | Int索引                                    | 取得String中该索引位置上的char                     |      |
| `getChars()`, `getBytes()`       | 要复制部分的起点和终点的索引, 复制的目标数组, 目标数组的起始索引       | 复制char或byte到一个目标数组中                      |      |
| `toCharArray()`                  |                                          | 生成一个char[], 包含String的所有字符                |      |
| `equals()`                       | 与之进行比较的字符串                               | 比较两个String的内容是否相同                        |      |
| `compareTo()`                    | 与之进行比较的字符串                               | 按字典顺序比较String的内容, 比较结果为负数, 零或正数. 注意, 大小写不等价 |      |
| `contains()`                     | 要搜索的CharSequence                         | 如果该String对象包含参数的内容, 则返回true              |      |
| `contentEquals()`                | 与之进行比较的CharSequence或StringBuffer         | 如果该String与参数的内容完全一致则返回true               |      |
| `equalsIgnoreCase()`             | 与之进行比较的String                            | 忽略大小写, 如果两个String的内容相同则返回true            |      |
| `regionMatcher()`                | 该String的索引偏移量, 另一个String及其索引偏移量, 要比较的长度. 重载版本增加了忽略大小写 | 返回boolean结果, 以表明所比较区域是否相等                |      |
| `startsWith()`                   | 可能的起始String. 重载版本在参数中增加了偏移量              | 返回boolean结果, 以表明该String是否以此参数起始          |      |
| `endsWith()`                     | 该String可能的后缀String                       | 返回boolean结果, 以表明此参数是否是该字符串的后缀            |      |
| `indexOf()`, `lastIndexOf()`     | 重载版本包括: char, char与起始索引, String, String与起始索引 | 如果该String并包含此参数, 就返回-1; 否则返回此参数在String中的起始索引. lastIndexOf()是从后向前搜索 |      |
| `substring()` `subSequence()`    | 重载版本: 起始索引; 起始索引+终点坐标                    | 返回一个新的String, 以包含参数指定的子字符串               |      |
| `concat()`                       | 要连接的String                               | 返回一个新的String对象, 内容为原始String连接上参数String   |      |
| `replace()`                      | 要替换掉的字符, 用来进行替换的新字符. 也可以用一个CharSequence来替换另一个CharSequence | 返回替换字符后的新String对象, 如果没有替换发生, 则返回原始的String对象 |      |
| `toLowerCase()`, `toUpperCase()` |                                          | 将字符的大小写改变后, 返回一个新String对象. 如果没有发生改变, 则返回原始的String对象 |      |
| `trim()`                         |                                          | 将String两端的空白字符删除后, 返回一个新的String对象. 如果没有改变发生, 则返回原始的String对象 |      |
| `valueOf()`                      | 重载版本: Object; char[]; char, 偏移量, 字符个数; boolean; char; int; long; float; double | 返回一个表示参数内容的String                        |      |
| `intern()`                       |                                          | 为每个唯一的字符序列生成一个且仅生成一个String引用             |      |