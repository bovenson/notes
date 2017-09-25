---
title: javap - Java反编译工具
tags: Java, javap, 反编译
---

```java
// 示例代码
public class Concatenation {
	public static void main(String args[]) {
		String mango = "mango";
		String s = "abc" + mango + "def" + 47;
		System.out.println(s);
	}
}
```

```shell
### javap -c 
# -c 标志表示将生成JVM字节码
$ javac Concatenation.java 
$ java Concatenation
abcmangodef47
$ javap -c Concatenation
Compiled from "Concatenation.java"
public class Concatenation {
  public Concatenation();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #2                  // String mango
       2: astore_1
       3: new           #3                  // class java/lang/StringBuilder
       6: dup
       7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
      10: ldc           #5                  // String abc
      12: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      15: aload_1
      16: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      19: ldc           #7                  // String def
      21: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      24: bipush        47
      26: invokevirtual #8                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      29: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      32: astore_2
      33: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
      36: aload_2
      37: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      40: return
}
```

- 其中dup和invokevirtual语句相当于JVM上的汇编语句