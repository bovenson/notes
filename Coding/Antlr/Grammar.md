# 语法词典

## 注释

```g4
Single Line
// -

Multi Line
/**
 *
 */
```

## 命名

Token以大写字母开头，解析规则以小写开头。首字符后，可以是大小写字母、数字、下划线。

```g4
ID, LPAREN, RIGHT_CURLY // token names/rules
expr, simpleDeclarator, d2, header_file // rule names
```

支持Unicode编码字符，比如中文文字。

## 常量

ANTLR不区分字符和字符串，所有的字符串常量，都使用单引号包裹，常量字符串中不包含正则表达式。常量字符串支持

## Actions

Actions是用目标语言编写的代码块。可以在一个语法中多次使用action，格式为大括号包围的任意文本。字符串和注释中的大括号无须转义。如果左右大括号能够平衡匹配，也无须对大括号进行转义。

Actions可以出现在：以@header 和 @members命名的action中、解析和语法规则中、exception catching specifications, attribute sections for parser rules (return values, arguments, and locals) and some rule element options (currently predicates)。

## Keywords

ANTLR保留字如下。

```
import, fragment, lexer, parser, grammar, returns,
locals, throws, catch, finally, mode, options, tokens
```

尽量不要使用rule作为关键字，此外应当排除目标语言的关键字。

# 语法结构

语法本质上是一个语法声明，后跟规则列表，具有以下一般形式。

```g4
/** Optional javadoc style comment */
grammar Name; ①
options {...}
import ... ;
 	
tokens {...}
channels {...} // lexer only
@actionName {...}
 	 
rule1 // parser and lexer rules, possibly intermingled
...
ruleN
```

语法 `X` 必须定义在以 `X.g4` 命名的文件内。可以以任意顺序定义 options、imports、 token specifications、actions。options、imports、 token specifications 最多可定义一次，除了Header和至少一个规则，其他都是可选的。规则基本格式如下。

```
ruleName : alternative1 | ... | alternativeN ;
```

**解析器规则（parser）名称必须以小写字母开头，而词法分析器（lexer）规则必须以大写字母开头。**

定义grammar时如果grammar头部没有前缀，那么可以定义 词法分析器 和 解析器规则 规则。

```g4
parser grammar Name;	// 只定义解析器
...

lexer grammar Name;		// 只定义语法分析器
...
```

只有语法分析器可以包含mode、自定义channels。

```g4
channels {
  WHITESPACE_CHANNEL,
  COMMENTS_CHANNEL
}
```

这些channels可以像枚举一样在语法分析器中使用。

```g4
WS : [ \r\t\n]+ -> channel(WHITESPACE_CHANNEL) ;
```

## Imports

ANTLR 支持导入其他文件，放在主文件的最后，因此导入文件定义不会覆盖主文件定义内容。

## Tokens

Tokens 是用于定于grammar需要的token类型，和语法分析器规则无关。

```g4
tokens { Token1, ..., TokenN }
```

通常情况下，tokens部分定义用于grammar中actions需要使用的token类型。

```g4
// explicitly define keyword token types to avoid implicit definition warnings
tokens { BEGIN, END, IF, THEN, WHILE }
 
@lexer::members { // keywords map used in lexer to assign token types
Map<String,Integer> keywords = new HashMap<String,Integer>() {{
	put("begin", KeywordsParser.BEGIN);
	put("end", KeywordsParser.END);
	...
}};
}
```

Tokens 只是定义一组tokens，添加到全局的集合中。

```g4
$ cat Tok.g4
grammar Tok;
tokens { A, B, C }
a : X ;
$ antlr4 Tok.g4
warning(125): Tok.g4:3:4: implicit definition of token X in parser
$ cat Tok.tokens
A=1
B=2
C=3
X=4
```

## 语法级别的Actions

现在，只有两个action定义用于语法规则之外，header和members。

对于组合语法，可以使用@parser::name 或者 @lexer::name 限制action应用在parser还是lexer。

```g4
grammar Count;
 
@header {
package foo;
}
 
@members {
int count = 0;
}
 
list
@after {System.out.println(count+" ints");}
: INT {count++;} (',' INT {count++;} )*
;
 
INT : [0-9]+ ;
WS : [ \r\t\n]+ -> skip ;
```

# 解析器规则

解析器包含一系列的解析器规则，可以定义在解析器语法或者组合语法中。Java程序通过调用ANTLR生成的规则函数，执行解析器。最基本的规则可以是一个规则名，紧跟单个选项，并以分号结尾。

```g4
/** Javadoc comment can precede rule */
retstat : 'return' expr ';' ;
```

规则可以有多个选项，并以|结尾。

```g4
operator:
 	stat: retstat
 	| 'break' ';'
 	| 'continue' ';'
 	;
```

选项可以是一系列的规则元素，或者为空。空选项可以让整个规则为可选。

```g4
superClass
 	: 'extends' ID
 	| // empty means other alternative(s) are optional
 	;
```

## Alternative Labels

使用 # 可为选项打标签，ANTLR会生成对用的监听器，可以更细粒度地监听解析树事件。要么所有的选项都有标签，要么都没有。

```g4
grammar T;
stat: 'return' e ';' # Return
 	| 'break' ';' # Break
 	;
e   : e '*' e # Mult
    | e '+' e # Add
    | INT # Int
    ;
```

ANTLR生成如下监听器。

```java
public interface AListener extends ParseTreeListener {
 	void enterReturn(AParser.ReturnContext ctx);
 	void exitReturn(AParser.ReturnContext ctx);
 	void enterBreak(AParser.BreakContext ctx);
 	void exitBreak(AParser.BreakContext ctx);
 	void enterMult(AParser.MultContext ctx);
 	void exitMult(AParser.MultContext ctx);
 	void enterAdd(AParser.AddContext ctx);
 	void exitAdd(AParser.AddContext ctx);
 	void enterInt(AParser.IntContext ctx);
 	void exitInt(AParser.IntContext ctx);
}
```

可以在多个选项使用相同的标签。

```g4
	e : e '*' e # BinaryOp
 	| e '+' e # BinaryOp
 	| INT # Int
 	;
```

对应生成的监听器如下。

```java
 	void enterBinaryOp(AParser.BinaryOpContext ctx);
 	void exitBinaryOp(AParser.BinaryOpContext ctx);
 	void enterInt(AParser.IntContext ctx);
 	void exitInt(AParser.IntContext ctx);
```

## 规则上下文对象

ANTLR生成可以访问和每个规则相关的上下文对象。对于只有一个引用的规则，ANTLR生成一个没有参数的方法。

```g4
inc : e '++' ;
```

ANTLR生成如下Context对象。

```java
public static class IncContext extends ParserRuleContext {
 	public EContext e() { ... } // return context object associated with e
 	...
}
```

ANTLR也支持多个引用的规则的上下文对象。

```g4
field : e '.' e ;
```

生成的Context对象如下。

```java
public static class FieldContext extends ParserRuleContext {
 	public EContext e(int i) { ... } // get ith e context
 	public List<EContext> e() { ... } // return ALL e contexts
 	...
}
```

如果另外一个规则，s，引用了 field，嵌套的action可以访问field匹配的规则e的列表。

```g4
s : field
 	{
 	List<EContext> x = $field.ctx.e();
 	...
 	}
;
```

## 规则元素标签

可以使用=符号来为规则元素设置标签，以向规则上下文对象中添加字段。

```g4
stat: 'return' value=e ';' # Return
 	| 'break' ';' # Break
 	;
```

这里的value是定义在其他位置的规则e的返回值的标签。便签编程对应解析树节点的字段。在这个实例中，value是 Return 选项的标签，因此value标签成为了ReturnContext的字段。

使用 += （标签列表）符号可以方便地跟踪多个token。比如，下面的创建了一个Token对象列表，来匹配简单的数组构造方法。

```g4
array : '{' el+=INT (',' el+=INT)* '}' ;
```

ANTLR在对应的规则上下文类中生成对应的列表字段。

```g4
	public static class ArrayContext extends ParserRuleContext {
 	public List<Token> el = new ArrayList<Token>();
 	...
 	}
```

这种标签列表同样适用于引用。

```g4
 	elist : exprs+=e (',' exprs+=e)* ;
```

ANTLR生成一个字段，来保存上下文对象列表。

```java
 	public static class ElistContext extends ParserRuleContext {
 	public List<EContext> exprs = new ArrayList<EContext>();
 	...
 	}
```

## 规则元素

规则元素指定解析器在给定时刻应执行的操作，就像编程语言中的语句一样。这些元素可以是rule、token、string literal，比如表达式、ID、'return'。下面是规则元素的完整列表。

| 语法       | 描述                                                         |
| ---------- | ------------------------------------------------------------ |
| T          | 匹配当前输入位置的token T，Tokens总是以大写字母开头。        |
| 'literal'  | 在当前输入位置，匹配字符常量，字符串文字只是带有固定字符串的标记。 |
| r          | 在当前输入位置匹配规则r，相当于函数调用一样触发该规则。解析规则名总是以小写字母开头。 |
| r [«args»] | 当前输入位置匹配规则r，并像函数调用一样传入参数列表。中括号里面的参数，使用目标语言语法。 |
| {«action»} | 在前一个选项元素之后和后一个选项元素之前立即执行action。该action符合目标语言语法。ANTLR逐字复制这些action代码至生产的类中，除了属性占位符和token引用，比如 $x，$x.y。 |
| {«p»}?     | 判断语义断言«p»。                                            |
| .          | 匹配任意单个Token，EOF除外。点操作被成为通配符。             |

当需要匹配一个或者多个token之外的任意内容是，可以使用 ~ 操作符。这个操作符很少在解析器中使用，但是可用。`~INT` 匹配除了INT之外的任意Token。`~','` 匹配除了逗号的任意token。`~(INT|ID)` 匹配除了INT和ID的任意Token。

Token, string literal, semantic predicate rule elements 可以带选项。

