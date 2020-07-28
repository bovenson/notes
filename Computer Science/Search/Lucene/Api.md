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

# Hints, Tips and Traps

[`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html)、 [`CharFilter`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/CharFilter.html)、 [`Tokenizer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Tokenizer.html)、 [`TokenFilter`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/TokenFilter.html)的关系很容易迷惑。

- [`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html) 是分析链的工厂，`Analyzer`不处理文本，它构造用于处理文本的`CharFilter`、`Tokenizer`或者`TokenFilter`。Analyzer有两个任务：创建[`TokenStream`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/TokenStream.html)，用于接受reader、产生tokens、包装或者预处理Reader对象。
- CharFilter是Reader的子类，它支持偏移量跟踪。
- [`Tokenizer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Tokenizer.html)仅负责将输入文本分解为tokens。
- [`TokenFilter `](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/TokenFilter.html)修改令牌流(stream of tokens)及其内容。
- [`Tokenizer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Tokenizer.html)是 [`TokenStream`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/TokenStream.html)，但[`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html) 不是。
- [`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html) “具有字段识别能力”，但 [`Tokenizer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Tokenizer.html)则不能。 [`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html)在构造`TokenStream`时可能会考虑字段名称。

如果想要使用特定的`CharFilter`、`Tokenizer`、`TokenFilter`组合，最简单的事情是使用[`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html)的匿名类，提供[`Analyzer.createComponents(String)`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html#createComponents-java.lang.String-)、 [`Analyzer.initReader(String, java.io.Reader)`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html#initReader-java.lang.String-java.io.Reader-)。Lucene提供很多Analyzer提供有用的分析链，其中最常用的是[StandardAnalyzer](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/standard/StandardAnalyzer.html)。[analyzers-common](https://lucene.apache.org/core/8_5_2/analyzers-common/overview-summary.html)库为多种语言提供了analyzers，同时允许配置自定义Analyzer。

除了StandardAnalyzer，Lucene包含多种组件，包括分析组件，他们在analysis文件夹下。其中一些支持特定语言，另一些集成外部组件。common文件夹有一些值得注意的通用分析器，包括 [PerFieldAnalyzerWrapper](https://lucene.apache.org/core/8_5_2/analyzers-common/org/apache/lucene/analysis/miscellaneous/PerFieldAnalyzerWrapper.html)，多数`Analyzer`在所有的[`Field`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/document/Field.html)上执行相同的操作。PerFieldAnalyzerWrapper可用于将不同的分析器与不同的字段关联。

分析器是构造索引缓慢的主要原因之一。

benchmark对测试分析进程效率非常有帮助。

# Invoking the Analyzer

应用程序通常不需要调用分析器，交由Lucene来做。

- 索引构建阶段， [`addDocument(doc)`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/index/IndexWriter.html#addDocument-java.lang.Iterable-)时对索引有效的分析器会被调用，应用于添加的文档的每个索引字段。
- 搜索阶段，QueryParser有可能在解析阶段调用Analyzer，有些搜索可能不会有分析操作。

应用可以这样执行分析器，用于测试或者其他目的。

```java
Version matchVersion = Version.LUCENE_XY; // Substitute desired Lucene version for XY
Analyzer analyzer = new StandardAnalyzer(matchVersion); // or any other analyzer
TokenStream ts = analyzer.tokenStream("myfield", new StringReader("some text goes here"));
// The Analyzer class will construct the Tokenizer, TokenFilter(s), and CharFilter(s),
//   and pass the resulting Reader to the Tokenizer.
OffsetAttribute offsetAtt = ts.addAttribute(OffsetAttribute.class);

try {
  ts.reset(); // Resets this stream to the beginning. (Required)
  while (ts.incrementToken()) {
    // Use AttributeSource.reflectAsString(boolean)
    // for token stream debugging.
    System.out.println("token: " + ts.reflectAsString(true));

    System.out.println("token start offset: " + offsetAtt.startOffset());
    System.out.println("  token end offset: " + offsetAtt.endOffset());
  }
  ts.end();   // Perform end-of-stream operations, e.g. set the final offset.
} finally {
  ts.close(); // Release resources associated with this stream.
}
```

# Indexing Analysis vs. Search Analysis

选择正确的分析器对于搜索质量至关重要，也会影响索引、搜索性能。对于应用程序来说，正确的分析器取决于输入文本以及解决什么样的问题，这里有一些经验指导：

- 测试
- 过多的分析器可能会影响索引性能
- 索引、搜索过程从相同的分析器开始，否则搜索的时候可能不知道目的是什么...
- 在某些情况下，需要使用其他分析器进行索引和搜索
  - 某些搜索需要过滤更多停用词

# 构造自己的分析器和分析组件

构造自己的分析器是最直接的，自定义分析器应该是[`Analyzer`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html)的子类，它可以使用现有的分析组件CharFilter、Tokenizer、TokenFilter、自定义组件。

# 字段剖面边界

对相同的field多次调用 [`document.add(field)`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/document/Document.html#add-org.apache.lucene.index.IndexableField-) ，我们说每次调用创建会为该字段创建一个剖面(section)，实际上针对这些切面，会单独调用[`tokenStream(field,reader)`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html#tokenStream-java.lang.String-java.io.Reader-) 。但是默认的Analyzer行为是把这些剖面当做一个大的剖面，这允许词组搜索和相似搜索可以无缝跨越这些剖面(sections)的边界。比如，像这样添加`f`字段。

```java
document.add(new Field("f","first ends",...);
document.add(new Field("f","starts two",...);
indexWriter.addDocument(document);
```

然后，词组搜索`ends starts`会找到那篇文章。如果需要，可以通过在连续的字段剖面之间引入位置间隙来修改此行为，只需覆盖[`Analyzer.getPositionIncrementGap(fieldName)`](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/Analyzer.html#getPositionIncrementGap-java.lang.String-)即可。

```java
Version matchVersion = Version.LUCENE_XY; // Substitute desired Lucene version for XY
Analyzer myAnalyzer = new StandardAnalyzer(matchVersion) {
  public int getPositionIncrementGap(String fieldName) {
    return 10;
  }
};
```



# Reference

- [lucene analysis](https://lucene.apache.org/core/8_5_2/core/org/apache/lucene/analysis/package-summary.html#package.description)

