---
title: Selenium
---

# Python

## 使用

```python
from selenium import webdriver
browser = webdriver.Firefox()
browser.get(url)
```

## 获取页面源代码

```python
browser.page_source
```

## 等待元素加载完成

```python
"""
The webdriver will wait for a page to load by default via .get() method.

As you may be looking for some specific element as @user227215 said, you should use WebDriverWait to wait for an element located in your page:
"""
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.common.exceptions import TimeoutException

browser = webdriver.Firefox()
browser.get("url")
delay = 3 # seconds
try:
    myElem = WebDriverWait(browser, delay).until(EC.presence_of_element_located((By.ID, 'IdOfMyElement')))
    print "Page is ready!"
except TimeoutException:
    print "Loading took too much time!"
```

## 设置headers

```python
#### 更改 user agent
from selenium import webdriver
profile = webdriver.FirefoxProfile()
profile.set_preference("general.useragent.override", "whatever you want")
driver = webdriver.Firefox(profile)
```

### 参考

- [参考一](https://stackoverflow.com/questions/29916054/change-user-agent-for-selenium-driver)
- [参考二](https://stackoverflow.com/questions/15645093/setting-request-headers-in-selenium)

## 参考

- [获取页面源代码](https://stackoverflow.com/questions/7263824/get-html-source-of-webelement-in-selenium-webdriver-using-python)
- [使用](http://blog.csdn.net/qq1124794084/article/details/53923897)
- [使用](https://www.cnblogs.com/fnng/archive/2013/05/29/3106515.html)
- [等待页面加载完成](https://stackoverflow.com/questions/26566799/how-to-wait-until-the-page-is-loaded-with-selenium-for-python)

# 问题