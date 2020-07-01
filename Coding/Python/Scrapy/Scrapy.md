# Scrapy

## 命令

### 新建工程

`scrapy startproject projectname`

### 在工程中, 新建spider

`scrapy gensipder dmoz_spider dmoz.org`

### 列出spider

`scrapy list`

### 执行spider

`scrapy crawl spider_name`

### 查看版本信息

`scrapy version`

`scrapy version -v`

### 查看帮助

`scrapy --help`

### view

查看页面源码在浏览器中显示的样子(在浏览器中显示源代码)

### parse

在工程中使用固定的parse函数解析某个页面 

### shell

`scrapy shell http://www.xxx.xx`

一个非常有用的命令, 可用于调试数据, 检测xpath, 查看源代码等

###  `runspider`

运行字包含的爬虫, 例如:

```python
import scrapy


class StackOverflowSpider(scrapy.Spider):
    name = "stackoverflow"
    start_urls = ["http://stackoverflow.com/questions?sort=votes"]

    def parse(self, response):
        for href in response.css(".question-summary h3 a::attr(href)"):
            full_url = response.urljoin(href.extract())
            yield scrapy.Request(full_url, callback=self.parse_question)
            pass
        pass

    def parse_question(self, response):
        yield {
            'title': response.css("h1 a::text").extract()[0],
            'votes': response.css(".question .vote-count-post::text").extract()[0],
            'body': response.css(".question .post-text").extract()[0],
            'link': response.url,
        }
```

**运行**

```shell
scrapy runspider spider_file_name.py
```

### `bench`

执行一个基准测试

`scrapy bench`

可用来检测scrapy是否安装成功



## 简单示例

### 代码

```python
import scrapy


class StackOverflowSpider(scrapy.Spider):
    name = "stackoverflow"
    start_urls = ["http://stackoverflow.com/questions?sort=votes"]

    def parse(self, response):
        for href in response.css(".question-summary h3 a::attr(href)"):
            full_url = response.urljoin(href.extract())
            yield scrapy.Request(full_url, callback=self.parse_question)
            pass
        pass

    def parse_question(self, response):
        yield {
            'title': response.css("h1 a::text").extract()[0],
            'votes': response.css(".question .vote-count-post::text").extract()[0],
            'body': response.css(".question .post-text").extract()[0],
            'link': response.url,
        }
```

### 原理解释

![](./img/scrapy-01-01.jpg)