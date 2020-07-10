# Lucene

传入Lucene用于构建索引的纯文本，通常会进行令牌化(tokenization)的过程。令牌化是把输入文本拆分成多个小索引元素的过程，这些元素成为令牌(tokens)。输入文本被分解为令牌的方式在很大程度上影响后面如何搜索该文本。例如，可以识别语句的开头和结尾以提供更准确的短语和相似度搜索（Lucene不提供句子识别）。

在某些情况下，仅将输入文本拆分成令牌是不够的，可能需要更深入的分析。Lucene提供标记前和标记后的分析工具。

标记前工作，比如剥离HTML标签，转换、删除任意匹配的模式串。

笔记后的工作，比如：

- [Stemming](http://en.wikipedia.org/wiki/Stemming) 词干提取，使用词干替换单词。在语言形态学和信息检索中，词干提取是将变形的（或有时衍生的）词还原为词干，基词或词根形式（通常是书面词形式）的过程。
- [Stop Words Filtering](http://en.wikipedia.org/wiki/Stop_words) 停用词过滤，减少索引量和噪音。停用词是在处理自然语言数据（文本）之前或之后过滤掉的词。
- [Text Normalization](http://en.wikipedia.org/wiki/Text_normalization) 文本规范化，是将文本转换为单一规范形式的过程。
- [Synonym Expansion](http://en.wikipedia.org/wiki/Synonym) 同义词扩展。

# Packages

## **[`org.apache.lucene.analysis`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/package-summary.html)** 

用于将文本转换为可可索引、可搜索标记的库，包括[`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html) 及相关类。analysis包提供了将Strings和Readers转换为Lucene可以索引的令牌的机制。该包包含四个主要类，所有分析过程由他们派生出来。

- [`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html) 用于提供索引和搜索过程可消费的 [`TokenStream`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/TokenStream.html) 。
- [`CharFilter`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/CharFilter.html) CharFilter扩展了Reader在令牌化文本前的转换过程，同时为这些更改提供正确的字符偏移量。当索引令牌从CharFilter创建时，该能力允许高亮显示应用在原始文本上。修改后的文本，其偏移量与原始文本中的偏移量不同。多个`CharFilter`可以串联，来执行多个令牌化前的修改操作。 [`Tokenizer.setReader(java.io.Reader)`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Tokenizer.html#setReader-java.io.Reader-) 接受 `CharFilter`。
- [`Tokenizer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Tokenizer.html) Tokenizer是一个`TokenStream`，负责把输入文本拆分成令牌。在许多情况下， [`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html) 会将 [`Tokenizer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Tokenizer.html) 用作分析过程的第一步。但是，要在标记化之前修改文本，需要使用CharFilter。
- [`TokenFilter`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/TokenFilter.html) `TokenFilter`是一个`TokenStream`，用于修改Tokenizer创建的令牌。TokenFilter常见的修改操作有删除、词干提取、同义词注入。并不是所有的`Analyzer`都需要TokenFilter。

定义抽象[`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html) API，用于将[`Reader`](https://docs.oracle.com/javase/8/docs/api/java/io/Reader.html?is-external=true)转换为[`TokenStream`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/TokenStream.html) 。多个[`TokenFilter`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/TokenFilter.html)应用于[`Tokenizer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Tokenizer.html)的输出组成`TokenStream`。`Tokenizers`和`TokenFilters`绑定，和 [`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html)一起使用。[analyzers-common](https://lucene.apache.org/core/8_5_2/analyzers-common/overview-summary.html) 提供了多种Analyzer实现，包括[StopAnalyzer](https://lucene.apache.org/core/8_5_2/analyzers-common/org/apache/lucene/analysis/core/StopAnalyzer.html) 和基于语法的 [StandardAnalyzer](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/standard/StandardAnalyzer.html)。

`TokenStream` 罗列了一系列的token，来自`Document`的`Field`或者查询文本，`TokenStream`是一个抽象类，具体的实现有两个，[`Tokenizer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Tokenizer.html) 和 [`TokenFilter`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/TokenFilter.html)。[`Tokenizer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Tokenizer.html) 的输入来自`Reader`， [`TokenFilter`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/TokenFilter.html)的输入来自另外一个TokenStream。

## **[`org.apache.lucene.codecs`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/codecs/package-summary.html)**

提供了对倒排索引结构的编码和解码的抽象，以及可以根据应用程序需求选择的不同实现。

## **[`org.apache.lucene.document`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/document/package-summary.html)** 

提供一个简单的Document类。Document只是一组命名字段，其值可以是字符串或Reader的实例。

## **[`org.apache.lucene.index`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/index/package-summary.html)** 

提供了两个主要的类，`IndexWriter`用于创建索引并将文档添加到索引；`IndexReader`，用于访问索引中的数据。

## **[`org.apache.lucene.search`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/search/package-summary.html)** 

提供用于查询的数据结构，比如[`TermQuery`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/search/TermQuery.html)用于别词，[`PhraseQuery`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/search/PhraseQuery.html) 用于词组，[`BooleanQuery`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/search/BooleanQuery.html) 用于查询的逻辑组合。[`IndexSearcher`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/search/IndexSearcher.html) 将查询转换为 [`TopDocs`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/search/TopDocs.html)，提供多种 [QueryParser](https://lucene.apache.org/core/8_5_2/queryparser/overview-summary.html) 用于从字符串或者xml生成查询结构。

## **[`org.apache.lucene.store`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/store/package-summary.html)** 

定义了抽象类  [`Directory`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/store/Directory.html)，用于持久保存数据，保存了文件名的集合，供[`IndexOutput`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/store/IndexOutput.html)写或[`IndexInput`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/store/IndexInput.html)读。同时提供了多种实现 ，推荐使用 [`FSDirectory`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/store/FSDirectory.html)，它尝试高效地使用系统磁盘缓冲区。

## **[`org.apache.lucene.util`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/util/package-summary.html)**

包含一些方便的数据结构和util类，比如 [`FixedBitSet`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/util/FixedBitSet.html) 和 [`PriorityQueue`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/util/PriorityQueue.html)。

# Steps

- 通过添加`Fields`创建`Documents`
- 创建[`IndexWriter`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/index/IndexWriter.html)，并通过`addDocument`添加`Document`
- 调用 [QueryParser.parse()](https://lucene.apache.org/core/8_5_2/queryparser/org/apache/lucene/queryparser/classic/QueryParserBase.html#parse(java.lang.String)) 从字符串构造请求
- 创建 [`IndexSearcher`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/search/IndexSearcher.html) ，将构造的请求对象通过[`search()`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/search/IndexSearcher.html#search-org.apache.lucene.search.Query-int-)方法传入