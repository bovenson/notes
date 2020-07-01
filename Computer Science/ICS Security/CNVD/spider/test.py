#!/bin/python3
# coding: utf-8

"""
TEST
"""
import re
from bs4 import BeautifulSoup

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-12-18 14:59"


# def show_f(o):
#     print(o)
#
#
# l = [1, 2, 3]
# r = map(lambda x: x + 2, l)
# print(list(r))
#
# r = map(lambda x: show_f(x), l)
# print(list(r))
#
# s = "hello"
# sr = ["hello", 'kll', 'edl']
# res = list(map(lambda x: x.replace('l', '-'), sr))
# print(sr)
# print(res)
#
# html = """
# <td>
#
#
# 													OPW Fuel Management Systems SiteSentinel iSite ATG &lt;V175<br>
#
# 													OPW Fuel Management Systems SiteSentinel iSite ATG V175-V189<br>
#
# 													OPW Fuel Management Systems SiteSentinel iSite ATG V191-V195<br>
#
# 													OPW Fuel Management Systems SiteSentinel iSite ATG V16Q3.1<br>
#
# 													OPW Fuel Management Systems SiteSentinel Integra 100 &lt;V175<br>
#
# 													OPW Fuel Management Systems SiteSentinel Integra 100 V175-V189<br>
#
# 													OPW Fuel Management Systems SiteSentinel Integra 100 V191-V195<br>
#
# 													OPW Fuel Management Systems SiteSentinel Integra 100 V16Q3.1<br>
#
# 													OPW Fuel Management Systems SiteSentinel Integra 500 &lt;V175<br>
#
# 													OPW Fuel Management Systems SiteSentinel Integra 500 V175-V189<br>
#
# 													OPW Fuel Management Systems SiteSentinel Integra 500 V191-V195<br>
#
# 													OPW Fuel Management Systems SiteSentinel Integra 500 V16Q3.1<br>
#
#
# 										</td>
# 										"""
#
# soup = BeautifulSoup(html, "html.parser")
# print(soup.prettify())
# print(list(map(lambda x: x.replace_with('\\n'), soup.find_all('br'))))
# print(soup.prettify())

s = "2013-02-12 2014-24-12"
p = re.compile(r'(?P<year>\d{4})-(?P<month>\d{2})-(?P<day>\d{2})', re.IGNORECASE)
m = p.search(s)
print(m.groups())
print(m.group('year'))
print(m.group('month'))
print(m.groupdict())

print('\n', '-' * 100, '\n')

m = p.findall(s)
print()
print(type(m))
print(m)
for g in m:
    print(type(g))
    # print(g.groups())
    # print(g.group('year'))
    # print(g.group('month'))
    # print(g.groupdict())

print('\n', '-' * 100, '\n')

m = p.finditer(s)
print(m)
for g in m:
    print(g.groups())
    print(g.group('year'))
    print(g.group('month'))
    print(g.groupdict())