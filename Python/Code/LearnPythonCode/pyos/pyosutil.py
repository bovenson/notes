#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: pyosutil.py
DATE: 17-9-6 下午3:10
DESC: 
"""
import os

import errno


def mkdirs(dir_name, ab_path=None):
    if ab_path:
        mkdir_p(os.path.join(ab_path, dir_name))
    else:
        mkdir_p(dir_name)


def mkdir_p(path):
    try:
        os.makedirs(path)
    except OSError as exc:  # Python >2.5
        if exc.errno == errno.EEXIST and os.path.isdir(path):
            pass
        else:
            raise
