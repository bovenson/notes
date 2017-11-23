---
title: Java命名规范及注释
---

[TOC]

#　规范

## 原则

- 名称只能由字母、数字、下划线、$符号组成
- 不能以数字开头
- 名称不用使用JAVA中的关键字



## 其他

| 类型   | 命名规范              | 实例            |
| ---- | ----------------- | ------------- |
| 包    | 全部小写，由域名定义        | `com.neu.szk` |
| 类    | 单词首字母大写           | `HelloWorld`  |
| 方法   | 第一个单词小写，后续单词首字母大写 | `drawImage`   |
| 常量   | 全部大写              | `MAX_LEN`     |



# 注释

Java有两种注释风格，多行注释`/**/`和单行注释`//`。对于连续注释内容，通常每一行都以一个“*”开头：

```java
/*
 * This is a comment
 * that continues
 * across lines.
 */
```

## 类注释

```java
/**
* FileName: Test.java
* 类说明
*
* @author ---
* @Date   ---
* @version ---
*/
```

## 属性注释

```java
/** 注释内容 **/
private String msg = null;
```

## 方法注释

```java
/**
* 类方法的详细使用说明
*
* @param 参数1 参数1的使用说明
* @return 返回结果的说明
* @throws 异常类型.错误代码 注明从此类方法中抛出异常的说明
*/
```

## 构造方法注释

```java
/**
* 构造方法的详细使用说明
*
* @param 参数1 参数1的使用说明
* @throws 异常类型.错误代码 注明从此类方法中抛出异常的说明
*/
```

## 方法内部注释

```java
// 注释
Color bgColor = Color.RED;
```

# 注释文档

javadoc是用于提取注释的工具，输出的是一个HTML文件。

## 语法

所用的javadoc命令都只能在"/**"注释中出现，和通常一样，注释结束于` */ `

使用javadoc的方式有两种：嵌入HTML，或使用“文档标签”。

独立文档标签是一些以"@"字符开头的命令，且要置于注释行的最前面（但是不算前导"*"）。而行内文档标签则可以出现在javadoc注释中的任何地方，它们也以"@"字符开头，但要包括在花括号内。

共有三种类型的注释文档，分别对应于注释位置后面的三种元素：类、域和方法。比如：

```java
//: object/Hello.java
/** A class document */
public class Hello {
    /** A field comment */
    public int i;

    /** A method comment */
    public void f() {}
}
```

**javadoc只能为public和protected成员进行文档注释**。private和包内可访问成员的注释会被忽略掉。

## 嵌入式HTML

```java
/**
 * <pre>
 *     System.out.println(new Date());
 * </pre>
 */

/**
 * You can <em>even</em> insert a list:
 * <ol>
 *     <li>Item one</li>
 *     <li>Item two</li>
 *     <li>Item three</li>
 * </ol>
 */
```

**不要嵌入标题标签（如\<h1\>）和\<hr\>**。

## 一些标签示例

- `@see`：引用其他类

  ```java
  @see classname
  @see full-qualified-classname
  @see full-qualified-classname#method-name
  ```

- `{@link pakage.class#member label}`：该标签与@see极其相似

- `{@docRoot}`：该标签产生到文档根目录的相对路径，用于文档树页面的显式超链接

- `{@inheritDoc}`：从当前类的最直接基类中继承相关文档到当前的文档注释中

- `@version`：`@version version-information`

- `@author`: `@author author-information`

- `@since`: 该标签允许指定程序代码最早使用的版本

- `@param`：该标签用于方法文档中，`@param parameter-name description`

- `@return`: `@return description`

- `@throw`: `@throws fully-qualified-class-name description`

- `@deprecated`: 用于之处一些旧特性已由改进的新特性所取代

示例：

```java
//: chapter02/HelloDate.java
import java.util.*;

/** The first Thinking in Java example program.
 * Displays a string and today's date.
 * @author Bruce Eckel
 * @author www.MindView.net
 * @version 4.0
 */
public class HelloDate {
    /** Entry point to class & application.
     * @param args array of string arguments
     * @throws exceptions No exceptions throw
     */
    public static void main(String args[]) {
        System.out.println("Hello, it's: ");
        System.out.println(new Date());
    }  
} /* Output: (55% match)
Hello, it's:
Thu Nov 23 16:46:09 CST 2017
*///:~
```



