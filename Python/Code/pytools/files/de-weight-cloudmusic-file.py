#!/usr/bin python3
# coding: utf-8

"""
FILE: de-weight-cloudmusic-file.py
DATE: 17-6-26 下午3:10
DESC: 去除网易云音乐重复文件
"""
import hashlib
import os
import re
from functools import partial
from optparse import OptionParser

import sys

import shutil

import errno

USAGE = "usage: python3 de-weight-cloudmusic-file.py [directory path] -r [recursion] -d [repeat file destination path]"


parser = OptionParser(USAGE)
parser.add_option("-r", action="store_true", dest="recursion", default=False)
parser.add_option("-d", dest="destination")
opt, args = parser.parse_args()


if len(args) < 1:
    print(USAGE)
    sys.exit(1)


work_path = args[0]
dest_path = opt.destination if opt.destination else os.path.join(work_path, 'repeat')
recursion = opt.recursion


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


try:
    os.makedirs(dest_path)
except OSError as exc:  # Python >2.5
    if exc.errno == errno.EEXIST and os.path.isdir(dest_path):
        pass
    else:
        raise

file_list = get_all_file_recursion(work_path, recursion)
file_info_list = []

for file_item in file_list:
    file_item = str(file_item)
    if file_item.lower() in file_info_list:
        shutil.move(file_item, os.path.join(dest_path, file_item.rsplit(os.path.sep, 1)[-1]))
    else:
        file_info_list.append(file_item.lower())
    if len(re.compile(r'\(\d+\)').findall(file_item)) > 0:
        print(re.compile(r'\(\d+\)').findall(file_item))
        old_file_name_split = file_item.rsplit(os.path.sep, 1)[-1].rsplit('.', 1)
        file_path = file_item.rsplit(os.path.sep, 1)[0]
        old_file_name = old_file_name_split[0]
        file_format = ''
        if len(old_file_name_split) > 1:
            file_format = '.' + old_file_name_split[1]
        new_file_name = re.sub(r'\(\d+\)', '', old_file_name).strip() + file_format
        new_file_with_path = os.path.join(file_path, new_file_name)
        print(file_item, ' -> ', new_file_with_path)

        if os.path.exists(new_file_with_path):
            shutil.move(file_item, os.path.join(dest_path, new_file_name))
        else:
            shutil.move(file_item, new_file_with_path)


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


class FileInfo(object):
    md5 = ''
    file_name = ''
    file_path = ''

    def __init__(self, md5, file_name='', file_path=''):
        self.md5 = md5
        self.file_name = file_name
        self.file_path = file_path

    def __str__(self):
        return self.file_name

    def __eq__(self, other):
        if not isinstance(other, FileInfo):
            return False
        return self.md5 == other.md5


def mkdir_p(path):
    try:
        os.makedirs(path)
    except OSError as exc:  # Python >2.5
        if exc.errno == errno.EEXIST and os.path.isdir(path):
            pass
        else:
            raise


def process_repeat_file(file_name):
    mkdir_p(dest_path)
    shutil.move(file_name, dest_path)

file_list = get_all_file_recursion(work_path, recursion)
for file_item in file_list:
    new_file = FileInfo(md5=md5sum(file_item), file_name=file_item)
    if new_file in file_info_list:
        print("重复文件:", file_item)
        process_repeat_file(file_item)
    else:
        file_info_list.append(new_file)

# python3 de-weight-cloudmusic-file.py /media/bovenson/Data/媒体/音乐/网易云音乐
