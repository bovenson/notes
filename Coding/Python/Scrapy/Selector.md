[TOC]

# Selector

用来解析网页的库有很多, 比如`beautifulsoup`, `lxml`, 但是`scrapy`默认使用的是`selector`.

## 基本介绍

Scrapy的选择器是`Selector`的示例.

### 创建实例

通过传送文本(text)或`TextResponse`对象来创建, 并自动选择解析规则(XML/HTML)

```shell
>>> from scrapy.selector import Selector
>>> from scrapy.http import HtmlResponse
```

#### 从text创建

```shell
>>> body = '<html><body><span>good</span></body></html>'
>>> Selector(text=body).xpath('//span/text()').extract()[u'good']
```

#### 从response对象创建

```shell
>>> response = HtmlResponse(url='http://example.com', body=body)
>>> Selector(response=response).xpath('//span/text()').extract()
[u'good']
```

方便起见, reponse对象有`selector`属性, 来创建`selector`:

```shell
>>> response.selector.xpath('//span/text()').extract()[u'good']
```

## 常用抽取方法

### `xpath`

### `css`

### `re`

### `extract`

## 应用实例

