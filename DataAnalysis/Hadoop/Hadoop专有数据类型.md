# Writable 和 WritableComparable接口
## Writable接口
* ** Writable接口的主要目的是,当数据在网络上传输或从硬盘读写时,提供数据的序列化和反序列化机智 **

* ** 所有用作mapper或reducer输入或输出的数据类型都必须实现这个接口 **

## Comparable接口
* ** 用作键的数据有着更为严格的要求,除实现Writable接口之外,它必须实现标准Java中的Comparable接口 **
* compare方法的返回值为-1(小于,<),0(=),1(大于>)
* 作为一个方便使用的接口,Hadoop在org.apache.hadoop.io包里提供了一个WritableComparable接口,定义如下代码所示.
`public interface WritableComparable extends Writable, Comparable { }`

# wrapper类
** Hadoop提供了包装Java原始类型并实现了WritableComparable的类,它们被放置在org.apache.hadoop.io包下.这些包装类如下. **
## 原始包装类

** 这些类在概念上与原始包装类相似.它们保持一个原始值,该值既可以在创建类的时候创建,也可以通过setter方法设置 **
** 这些类有: **
* BooleanWritable
* ByteWritable
* DoubleWritable
* FloatWritable
* IntWritable
* LongWritable
* VIntWritable(可变长度的整数类型)
* VLongWritable(可变长度的长整数类型)

## 数组包装类

** 这些类为其他Writable对象数组提供了可写封装. ** 例如这些类的实例可以储存IntWritable类型的数组,却不能储存原始的整型(int)数组.** 这些类需要继承Writable类 **
** 这些类如下所示. **
* ArrayWritable
* TwoDArrayWritable

## Map包装类
** 这些类允许使用java.util.Map接口作为键或者值,他们被定义为`Map<Writable,Writable>` 并有效管理部分内部运行时类型检查. ** 这就意味着弱化了编译类型检查
** 如下所示. **
* AbstractMapWritable(这是其他具体的Writable map包装类的基类)
* MapWritable(通用的map包装类,将Writable键映射为Writable值)
* SortedMapWritable(MapWritable类的一个特殊实现,同时实现了SortedMap接口)




