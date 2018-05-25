#!/bin/python3
# coding: utf-8

"""
数据读写工具
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-04-21 09:46"

from openpyxl import load_workbook


def read_from_excel(file_name, sheet_name):
    res = []
    workbook = load_workbook(file_name)
    worksheet = workbook[sheet_name]
    rows = worksheet.rows
    for row in rows:
        res.append([col.value for col in row if col.value is not None])
    return res


if __name__ == '__main__':
    print(read_from_excel('data.xlsx', 'ap_positions_1'))
    print(read_from_excel('data.xlsx', 'rf_positions_1'))
