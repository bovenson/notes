---
title: LJH QA
---

[TOC]

# 核心代码是哪一部分

系统大概分为三个部分：

- 数据收集
  - 即使用注入页面的JS代码，获取页面信息，并发送给系统
- 数据处理
  - 即后台服务对接受到的页面信息进行解析、存储
- 数据展示
  - 即对页面访问信息的前端展示

# 数据库怎么设计的，代码体现在哪一部分

模型使用Django框架的Model模块进行定义。

相应的代码在`app/models.py`

```python
class PageViewInfo(models.Model):
    """页面访问数据"""
    title = models.CharField(verbose_name='网页标题', max_length=50, blank=True, null=True)
    account = models.CharField(verbose_name='客户账号', max_length=100, blank=True, null=True)
    uuid = models.CharField(verbose_name='访客ID', max_length=100, blank=True, null=True)
    domain = models.CharField(verbose_name='域名', max_length=500, blank=True, null=True)
    referrer = models.URLField(verbose_name='referrer url', max_length=500, blank=True, null=True)
    url = models.URLField(verbose_name='网页URL', max_length=500, blank=True, null=True)
    date = models.DateField(verbose_name='访问日期', auto_now_add=True)
    date_time = models.DateTimeField(verbose_name='访问日期及时间', auto_now_add=True)
    is_new = models.BooleanField(verbose_name='是否是新访客', default=False)
    source = models.CharField(verbose_name='流量来源', max_length=20, default='其他')

    def __str__(self):
        return self.title
```

**数据表是怎么生成的？**

数据库使用SQLite，数据库文件以及数据表的生成由Django执行，对应的命令为：

```shell
$ python3 manager.py makemigrations
$ python3 manage.py migrate
```

# 嵌入前端的js代码在哪里

和JS注入相关的两个js文件是：

- `ma-set.js` : 在ga中，这个是由站长进行配置，主要功能有两个

  - 执行js代码动态插入`ma.js`的标签，用于注入`ma.js`，相关的代码如下

    ```javascript
    (function() {
        var ma = document.createElement('script');
        ma.type = 'text/javascript';
        ma.async = true;
        // ma.src = "http://118.202.45.20:8000/static/js/ma.js";
        ma.src = "http://localhost:8000/static/js/ma.js";
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(ma, s);
    })();
    ```

  - 整理需要发送给后台的数据

    ```javascript
    var _map = _map || [];
    (function() {
        /********** 设置数据 **********/
        _map.push(['account', '书云']);
        _map.push(['title', document.title]);   // 网页标题
        _map.push(['uuid', getUserID()]);       // 用户UUID
    })();
    ```

- `ma.js` : 用于将站长配置的信息发送给后台服务（通过请求`_utm.gif`的方式）

  - 相关代码如下

    ```javascript
    (function() {
        var params = {};
        // Document 对象数据
        if (document) {
            params.domain = document.domain || '';
            params.url = document.URL || '';
            params.title = document.title || '';
            params.referrer = document.referrer || '';
        }
    
        // Windows 对象数据
        if (window && window.screen) {
            params.sh = window.screen.height || 0;
            params.sw = window.screen.width || 0;
            params.cd = window.screen.colorDepth || 0;
        }
    
        // Navigator 对象数据
        if (navigator) {
            params.lang = navigator.language || '';
        }
    
        // 解析 _map 配置
        if (_map)
        {
            for (var i in _map) {
                params[_map[i][0]] = _map[i][1];
            }
        }
    
        $.post({
            url: 'http://localhost:8000/app/_utm.gif',
            // url: 'http://202.118.26.199:8000/app/_utm.gif',
            type: 'POST',
            data: params,
            success: function(msg) {
                console.log("请求成功:" + msg);
            },
            dataType: 'json'
        });
    })();
    ```

    

# 哪里用到了bootstrap，哪里用到了django

**Bootstrap**

`bootstrap`是前端开发的工具包，主要用于前端页面布局及样式设计，相应的体现是在一些`html`文件中。

比如在`templates/app/analysis/analysis.html`中：

```html
<div class="row">
    <div class="col chart-container">
        <div class="chart-border" id="chart-uv" style="width: 100%;min-height: 20rem;"></div>
    </div>
    <div class="col chart-container">
        <div class="chart-border" id="chart-pv" style="width: 100%;min-height: 20rem;"></div>
    </div>
</div>
```

样式类`row`、`col` 都来自于`bootstrap`框架。

**Django**

Django是一个基于Python的Web开放框架，是整个系统的基础。

主要体现在三个方面：

- `Model` ：与`app/models.py `相对应
- `View` ：与 `app/models.py` 相对应
- `Controller` ：与`app/views.py`相对应
- `Template` : 对前端页面进行渲染。与`templates` 文件夹下的`html`文件相对应

# 还有实现过程中遇到了哪些问题，都怎么解决的

**怎么跨站点发送数据？**

由于浏览器禁止跨域请求的问题，但是对静态文件没有限制，于是通过向数据分析服务器请求静态文件，并把数据当作参数，实现发送数据的需求。具体可以参考ga的实现。

# 图表绘制，时间轴选择这种是怎么做到的

**图标绘制**

使用的echarts插件。

**时间轴选择**

也是使用插件，日期选择器，可以选择一段时间。

# 为什么访问地域功能没实现

凤尾没有提这个需求。