---
title: Java输入输出
tags: Java输入输出
---

[TOC]

# 输入

## Scanner

#### next 和 nextLine区别

- `next`: 一定要读取到有效字符后才可以结束输入，对输入有效字符之前遇到的空格键、Tab键或Enter键等结束符，next()方法会自动将其去掉，只有在输入有效字符之后，next()方法才将其后输入的空格键、Tab键或Enter键等视为分隔符或结束符。 **next()查找并返回来自此扫描器的下一个完整标记。完整标记的前后是与分隔模式匹配的输入信息，所以next方法不能得到带空格的字符串。**
- `nextLine`: 结束符只是Enter键，即nextLine()方法返回的是Enter键之前的所有字符，它是可以得到带空格的字符串的。

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

#### 读取所有输入(返回String)

```java
public static String readAll() {
    if (!scanner.hasNextLine()) {
        return "";
    }
  	
  	String result = scanner.userDelimiter(EVERYTHING_PATTERN).next();
 	scanner.useDelimiter(WHITESPACE_PATTERN);
  	return result;
}
```

#### 读取下一个有效输入

```java
public static String readString() {
    try {
        return scanner.next();
    } catch(NoSuchElementException e) {
        throw new NoSuchElementException("尝试从标准输入读取一个字符串, 但是没有可用有效输入.");
    }
}
```

#### 读取int

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

#### 读取double

类似读取int

#### 读取float

类似读取int

#### 读取long

类似读取int

#### 读取short

类似读取int

#### 读取byte

类似读取int

#### 读取boolean

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

#### 读取所有有效输入(返回Sring[])

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

#### 读取所有行

```java
public static String[] readAllLines() {
	ArrayList<String> lines = new ArrayList<String>();
	while (hasNextLine()) {
		lines.add(readLine());
	}

	return lines.toArray(new String[lines.size]);
}
```

#### 读取所有int

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

