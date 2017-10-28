---
title: Java输入输出
tags: Java输入输出
---

[TOC]

# 输入

## 标准输入

### scanner

```java
Scanner scanner;
```

#### 判断标准输入是否为空(可能忽略空白符)

```java
scanner.hasNext();
```

#### 判断标准输入是否有下一行

```java
scanner.hasNextLine();
```

#### 判断标准输入是否还有字符(包括空白符)

功能上等同于`hasNextLine`

```java
private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
private static final Pattern EMPTY_PATTERN = Pattern.compile("");

scanner.useDelimiter(EMPTY_PATTERN);
boolean result = scanner.hasNext();
scanner.useDelimiter(WHITESPACE_PATTERN);
return result;
```

#### 读取一行

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

#### 读取一个字符

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

