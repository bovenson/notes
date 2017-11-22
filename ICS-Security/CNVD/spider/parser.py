#!/bin/python3
# coding: utf-8

"""
CNVD漏洞列表(http://ics.cnvd.org.cn/) 爬虫
页面解析器
"""
import re

from bs4 import BeautifulSoup
from bs4.element import Tag, NavigableString

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-21 16:56"


# 字段提示及字段名
FIELDS_MAP = {
    '漏洞名称': 'Vulnerability_name',
    '报送者': 'Discoverer',
    'CNVD-ID': 'CNVD_ID',
    '发布时间': 'Release_time',
    '危害级别': 'Level',
    '影响产品': 'Device',
    'CVEID': 'CVE_ID',
    '漏洞描述': 'Description',
    '参考链接': 'Refer_link',
    '漏洞解决方案': '漏洞解决方案',
    '漏洞发现者': 'Vendor',
    '厂商补丁': '厂商补丁',
    '验证信息': '验证信息',
    '报送时间': '报送时间',
    '收录时间': '收录时间',
    '更新时间': 'Update_time',
    '漏洞附件': '漏洞附件'
}


def list_page_parser(html_content):
    """
    漏洞列表解析器
    :param html_content: 页面内容
    :return: {'pages': [], 'info_urls': []}
    """
    res = {'pages': [], 'info_urls': []}
    urls = res.get('info_urls')
    soup = BeautifulSoup(html_content, 'html.parser', from_encoding='utf-8')
    trs = soup.find('tbody', id='tr').find_all('tr')
    for tr in trs:
        urls.append(tr.find('a', href=re.compile('^https?://www.cnvd.org.cn/flaw/show/CNVD-\d+-\d+')).get('href'))
    return res


def info_page_parser(html_content):
    """
    漏洞详情页解析器
    :param html_content: 页面内容
    :return: 漏洞元数据信息
    """
    meta_data = {}
    soup = BeautifulSoup(html_content, 'html.parser')
    # print(soup.prettify())

    # 提取信息
    # 漏洞名称
    try:
        meta_data[FIELDS_MAP.get('漏洞名称')] = soup.find('div', class_='blkContainerSblk').find('h1').string
    except Exception as e:
        meta_data[FIELDS_MAP.get('漏洞名称')] = "NOT FOUND"

    # 报送者
    try:
        art_info = soup.find('div', class_='artInfo')
        sender = art_info.find('span', text=re.compile(r'^报送者'))  # <span>报送者:恒安嘉新(北京)科技股份公司</span>
        meta_data[FIELDS_MAP.get('报送者')] = str(sender.string)[4:]
    except Exception as e:
        meta_data[FIELDS_MAP.get('报送者')] = "NOT FOUND"

    # 表单信息
    try:
        trs = soup.find('table', class_='gg_detail').find('tbody').find_all('tr')
        for tr in trs:
            try:
                tds = tr.find_all('td')
                if len(tds) < 2:
                    raise Exception('信息错误')
                td_field_name = re.sub(r'\s+', '', str(tds[0].string))
                td_filed_value = ''
                for elem in tds[1].contents:
                    if isinstance(elem, Tag):
                        td_filed_value += elem.string if elem.string else ''
                    elif isinstance(elem, NavigableString):
                        td_filed_value += str(elem)
                # print(td_filed_value)
                td_filed_value = re.sub('\s+', ' ', td_filed_value).strip()
                # print(td_filed_value)
                # td_files_value = str(reduce(lambda x, y: str(x.string)+str(y.string), tds[1].contents)).strip()
                if td_field_name == '危害级别' and FIELDS_MAP.get(td_field_name):
                    meta_data[FIELDS_MAP.get(td_field_name)] = td_filed_value[0:1]
                elif FIELDS_MAP.get(td_field_name):
                    meta_data[FIELDS_MAP.get(td_field_name)] = td_filed_value
            except Exception as e:
                # print(e)
                pass
    except Exception as e:
        print(e)
    # print(meta_data)
    return meta_data
