# Hello World

```g4
// Define a grammar called Hello
grammar Hello;
r  : 'hello' ID ;         // match keyword hello followed by an identifier
ID : [a-z]+ ;             // match lower-case identifiers
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
```
# 计算器

ANTLR在它的运行库中为两种树遍历机制提供支持。默认下ANTLR生成语法分析树和Listener接口，并在其中定义了回调方法，用于响应被内建的树遍历器的触发。

在Listener和Visitor机制之间最大的不同是: Listener方法被ANTLR提供的遍历器对象调用; 而Visitor方法必须显式的调用visit方法遍历它们的子节点，在一个节点的子节点上如果忘记调 用visit方法就意味着那些子树没有得到访问。

