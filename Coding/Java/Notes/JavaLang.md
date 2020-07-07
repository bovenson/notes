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

| 项目     | 备注                                                     |
| -------- | -------------------------------------------------------- |
| 编译方式 | C                                                        |
| 解释方式 | BASIC                                                    |
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

	Java动态分配内存. 使用运算符`new`, 由Java系统自动分配及扫描内存, 收集长期不用的"垃圾内存". 程序员无需考虑内存管理.
	
	C和C++程序员对于内存问题的处理. C使用`malloc`和`free`,C++使用`new`和`delete`. 如果错误释放资源, 可能会造成死机; 如果长期不释放资源, 可能会造成内存资源耗尽.

##### Java中通过在某个类中定义公用静态变量完成全局变量的功能

```java
Class GlobalVar {
	public static global_var;
  	......
}
```

	而在C语言中, 全局变量不进行封装, 可能会造成死机.

##### Java使用`try ... catch ...`机制进行出错处理

##### Java不适用头文件

##### Java不支持宏定义, 使用final定义常量

##### 通过接口支持多重继承

##### 同种数据类型分配固定长度

| 语言 | 类型 | PC     | VAX-11 |
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

| 储存区 |                                    |
| ------ | ---------------------------------- |
| 寄存器 | 最快的存储区                       |
| 栈     | 存放基本类型的变量数据和对象的引用 |
| 堆     | 存放所有`new`出来的对象            |
| 静态域 | 存放静态成员                       |
| 常量池 | 存放字符串常量和基本类型常量       |

-   对象本身存放在堆或常量池中
-   基本变量对象存放在栈中

## Java语言的数据类型

### 基本数据类型

| 类型    | 位数 | 值范围                                              |
| ------- | ---- | --------------------------------------------------- |
| Char    | 16   | \0000 ~ \uffff                                      |
| Byte    | 8    | -128 ~ 127                                          |
| Short   | 16   | -32768 ~ 32767                                      |
| Int     | 32   | -2147483648 ~ 2147483647                            |
| Long    | 64   | -9223372036854775808 ~ 9223372036854775807          |
| Float   | 32   | 1.40239846E-45 ~ 3.40282347E+38                     |
| Double  | 64   | 4.94065645841246544E-324 ~ 1.79769313486231570E+308 |
| Boolean | 1    | true, false                                         |

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

静态代码块 -> 成员变量 -> 构造方法 -> 静态方法 .

静态方法需要被调用才会执行.

以`Person p = new Person("A");`为例分析.

-   在栈中生成句柄(引用)`p`

-   加载类文件

    	首先从外存中找到Person.class文件,并加载到内存,在加载类文件时,除了非静态成员变量,不会被加载,其它的都会被加载.

    | 成员                     | 加载位置           |
    | ------------------------ | ------------------ |
    | 静态成员变量(类变量)     | 方法区的静态部分   |
    | 静态方法                 | 方法区的静态部分   |
    | 非静态方法(包括构造函数) | 方法区的非静态部分 |
    | 静态代码块               | 方法区的静态部分   |
    | 构造代码块               | 方法区的静态部分   |

    	静态方法和非静态方法都会加载到方法区中, 要调用非静态方法需要先实例化该类的对象, 通过该对象调用非静态方法.

-   执行类中的静态代码块

    	如果有的话, 对Person.class类进行初始化.

-   开辟空间

    	在堆内存中开辟空间, 分配内存地址.

-   默认初始化

    	在堆内存中建立对象的特有属性, 并进行初始化.

-   显式初始化

    	对属性进行显式初始化.

-   构造代码块

    	执行类中的构造代码块, 对对象进行构造代码块的初始化.	

-   构造函数初始化

    	对对象进行对应的构造函数初始化.

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

    包含3个类数据域`System.in`, `System.out`, `System.err`

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

- `OutputStreamWriter`将字符数据流转化为字节数据流输出

- `OutputStreamWriter`使用`write()`方法,一般配合`BufferedWriter`

- 用`newLine()`函数写出系统规定的换行符

- 打印数据流

  -   `PrintStream`: 将所有输入的字符都转换为字节, 转换规则为所在平台预设的字符编码方式
  -   `PrintWriter`: 以字符为向导, 若要输出的数据为字符, 最好使用`PrintWriter`

- 重定向

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

#　规范

## 原则

- 名称只能由字母、数字、下划线、$符号组成
- 不能以数字开头
- 名称不用使用JAVA中的关键字

## 其他

| 类型 | 命名规范                           | 实例          |
| ---- | ---------------------------------- | ------------- |
| 包   | 全部小写，由域名定义               | `com.neu.szk` |
| 类   | 单词首字母大写                     | `HelloWorld`  |
| 方法 | 第一个单词小写，后续单词首字母大写 | `drawImage`   |
| 常量 | 全部大写                           | `MAX_LEN`     |



## 注释

Java有两种注释风格，多行注释`/**/`和单行注释`//`。对于连续注释内容，通常每一行都以一个“*”开头：

```java
/*
 * This is a comment
 * that continues
 * across lines.
 */
```

### 类注释

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

### 属性注释

```java
/** 注释内容 **/
private String msg = null;
```

### 方法注释

```java
/**
* 类方法的详细使用说明
*
* @param 参数1 参数1的使用说明
* @return 返回结果的说明
* @throws 异常类型.错误代码 注明从此类方法中抛出异常的说明
*/
```

### 构造方法注释

```java
/**
* 构造方法的详细使用说明
*
* @param 参数1 参数1的使用说明
* @throws 异常类型.错误代码 注明从此类方法中抛出异常的说明
*/
```

### 方法内部注释

```java
// 注释
Color bgColor = Color.RED;
```

## 注释文档

javadoc是用于提取注释的工具，输出的是一个HTML文件。

### 语法

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

### 嵌入式HTML

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

### 一些标签示例

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

# String

- String对象是不可变的
- 每当把String对象作为方法的参数/返回值时, 都会复制一份引用, 而该引用所指的对象其实一直待在单一的物理位置上

## Operation

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

# 类型

```java
// 打印对象类型
obj.getClass().getName();

// 判断是否是某一个类型
boolean res = str instanceof String;
```

# Java 数组

```java
// 声明
String[] arr;
int iArr[];

// 分配空间
// iArr = {1, 2};   ERROR
// iArrN = {1, 2};  ERROR
iArr = new int[3];
iArrN = new int[3];

// 声明并分配空间
String[] arrN = new String[5];
int iArrN = new int[3];

// 声明并初始化
int iArr2[] = new int[]{1, 2, 3, 4, 5};
int iArr3[] = {1, 2, 3};
String sArr2[] = new String[]{"AA", "BB", "CC"};
String sArr3[] = {"AA", "BB"};

// Arrays.fill() 填充数组
Arrays.fill(sArrN, "DD");

// 长度
int length = sArr3.length;

// 遍历
for (int i=0; i < sArr3.length; ++i) {
  System.out.println(sArr3[i]);
}

for (int aIArr3 : iArr3) {
  System.out.println(aIArr3);
}

// 数组转成 String
String str = Arrays.toString(iArr3);
System.out.println(str);
System.out.println(str.getClass().getName());

// 转成 ArrayList
ArrayList<String> arrList = new ArrayList<String>(Arrays.asList(sArr3));

// 转成 List
List<String> list = Arrays.asList(sArr3);

System.out.println(Arrays.asList(sArr3));
System.out.println(Arrays.asList(sArr3).getClass().getName());

boolean res = str instanceof String;

System.out.println(res);

// 判断包含某一个值
System.out.println(Arrays.asList(sArr3).contains("AA"));    // true
System.out.println(Arrays.asList(sArr3).contains("CC"));    // false

// 转换成 set
Set<String> set = new HashSet<String>(Arrays.asList(sArr3));

// 排序
int[] iArr4 = {3, 7, 1, 2, 6};
Arrays.sort(iArr4); // Arrays.sort(iArr4, 2, 4); .sort(arr, fromIndex, toIndex);

// 复制数组
int[] iArr5 = Arrays.copyOf(iArr4, 10);
int[] iArr6 = Arrays.copyOfRange(iArr4, 1, 3);
for (int i : iArr5) {
  System.out.println(i);
}

// 比较数组
System.out.println(Arrays.equals(iArr4, iArr5));

// 去重 使用 set
int[] iArr7 = {1, 2, 3, 2, 2, 6, 5, 3, 4, 5, 9, 8, 0, 7, 4, 3, 2, 1, 9};
```

# 输入

## Scanner

### next 和 nextLine区别

- `next`: 一定要读取到有效字符后才可以结束输入，对输入有效字符之前遇到的空格键、Tab键或Enter键等结束符，next()方法会自动将其去掉，只有在输入有效字符之后，next()方法才将其后输入的空格键、Tab键或Enter键等视为分隔符或结束符。 **next()查找并返回来自此扫描器的下一个完整标记。完整标记的前后是与分隔模式匹配的输入信息，所以next方法不能得到带空格的字符串。**
- `nextLine`: 结束符只是Enter键，即nextLine()方法返回的是Enter键之前的所有字符，它是可以得到带空格的字符串的。

### scanner

```java
Scanner scanner;
Scanner s = new Scanner(System.in);
```

### 判断标准输入是否为空(可能忽略空白符)

```java
scanner.hasNext();
```

### 判断标准输入是否有下一行

```java
scanner.hasNextLine();
```

### 判断标准输入是否还有字符(包括空白符)

功能上等同于`hasNextLine`

```java
private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
private static final Pattern EMPTY_PATTERN = Pattern.compile("");

scanner.useDelimiter(EMPTY_PATTERN);
boolean result = scanner.hasNext();
scanner.useDelimiter(WHITESPACE_PATTERN);
return result;
```

### 读取一行

```java
public static String readLine() {
  String line;
  try {
      line = scanner.nextLine();
  } catch (NoSuchElementException e) {
      line = null;
  }
  return line;
}
```

### 读取一个字符

```java
private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
private static final Pattern EMPTY_PATTERN = Pattern.compile("");

public static char readChar() {
  try {
    scanner.useDelimiter(EMPTY_PATTERN);
    String ch = scanner.next();
    assert ch.length == 1 : "Internal (Std)In.readChar() error! Please contact the authors.";
    scanner.useDelimiter("WHITESPACE_PATTERN");
    return ch.charAt(0);
  } catch (NoSuchElementException e) {
    throw new NoSuchElementException("attempts to read a 'char' value from standard input, but there are no more tokens available");
  }
}
```

### 读取所有输入(返回String)

```java
static final Pattern EVERYTHIN_PATTERN = Pattern.compile("\\A");

public static String readAll() {
    if (!scanner.hasNextLine()) {
        return "";
    }
  	
  	String result = scanner.userDelimiter(EVERYTHING_PATTERN).next();
 	scanner.useDelimiter(WHITESPACE_PATTERN);
  	return result;
}
```

### 读取下一个有效输入

```java
public static String readString() {
    try {
        return scanner.next();
    } catch(NoSuchElementException e) {
        throw new NoSuchElementException("尝试从标准输入读取一个字符串, 但是没有可用有效输入.");
    }
}
```

### 读取int

```java
public static int readInt() {
    try {
        return scanner.readInt();
    } catch (InputMismatchException e) {
        String token = scanner.next();
      	throw new InputMismatchException("attempts to read an int value form standard input, but the next token is \"" + token + "\"");
    } catch (NoSuchElementException e) {
        throw new NoSuchElementException("attempts to read an int value from standard input, but there are no more tokens available");
    }
}
```

### 读取double

类似读取int

### 读取float

类似读取int

### 读取long

类似读取int

### 读取short

类似读取int

### 读取byte

类似读取int

### 读取boolean

```java
public static boolean readBoolean() {
    String token = readString();
  	if ("true".equalsIgnoreCase(token))
      return true;
  	if ("false".equalsIgnoreCase(token))
      return false;
  	if ("1".equals(token))
      return true;
  	if ("0".equals(token))
      return false;
  	throw new InputMismatchException("attampts to read a boolean value from standard input, but the next token is \"" + token + "\"");
}
```

### 读取所有有效输入(返回Sring[])

不包含空白符.

```java
public static String[] readAllStrings() {
    // we could use readAll.trim().split(), but that's not consistent
    // because trim() uses characters 0x00..0x20 as whitespace
    String[] tokens = WHITESPACE_PATTERN.split(readAll());
  	if (tokens.length == 0 || tokens[0].length() > 0)
      return tokens;
  	
  	// don't include first token if it is leading whitesapce
  	String[] decapitokens = new String[tokens.length-1];
  	for (int i=0; i < tokens.length - 1; ++i) {
        decapitokens[i] = tokens[i+1];
    }
  	return decapitokens;
}
```

### 读取所有行

```java
public static String[] readAllLines() {
	ArrayList<String> lines = new ArrayList<String>();
	while (hasNextLine()) {
		lines.add(readLine());
	}

	return lines.toArray(new String[lines.size]);
}
```

### 读取所有int

```java
public static int[] readAllInts() {
	String[] fields = readAllStrings();
	int[] vals = new int[fields.length];
	for (int i = 0; i < fields.length; ++i) {
		vals[i] = Integer.parseInt(fields[i]);
	}
	return vals;
}
```

### 读取所有double

类似读取所有int

### 读取所有double

类似读取所有int

# 标准输入输出

```java
// 第一种方式 使用BufferReader
import java.io.InputStreamReader;
import java.io.BufferedReader;
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

// 第二种方式 使用Scanner
import java.util.Scanner;
Scanner scanner = new Scanner(System.in);
```