#!/usr/bin python3
# coding: utf-8

"""
FILE: test.py
DATE: 17-6-26 上午11:15
DESC: 
"""


import os

p_file_path = '/media/bovenson/Data/媒体/音乐/网易云音乐'


def get_all_file_and_dir_recursion(file_path, recursion=False):
    res = []
    for item in os.listdir(file_path):
        full_path = os.path.join(file_path, item)
        res.append(full_path)
        if recursion and os.path.isdir(full_path):
            res.extend(get_all_file_and_dir_recursion(full_path, recursion=recursion))
    return res


if __name__ == "__main__":
    file_dir_s = get_all_file_and_dir_recursion(p_file_path, True)
    print(len(file_dir_s))

