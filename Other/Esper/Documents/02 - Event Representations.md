# 02 - Event Representations

这个章节意在区分模型和代表事件。

Esper使用术语“事件类型”来描述事件可用的类型信息。

应用程序可以在开始的时候配置事先定义好的事件类型，或者在运行时通过API或者EPL语法动态添加事件类型。

EPL语法`create schema`允许在运行时声明一个事件类型。

事件一条，一个动作或者状态改变引起的不可变的记录。事件属性捕获一个事件的状态信息。

在Epser中，一个事件可以被以下任意一个相关的Java对象表示。

| Java Class                               | Description                              |
| ---------------------------------------- | ---------------------------------------- |
| `java.lang.Object`                       | 任何带有getter方法符合JavaBean公约的POJO（plain-old java object，普通Java对象）。 |
| ` java.util.Map`                         | Map事件是`java.util.Map`接口的实现，每个map入口是一个恰当的值。 |
| ` Object[]`                              | Object数组，每个元素是一个恰当的值。                    |
| `org.apache.avro.generic.GenericData.Record` | Apache Avro events are `GenericData.Record` objects (Avro is a data serialization system with JSON and schema support) |
| ...                                      |                                          |



