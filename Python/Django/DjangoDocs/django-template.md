---
title: template
date: 2017-07-24 17:48:54
tags: Python, Django, Template
---

# template

## 返回404

### 示例

```python
try:
    book = Book.objects.get(id=book_id)
except Book.DoesNotExist:
    raise Http404("书籍不存在")
```

## url标签传递多个参数

```python
{% url 'some-url-name' v1 v2 %}
```

## 截取制定长度字符串

```python
{# 截取前140个字 #} 
{{ book.summary|truncatechars:140 }}
```

## 在for循环中截取队列制定长度

```html
{% for tag in booklist.tags.all|slice:":5" %}
    <a href="{% url 'tag' tag.id %}" class="booklist-tag">#{{ tag.name }}</a>
{% endfor %}
```

## 格式化Datetime

```python
{{ wpis.entry.lastChangeDate|date:'Y-m-d H:i' }}
```

