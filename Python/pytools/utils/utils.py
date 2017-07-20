#!/usr/bin python3
# coding: utf-8

"""
FILE: utils.py
DATE: 17-6-26 上午11:10
DESC: 工具函数
"""
import hashlib
import os
from functools import partial


def md5sum(file_name):
    """
    计算文件md5
    :param file_name: 文件路径及名称
    :return:
    """
    with open(file_name, mode='rb') as f:
        d = hashlib.md5()
        for buf in iter(partial(f.read, 4096), b''):
            d.update(buf)
    return d.hexdigest()


def get_all_file_and_dir_recursion(file_path, recursion=False):
    """
    获取某一路径下所有文件及文件夹路径
    :param file_path: 父目录
    :param recursion: 是否递归获取
    :return: 路径集合
    """
    res = []
    for item in os.listdir(file_path):
        full_path = os.path.join(file_path, item)
        res.append(full_path)
        if recursion and os.path.isdir(full_path):
            res.extend(get_all_file_and_dir_recursion(full_path, recursion=recursion))
    return res


def get_all_file_recursion(file_path, recursion=False):
    """
    获取某一路径下所有文件路径
    :param file_path: 父目录
    :param recursion: 是否递归获取
    :return: 路径集合
    """
    res = []
    for item in os.listdir(file_path):
        full_path = os.path.join(file_path, item)
        if os.path.isfile(full_path):
            res.append(full_path)
        if recursion and os.path.isdir(full_path):
            res.extend(get_all_file_recursion(full_path, recursion=recursion))
    return res

if __name__ == "__main__":
    # print(md5sum('../files/de-weight.py'))
    print(md5sum('/media/bovenson/Data/媒体/音乐/网易云音乐/Dj苏性 - 八连杀(2).MP3'))
    print(md5sum('/media/bovenson/Data/媒体/音乐/网易云音乐/Dj苏性 - 八连杀(3).MP3'))
    print(md5sum('/media/bovenson/Data/媒体/音乐/网易云音乐/dj苏性 - 八连杀.mp3'))
    pass
