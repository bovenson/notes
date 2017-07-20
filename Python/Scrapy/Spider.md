[TOC]

# Spider

`Spider`是一个类, 定义了怎样爬去一个网站, 包括怎样跟踪连接, 怎样提取数据.

## 流程

- generating the initial Requests(下载)
- parse the response(解析)
- using selector(抽取)
- store item(保存)

## 基类: `scrapy.Spider`

### 属性

#### `name`

spider的名称, 要求工程内唯一

#### `allowed_domains`

允许的域名

#### `start_urls`

出事`url`集合

#### `custom_settings`

个性化设置, 会覆盖全局的设置

#### `crawler`

抓取器, spider将绑定到它上面

#### `settings`

配置实例, 包括工程中所有的配置变量

#### `logger`

日志实例

### 方法

#### `from_crawler(crawler, *args, **kwargs)`

用于创建spiders

#### `start_requests()`

生成初始的requests

#### `make_requests_from_url(url)`

根据url生成一个request

#### `parse(response)`

用来解析网页内容

#### `self.logger.info("info")`

记录日志

#### `closed(reason)`

当spider关闭的时候调用的方法

## 子类

###  `CrawlSpider`

- 最常用的spider, 用于抓取普通的网页
- 增加了两个成员:
  - `rules`: 定义了一些抓取规则(怎么跟踪, 使用哪一个parse函数解析次连接)
  - `parse_start_url(response)`: 解析初始url的响应

### `XMLFeedSpider`

### `CSVFeedSpider`

### `SitemapSpider`

