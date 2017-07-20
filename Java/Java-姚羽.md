[TOC]

# Java语言程序设计

## Java语言初探

### Java的流行

#### 三层含义

- 编程语言
- 开发环境
- 应用环境

#### 最大特点

**"跨平台"**

### Java语言的特点

##### 简单

##### 跨平台

##### 面向对象

##### 多线程

##### 半编译半解释

| 项目   | 备注                               |
| ---- | -------------------------------- |
| 编译方式 | C                                |
| 解释方式 | BASIC                            |
| 编译流程 | 源代码->编译->字节码->字节码解释程序->对应平台的机器语言 |

##### 安全及稳定

- 异常处理

- 内存垃圾收集机制

  程序只能访问被许可的内存

- 无显式指针

  内存分配交给系统

- 字节码检验器

  在解释前,以某种规则验证字节码的安全性

#### Java与C语言

##### 内存的分配

​	Java动态分配内存. 使用运算符`new`, 由Java系统自动分配及扫描内存, 收集长期不用的"垃圾内存". 程序员无需考虑内存管理.

​	C和C++程序员对于内存问题的处理. C使用`malloc`和`free`,C++使用`new`和`delete`. 如果错误释放资源, 可能会造成死机; 如果长期不释放资源, 可能会造成内存资源耗尽.

##### Java中通过在某个类中定义公用静态变量完成全局变量的功能

```java
Class GlobalVar {
	public static global_var;
  	......
}
```

​	而在C语言中, 全局变量不进行封装, 可能会造成死机.

##### Java使用`try ... catch ...`机制进行出错处理

##### Java不适用头文件

##### Java不支持宏定义, 使用final定义常量

##### 通过接口支持多重继承

##### 同种数据类型分配固定长度

| 语言   | 类型   | PC     | VAX-11 |
| ---- | ---- | ------ | ------ |
| C    | int  | 32 bit | 32 bit |
| Java | int  | 16 bit | 32 bit |

##### 没有显式指针

### Java语言的工作机制

#### JVM

Java虚拟机, 其抽象构件有:

-   指令集: 独立于平台的字节码
-   寄存器组: 程序计数器, 堆栈指针, 运行环境指针, 变量指针
-   类文件: 独立于平台
-   堆栈: 传递参数和返回运行结果
-   垃圾收集器: 收集不再使用的内存片段
-   存储区: 存放字节码

### Java储存区

| 储存区  |                   |
| ---- | ----------------- |
| 寄存器  | 最快的存储区            |
| 栈    | 存放基本类型的变量数据和对象的引用 |
| 堆    | 存放所有`new`出来的对象    |
| 静态域  | 存放静态成员            |
| 常量池  | 存放字符串常量和基本类型常量    |

-   对象本身存放在堆或常量池中
-   基本变量对象存放在栈中

## Java语言的数据类型

### 基本数据类型

| 类型      | 位数   | 值范围                                      |
| ------- | ---- | ---------------------------------------- |
| Char    | 16   | \0000 ~ \uffff                           |
| Byte    | 8    | -128 ~ 127                               |
| Short   | 16   | -32768 ~ 32767                           |
| Int     | 32   | -2147483648 ~ 2147483647                 |
| Long    | 64   | -9223372036854775808 ~ 9223372036854775807 |
| Float   | 32   | 1.40239846E-45 ~ 3.40282347E+38          |
| Double  | 64   | 4.94065645841246544E-324 ~ 1.79769313486231570E+308 |
| Boolean | 1    | true, false                              |

### 类

#### 类的定义

```java
[public] class ClassName {
 	// 变量及成员方法.
}
```

#### 类,对象及句柄的关系

-   类是对象的抽象, 对象是类的具体实例.
-   类是抽象的, 不占用内存, 而对象是具体的, 占有内存空间.
-   在程序设计中, 句柄是一种特殊的智能指针. 句柄与普通指针的区别在于, 指针包含的是引用对象的内存地址, 而句柄则是由系统所管理的引用标识, 该标识可以被系统重新定位到一个内存地址上. 这种间接访问对象的模式增强了系统对引用对象的控制.

##### 问题一: 类不占用内存怎么生成对象那?

答案是从外存(磁盘等)加载. 使用到类中的内容时会从外存加载, 有如下情况:

- 创建对象
- 使用类中的静态成员
- 在命令中运行,例如`java HelloWorld`

##### 问题二: 类所有内容加载顺序和内存中的存放位置

静态代码块 -> 成员变量 -> 构造方法 -> 静态方法 .

静态方法需要被调用才会执行.

以`Person p = new Person("A");`为例分析.

-   在栈中生成句柄(引用)`p`

-   加载类文件

    ​	首先从外存中找到Person.class文件,并加载到内存,在加载类文件时,除了非静态成员变量,不会被加载,其它的都会被加载.

    | 成员            | 加载位置      |
    | ------------- | --------- |
    | 静态成员变量(类变量)   | 方法区的静态部分  |
    | 静态方法          | 方法区的静态部分  |
    | 非静态方法(包括构造函数) | 方法区的非静态部分 |
    | 静态代码块         | 方法区的静态部分  |
    | 构造代码块         | 方法区的静态部分  |

    ​	静态方法和非静态方法都会加载到方法区中, 要调用非静态方法需要先实例化该类的对象, 通过该对象调用非静态方法.

-   执行类中的静态代码块

    ​	如果有的话, 对Person.class类进行初始化.

-   开辟空间

    ​	在堆内存中开辟空间, 分配内存地址.

-   默认初始化

    ​	在堆内存中建立对象的特有属性, 并进行初始化.

-   显式初始化

    ​	对属性进行显式初始化.

-   构造代码块

    ​	执行类中的构造代码块, 对对象进行构造代码块的初始化.	

-   构造函数初始化

    ​	对对象进行对应的构造函数初始化.

-   将内存地址赋值给堆栈中的p

以`p.setName("B");`为例分析.

-   在栈内存中开辟`setName`方法的空间, 包含对象的引用`this`及临时变量`name`
-   将p的值赋值给`this`, `this`就指向了堆中调用该方法的对象.
-   将"B"赋值给临时变量`name`
-   将临时变量的值赋值给`this`的`name`

##### 问题三: 静态代码块, 构造代码和构造函数的区别

-   静态代码块: 用于给类初始化, 类加载时就会被加载执行, 只加载一次.
-   构造代码块: 用于给对象初始化, 只要建立对象, 该部分就会被执行, 且先于构造函数.
-   构造函数: 给对象初始化, 建立对象时, 选择相应的构造函数初始化对象.创建一个实例就加载一次.
-   三者加载执行顺序: 静态代码块->构造代码块->构造函数

### Java语言的数组

#### 声明

```java
int a[] = {1, 2, 3};
int a[][] = {{1, 2}, {3, 4}};
int a[];
double b[][];
int[] a = new int[6];
int[][] a = new int[3][4];
```

####  获取数组长度

`a.length();`

#### 使用`System.arraycopy()`复制数组

```java
import java.util.Arrays;

public class ArrayCopyTest {
    public static void main(String args[]) {
        int[] arrA = {1, 2, 3, 4, 5, 6};
        int[] arrB = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.arraycopy(arrA, 0, arrB, 0, arrA.length);
        System.out.println(Arrays.toString(arrA));  // [1, 2, 3, 4, 5, 6]
        System.out.println(Arrays.toString(arrB));  // [1, 2, 3, 4, 5, 6, 4, 3, 2, 1]
    }
}
```

### Java中的`==`运算符

```java
public class JavaEqualTest {
    public static void main(String args[]) {
        int a = 10, b = 10;
        System.out.println(a == b);         // true
        Integer c = new Integer(10), d = new Integer(10);
        System.out.println(c == d);         // false
        System.out.println(c.equals(d));    // true
        System.out.println(a == c);         // true
    }
}
```

### 使用改进的`for`循环

```java
for (int a: arrA) {
    System.out.print(a + " ");
}
```

## Java语言的基本输入输出

-   System类

    包含3个类数据域`System.out`, `System.out`, `System.err`

### 字符的输入

-   一般是以流的形式输入一个缓冲区

-   通常使用`FileReader`对象或`InputStreamReader`对象. 

    ```java
    BufferReader br = new BufferReader(new FileReader("in.txt"));
    ```

-   `InputStreamReader`对象将读入的字节数据流根据编码规则转化为字符数据流

-   `InputStreamReader`要与`BufferReader`对象的`readLine()`方法配合进行键盘输入

-   将字符串转为其他数据类型

    ```java
    boolean b = new Boolean(str).booleanValue();
    int i = Integer.parseInt(str);
    long l = Long.parseLong(str);
    double d = Double.parseDouble(str);
    ```

### 字符的输出

-   `OutputStreamWriter`将字符数据流转化为字节数据流输出

-   `OutputStreamWriter`使用`write()`方法,一般配合`BufferedWriter`

-   用`newLine()`函数写出系统规定的换行符

-   打印数据流

    ​	`PrintStream`: 将所有输入的字符都转换为字节, 转换规则为所在平台预设的字符编码方式

    ​	`PrintWriter`: 以字符为向导, 若要输出的数据为字符, 最好使用`PrintWriter`

-   重定向

    ```shell
    java PrintData >pnt			// 输出到打印机
    java PrintData >out.txt		// 输出到文件
    java PrintData <in.txt		// 从文件输入
    ```

## Java语言的方法

### 方法重载(overload)

```java
public class OverloadTest {
    static int square(int n) {
        return n * n;
    }
    static double square(double n) {
        return n * n;
    }
    static float square(float n) {
        return n * n;
    }

    public static void main(String args[]) {
        int a = 2;
        float f = 3.3f;
        double d = 3.3;
        System.out.println(square(a));  // 4
        System.out.println(square(f));  // 10.889999
        System.out.println(square(d));  // 10.889999999999999
    }
}
```

## 面向对象的程序设计



# END