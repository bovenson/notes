#!/bin/python3
# coding: utf-8

"""
CNVD漏洞列表(http://ics.cnvd.org.cn/) 爬虫
下载器
"""
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
import random
import urllib
import requests
from bs4 import BeautifulSoup

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-21 16:35"

BROWSER_NUMS = 8

COOKIES = None
# COOKIES = {'__jsluid': 'dddfb3f731e5085641e063b082d8c52c',
#            # 'bdshare_firstime': '1511253034454',
#            # '__jsl_clearance': '1511259854.222|0|tnkw8TUzdThJLH%2B1CbxvMBp%2Fl0g%3D',
#            # 'JSESSIONID': '5606200CFD9FC7E8892C624491E4C705'
#            }

DEFAULT_HEADERS = {
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
    'Accept-Encoding': 'gzip, deflate, sdch',
    'Accept-Language': 'zh-CN,zh;q=0.8',
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) '
                  'Chrome/62.0.3202.94 Safari/537.36',
}

USER_AGENTS = [
    'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
    'Opera/9.25 (Windows NT 5.1; U; en)',
    'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
    'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
    'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 '
    'Ubuntu/dapper-security Firefox/1.5.0.12',
    'Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/1.2.9',
    "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) "
    "Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
    "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 ",
    'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) '
    'Chrome/62.0.3202.94 Safari/537.36'
]


class HtmlDownloader:
    REQUEST_METHOD_GET = 'GET'
    REQUEST_METHOD_POST = 'POST'

    def __init__(self):
        self.browser = webdriver.Firefox()
        self.browsers = [self.browser]
        self.cur_browser = 0
        for i in range(BROWSER_NUMS-1):
            profile = webdriver.FirefoxProfile()
            profile.set_preference("general.useragent.override", random.choice(USER_AGENTS))
            self.browsers.append(webdriver.Firefox(profile))
        # self.browser.implicitly_wait(5)

    @staticmethod
    def download(url, data=None, cookies=None, headers=None):
        """
        下载url内容
        :param cookies: cookie 信息
        :param headers: 请求头
        :param url: 资源地址
        :param data: 请求数据
        :return: 获取到的内容
        """
        if url is None:
            return None

        if data:
            data = urllib.parse.urlencode(data).encode(encoding="utf-8")

        if headers is None:
            headers = DEFAULT_HEADERS
            # headers['user-agent'] = random.choice(USER_AGENTS)

        req = requests.get(url, params=data, headers=headers, cookies=COOKIES)
        # print(req.headers)

        if req.status_code != 200:
            print('下载页面失败, 错误代码:', req.status_code, ' 资源地址:', url)
            print(str(req.content, encoding='utf-8'))
            return None
        else:
            # print('页面下载完成', ' 资源地址:', url)
            return req.content
            # print(req.content)

    def next_browser(self):
        if len(self.browsers) > 0:
            browser = self.browsers[self.cur_browser]
            self.cur_browser += 1
            if self.cur_browser >= len(self.browsers):
                self.cur_browser = self.cur_browser % len(self.browsers)
            return browser
        else:
            return None

    def selenium_download(self, url):
        try:
            delay = 5
            browser = random.choice(self.browsers)
            browser.get(url)
            # print(self.browser)
            WebDriverWait(browser, delay).until(EC.presence_of_element_located((By.ID, 'J_Comment_Wrap')))
            # print(self.browser.page_source)
            res = browser.page_source
            return res
        except Exception as e:
            print('下载页面失败, 错误:', str(e).replace('\n', ' '), ' 资源地址:', url)
            return None


if __name__ == "__main__":
    url = "http://ics.cnvd.org.cn/?max=20&offset=0"
    url_info = 'http://www.cnvd.org.cn/flaw/show/CNVD-2017-29972'
    downloader = HtmlDownloader()
    downloader.download(url)
    downloader.download(url_info)
    # html_content = downloader.download(url)
    # soup = BeautifulSoup(html_content, 'html.parser', from_encoding="utf-8")
    # print(soup.prettify())
