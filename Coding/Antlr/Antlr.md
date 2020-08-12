# Description

Antlr4 是一款强大的语法生成器工具，可用于读取、处理、执行和翻译结构化的文本或二进制文件。Antlr 使用上下文无关文法描述语言，通过解析用户自定义文法，自动生成词法分析器 (Lexer)、语法分析器 (Parser) 和树分析器 (Tree Parser)。

Anltr 的语法可以定义目标语言的词法记号和语法规则，Antlr 自动生成目标语言的词法分析器和语法分析器；此外，如果在语法规则中指定抽象语法树的规则，在生成语法分析器的同时，Antlr 还能够生成抽象语法树；最终使用树分析器遍历抽象语法树，完成语义分析和中间代码生成。

# Reference

- https://developer.ibm.com/zh/articles/j-lo-antlr/#
- https://www.jianshu.com/p/21f2afca65e8