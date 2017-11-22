#!/bin/python3
# coding: utf-8

"""
CNVD漏洞列表(http://ics.cnvd.org.cn/) 爬虫
"""
from time import sleep

from db import CNVDOperator
from parser import list_page_parser, info_page_parser
from downloader import HtmlDownloader

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-21 16:33"


REQUEST_DELAY = 5


def url_generator(max_len: int, offset: int):
    return "http://ics.cnvd.org.cn/?max=%d&offset=%d" % (max_len, offset)


def spider():
    failed_list_urls = set()    # 下载失败的列表页
    failed_info_urls = set()    # 下载失败的漏洞详情页
    finished_info_urls = set()
    max_len = 100
    offset = 0
    downloader = HtmlDownloader()
    cnvd = CNVDOperator()
    while True:
        if len(failed_list_urls) > 0:
            list_url = failed_list_urls.pop()
        else:
            list_url = url_generator(max_len, offset)
            offset += max_len

        html_content = downloader.download(list_url)
        if html_content:    # 页面下载成功
            info_urls = set(list_page_parser(html_content).get('info_urls'))
            info_urls.update(failed_info_urls)
            failed_info_urls.clear()

            if len(info_urls) == 0:     # 最后分页, 没有更多详情页
                break

            for info_url in info_urls:
                if info_url in finished_info_urls:  # 如果详情页面已经处理完成
                    continue
                sleep(REQUEST_DELAY)
                html_content = downloader.selenium_download(info_url)
                if html_content:    # 页面下载成功
                    gg_detail = info_page_parser(html_content)  # 漏洞详情
                    cnvd.insert(meta_data=gg_detail)
                    finished_info_urls.add(info_url)
                else:
                    failed_info_urls.add(info_url)
        else:
            failed_list_urls.add(list_url)


if __name__ == "__main__":
    spider()
    # print(url_generator(100, 100))
    # url = "http://ics.cnvd.org.cn/?max=20&offset=1340"
    # html_content = HtmlDownloader.download(url)
    # page_list = list_page_parser(html_content)
    # if len(page_list.get('info_urls')) > 0:
    #     html_content = HtmlDownloader.download(page_list.get('info_urls')[0])
    #     res = info_page_parser(html_content)
    # downloader = HtmlDownloader()
    # url = 'http://www.cnvd.org.cn/flaw/show/CNVD-2017-29972'
    # html_content = downloader.selenium_download(url)
    # html_content = HtmlDownloader().download(url)
    # if html_content:
    # res = info_page_parser(html_content)
