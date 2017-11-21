#!/bin/python3
# coding: utf-8

"""
CNVD漏洞列表(http://ics.cnvd.org.cn/) 爬虫
"""
from parser import list_page_parser, info_page_parser
from downloader import HtmlDownloader

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-21 16:33"


if __name__ == "__main__":
    # url = "http://ics.cnvd.org.cn/?max=20&offset=1340"
    # html_content = HtmlDownloader.download(url)
    # page_list = list_page_parser(html_content)
    # if len(page_list.get('info_urls')) > 0:
    #     html_content = HtmlDownloader.download(page_list.get('info_urls')[0])
    #     res = info_page_parser(html_content)
    downloader = HtmlDownloader()
    url = 'http://www.cnvd.org.cn/flaw/show/CNVD-2017-29972'
    html_content = downloader.selenium_download(url)
    # html_content = HtmlDownloader().download(url)
    # if html_content:
    res = info_page_parser(html_content)
