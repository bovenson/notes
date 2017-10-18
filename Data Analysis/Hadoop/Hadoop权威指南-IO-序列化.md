# Hadoop权威指南: I/O操作序 - 列化

[TOC]

## 序列化和反序列化

**序列化** 是指将结构化对象转化为字节流以便在网络上传输或写到磁盘进行永久储存的过程

**反序列化** 是指将字节流转回结构化对象的逆过程

序列化在分布式数据处理的两大领域经常出现:**进程间通讯**和**永久存储**

## Hadoop中的序列化

- 在Hadoop中,系统中多个节点上进程间的通信是通过"远程过程调用(remote procedure call, RPC)"是实现的.
- RPC协议将信息序列化成二进制流后发送到远程节点, 远程节点接着将二进制流反序列化成原始消息
- RPC序列化格式四大理想属性:
- **紧凑**: 高效使用储存空间
- **快速**: 读写数据的额外开销比较小
- **可扩展**: 可以透明得读取老数据
- **支持互操作**: 可以使用不同语言读写永久储存的数据
- Hadoop使用自己的序列化格式`Writable`, 它绝对紧凑,速度快,但不太容易用Java以外的语言进行扩展和使用
- Avro是克服了Writable部分不足的序列化系统

## Writable接口

`Writable`接口定义了两个方法

- 一个将其状态写入`DataOutput`二进制流
- 另一个从`DataOutput`二进制流读取状态

```java
package org.apache.hadoop.io;
import java.io.DataOutput;
import java.io.DataInput;
import java.io.IOExcepion;

public interface Writable {
    void write(DataOutput out) throws IOExcepiont;
  	void readFileds(DataInput in) throws IOException;
}
```

### Writable接口示例

#### 序列化

```java
IntWritable writable = new IntWritable();
writable.set(163);
```

或者:

```java
IntWritable writable = new IntWritable(163);
```

**为了检查IntWritable的序列化形式,我们在`java.io.DataOutputStream`(`java.io.Dataoutput`的一个实现)中加入了一个帮助函数来封装`java.io.ByteArrayOutputStream`, 以便在序列化流中捕捉字节 **

```java
public static byte[] serialize(Writable writable) throws IOException {
	ByteArrayOutputStream out = new ByteArrayOutputStream();
  	DataOutputStream dataOut = new DataOutputStream(out);
  	writable.write(dataOut);
  	dataOut.close();
  	return out.toByteArray();
}
```

每个字节是按照大端顺序写入的(按照`java.io.DataOutput`接口中的声明,最重要的字节先写到流)

#### 反序列化

```java
public static byte[] deseralize(Writable writable, byte[] bytes) throws  IOException {
    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
  	DataInputStream dataIn = new DataInputStream(in);
  	writable.readFileds(dataIn);
  	dataIn.close();
  	return bytes;
}
```

调用`deserialize()`方法从我们刚才写入的输出数据中读取数据

```java
IntWritable newWritable = new IntWritale();
deserialize(newWritable, bytes);
```

最后,通过`get()`方法获取原始值:

`newWritable.get()`

### WritableComparable接口和comparator

`IntWritable`接口实现了原始`WritableComarable`接口:

```java
package org.apache.hadoop.io;
public interface WritableComparable<T> extends Writable, Comparable<T> { }
```

对于MapReduce来说, 类型比较非常重要

Hadoop提供的一个优化接口是继承自Java Comparator的`RawComparator`接口:

```java
package org.apache.hadoop.io;
import java.util.Comparator;
public interface RawComparator<T> extends Comparator<T> {
  	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2);
}
```

**该接口允许其实现直接比较数据流中的记录,无需先把数据流反序列化为对象, 避免了新建对象的额外开销**

`WritableComparator`是对继承自`WritableComparable`类的`RawComparator`类的一个通用实现,它提供两个主要功能

-   第一, 提供了对原始`compare()`方法的一个默认实现,该方法能够反序列化将在流中进行比较的对象,并调用对象的`compare()`方法
-   第二, 它充当的是`RawCompatator`实例的工厂(已注册`Writable`的实现)

两个功能示例如下:

首先获得`IntWritable`的`comparator`, 直接调用:

`RawComparator<IntWritable> comparator = WritableComparator.get(IntWritable.class);`

(第一个功能, 这个`comparator`可以用于比较两个`IntWritable`对象):

```java
IntWritable w1 = new IntWritable(163);
IntWritable w2 = new IntWritable(67);
System.out.println(comparator.compare(w1, w2))
```

(第二个功能,比较序列化对象):

```java
byte[] b1 = serialize(w1);
byte[] b2 = serialize(w2);
System.out.println(comparator.compare(b1, 0, b1.length, b2, 0, b2.length));
```



## Writable类

-   Hadoop自带的`org.apache.hadoop.io`包中有广泛的`Writable`类可供选择

### Java基本类型的Writable封装器

| Java基本类型 | Writable实现      | 序列化大小(字节) |
| -------- | --------------- | --------- |
| boolean  | BooleanWritable | 1         |
| byte     | ByteWritable    | 1         |
| short    | ShortWritable   | 2         |
| int      | IntWritable     | 4         |
|          | VintWritable    | 1-5       |
| float    | FloatWritable   | 4         |
| long     | LongWritable    | 8         |
|          | VlongWritable   | 1-9       |
| double   | DoubleWritable  | 8         |

### Text类型

-   **`Text`是针对UTF-8序列的`Writable`类**
-   `Text`使用标准UTF-8编码

#### 索引

Text类和Java String类之间存在一定的差别.

-   对Text类的索引是根据编码后字节序列中的位置实现的,并非字符串中的Unicode字符, 也不是Java char的编码单
-   对于ASCII字符串, 这三个索引位置的概念是一致的

**charAt()方法的使用**

```java
Text t = new Text("hadoop");
assertThat(t.getLength(), is(6));
assertThat(t.getBytes().length, is(6));

assertThat(t.charAt(2), is ((int)'d'));
assertThat("Out of bounds", t.charAt(100), is(-1));
```

注意`charAt()`方法返回的是一个表示Unicode编码位置的int类型值, 而String返回一个char类型值

Text还有一个`find()`方法,该方法类似于String的`indexOf()`方法:

```java
Text t = new Text("hadoop");
assertThat("Find a substring", t.find("do"), is(2));
assertThat("Finds first 'o'", t.find("o"), is(3));
assertThat("Find 'o' from position 4 or later", t.find("o", 4), is(4));
assertThat("No match", t.find("pig"), is(-1));
```

#### Test与String的区别

所有字符(除了U+10400, 它是一个候补字符,需要两个Java char表示,称为字符代理对)都可以使用单个Java char类型来表示.当一个字符需要多个字节来编码时,Text和String的区别就会显现

##### Unicode字符表

| Unicode编码点 | U+0041    | U+00DF        | U+6711     | U+10400                        |
| ---------- | --------- | ------------- | ---------- | ------------------------------ |
| 名称         | A(拉丁大写字母) | 拉丁小写字母SHARP S | 无(统一表示的汉子) | DESERET CAPITAL LETTER CLONG I |
| UTF-8编码单元  | 41        | c39f          | e69db1     | F0909080                       |
| Java表示     | \u0041    | \u00DF        | \u6771     | \uD801\uDC00                   |

##### 验证String和Text的差异性测试

```java
import org.apache.hadoop.io.Text;
import org.junit.Test;
import java.io.UnsupportedEncodingException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringTextComparisonTest {
    @Test
    public void string() throws UnsupportedEncodingException {
        String s = "\u0041\u00DF\u6771\uD801\uDC00";

        assertThat(s.length(), is(5));
        assertThat(s.getBytes("UTF-8").length, is(10));

        assertThat(s.indexOf("\u0041"), is(0));
        assertThat(s.indexOf("\u00DF"), is(1));
        assertThat(s.indexOf("\u6771"), is(2));
        assertThat(s.indexOf("\uD801\uDC00"), is(3));

        assertThat(s.charAt(0), is('\u0041'));
        assertThat(s.charAt(1), is('\u00DF'));
        assertThat(s.charAt(2), is('\u6771'));
        assertThat(s.charAt(3), is('\uD801'));
        assertThat(s.charAt(4), is('\uDC00'));

        assertThat(s.codePointAt(0), is(0x0041));
        assertThat(s.codePointAt(1), is(0x00DF));
        assertThat(s.codePointAt(2), is(0x6771));
        assertThat(s.codePointAt(3), is(0x10400));
    }

    @Test
    public void text() {
        Text t = new Text("\u0041\u00DF\u6771\uD801\uDC00");

        assertThat(t.getLength(), is(10));

        assertThat(t.find("\u0041"), is(0));
        assertThat(t.find("\u00DF"), is(1));
        assertThat(t.find("\u6771"), is(3));
        assertThat(t.find("\uD801\uDC00"), is(6));

        assertThat(t.charAt(0), is(0x0041));
        assertThat(t.charAt(1), is(0x00DF));
        assertThat(t.charAt(3), is(0x6771));
        assertThat(t.charAt(6), is(0x10400));
    }
}
```

-   测试证明,String的长度是其所含char编码单元的个数,但Text对象的长度却是其UTF-8编码的字节数(10=1+2+3+4)
-   String类的`indexOf()`方法返回`char`编码单元中的索引位置,`Text`类的`find()`方法则返回字节偏移量

#### 遍历Text

```java
import org.apache.hadoop.io.Text;

import java.nio.ByteBuffer;

public class TextIterator {
    public static void main(String args[]) {
        Text t = new Text("\u0041\u00DF\u6771\uD801\uDC00");

        ByteBuffer buf = ByteBuffer.wrap(t.getBytes(), 0, t.getLength());
        int cp;
        while(buf.hasRemaining() && (cp=Text.bytesToCodePoint(buf)) != -1) {
            System.out.println(Integer.toHexString(cp));
        }
    }
}
```

#### 可变性

与`String`想比,`Text`的另一个区别在于它是可变的,可以通过调用其中一个`set()`方法来重用Text实例

```java
Text t = new Text("hadoop");
t.set("pig");
assertThat(t.getLength(), is(3));
assertThat(t.getBytes().length, is(3));
```



### BytesWritable

`BytesWritable`是对二进制数据数组的封装.它的序列化为一个指定所含数据字节数的整数域(4字节),后跟数据内容本身.

```java
BytesWritable b = new BytesWritable(new byte[]{3, 5});
byte[] bytes = serialize(b);
assertThat(StringUtils.byteToHexString(bytes), is("000000020305"));
```

-   `BytesWritable`是可变的,可以通过`set()`方法进行修改

-   `BytesWirtable`类的`getBytes()`方法返回的字节数组长度是容量, 可能无法体现所储存数据的实际大小

-   可以通过`getLength()`方法来确定`BytesWritable`变量的大小

    ```java
    b.setCapacity(11);
    assertThat(b.getLength, is(2));
    assertThat(b.getBytes().length, is(11));
    ```

### NullWritable

-   `NullWritable`是`Writable`的特殊类型,它的序列化长度为0
-   充当占位符,不从数据流中读取数据,也不写入数据
-   是不可变的单实例类型,通过调用`NullWritable.get()`方法可以获取



### ObjectWritable和GenericWritable

#### ObjectWritable

`ObjectWritable`是对Java基本类型(`String, enum, Writable, null或这些类型的数组`)的一个通用封装,在Hadoop RPC中用于对方法的参数和返回类型进行封装和解封装.

#### GenericWritable

-   使用静态类型的数组,并使用对序列化后的类型的引用加入位置索引来提高性能
-   适用封装的类型比较少,而且能够提前知道

### Writable集合类

`org.apache.hadoop.io`包中一共有6个`Writable`集合类:`ArrayWritable,ArrayPrimitiveWritable,TwoDArrayWritable,MapWritable,SortedMapWritable和EnumMapWritable`

-   `ArrayWritable`和`TowDArrayWritable`是对`Writable`的数组和两维数组的实现,所有元素必须是同一类型的实例,如下

    `ArrayWritable writable = new ArrayWritable(Text.class);`

-   `ArrayPrimitiveWritable`是对`java`基本数组类型的一个封装,调用`set()`方法时可以识别相应组件类型,因此无需通过继承该类来设置类型

-   `MapWritable`和`SortedMapWritable`分别实现了`java.util.Map<Writable, Writable>`和`java.util.SortedMap<WritableComparable, Writable>`

#### 实现定制的Writable集合

-   可以完全控制二进制表示和排序顺序

##### 储存一对Text对象的Writable

```java
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TextPair implements WritableComparable<TextPair> {

    private Text first;
    private Text second;

    public TextPair() {
        set(new Text(), new Text());
    }

    public TextPair(String first, String second) {
        set(new Text(first), new Text(second));
    }

    public TextPair(Text first, Text second) {
        set(first, second);
    }

    public void set(Text first, Text second) {
        this.first = first;
        this.second = second;
    }

    public Text getFirst() {
        return first;
    }

    public Text getSecond() {
        return second;
    }

    @Override
    public int compareTo(TextPair tp) {
        int cmp = first.compareTo(tp.first);
        if (cmp != 0) {
            return cmp;
        }
        return second.compareTo(tp.second);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        first.write(out);
        second.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        first.readFields(in);
        second.readFields(in);
    }

    @Override
    public int hashCode() {
        return first.hashCode() * 163 + second.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TextPair) {
            TextPair tp = (TextPair) o;
            return first.equals(tp.first) && second.equals(tp.second);
        }
        return false;
    }

    @Override
    public String toString() {
        return first + "\t" + second;
    }
}
```

##### 用于比较TextPair字节表示的RawComparator

```java
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableUtils;

import java.io.IOException;

public class Comparator extends WritableComparator {
    private static final Text.Comparator TEXT_COMPARATOR = new Text.Comparator();

    public Comparator() {
        super(TextPair.class);
    }

    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
        try {
            int firstL1 = WritableUtils.decodeVIntSize(b1[s1]) + readVInt(b1, s1);
            int firstL2 = WritableUtils.decodeVIntSize(b2[s2]) + readVInt(b2, s2);
            int cmp = TEXT_COMPARATOR.compare(b1, s1, firstL1, b2, s2, firstL2);
            if (cmp != 0) {
                return cmp;
            }
            return TEXT_COMPARATOR.compare(b1, s1+firstL1, l1-firstL1, b2, s2+firstL2, l2-firstL2);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static {
        WritableComparator.define(TextPair.class, new Comparator());
    }
}
```

-   `firstL1`和`firstL2`表示两个每个字节流中第一个Text字段的长度
-   `WritableUtils`的`decodeVIntSize()`方法返回变长整数的长度
-   `readVInt()`返回编码值

##### 定制的RawComparator用于比较TextPair对象字节表示的第一个字段

```java
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableUtils;

import java.io.IOException;

public class FirstComparator extends WritableComparator {
    private static final Text.Comparator TEXT_COMPARATOR = new Text.Comparator();
    public FirstComparator() {
        super(TextPair.class);
    }

    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
        try {
            int firstL1 = WritableUtils.decodeVIntSize(b1[s1]) + readVInt(b1, s1);
            int firstL2 = WritableUtils.decodeVIntSize(b2[s2]) + readVInt(b2, s2);
            return TEXT_COMPARATOR.compare(b1, s1, firstL1, b2, s2, firstL2);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        if (a instanceof TextPair && b instanceof TextPair) {
            return ((TextPair) a).getFirst().compareTo(((TextPair) b).getFirst());
        }
        return super.compare(a, b);
    }
}
```

## 序列化框架

-   尽管大多是MapReduce程序使用的都是Writable类型的键值对,但这不是MapReduce API强制使用的.事实上,可以使用任何类型,只要能有一种机制对每个类型与其二进制表示来回转换
-   为了支持上述机制,Hadoop有一个针对可替换序列化框架的API.序列化框架用一个Serialization实现(`org.apache.hadoop.io.serializer`包)来实现
-   `Serialization`对象定义了从类型到Serializer实例(将对象转换为字节流)和Deserializer实例的映射方式
-   将`io.serialization`属性设置为一个逗号分隔的类名列表,即可注册Serialization实现,它的默认值包括`org.apache.hadoop.io.serizlizer.WritableSerialization`和Avro指定序列化和反序列化类, 这意味着只有Writable对象和Avro对象才可以在外部序列化和反序列化
-   Hadoop包含一个名为`JavaSerialization`的类,该类使用Java Object Serialization.一般不用, 不满足先前列出的序列化格式标准:精简,快速,可扩展,支持互操作

### 序列化IDL

-   接口定义语言(Interface Description Language, IDL)以不依赖于具体语言的方式进行声明



## Avro

