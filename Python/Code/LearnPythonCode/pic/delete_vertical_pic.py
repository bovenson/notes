#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: delete_vertical_pic.py
DATE: 17-9-6 下午3:06
DESC: 
"""
import os

import errno
import shutil

from PIL import Image


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


def solve(ab_path):
    # 更改工作目录
    os.chdir(ab_path)
    mkdirs('trash')
    mkdirs('4k+')
    mkdirs('2k')
    trash_path = os.path.join(ab_path, 'trash')
    path_4kp = os.path.join(ab_path, '4k+')
    path_2k = os.path.join(ab_path, '2k')

    for _file in os.listdir('.'):
        try:
            _file_ab_path = os.path.join(ab_path, _file)
            img = Image.open(_file_ab_path)
            if img.width < img.height or img.width < 1400 or img.height < 800:
                move_to_trash(_file_ab_path, trash_path)
                print(_file)
            elif img.width > 3500 and img.height > 2000:
                move_to_trash(_file_ab_path, path_4kp)
            elif img.width > 1900 and img.height > 1000:
                move_to_trash(_file_ab_path, path_2k)

        except:
            pass


def move_to_trash(ab_path, trash_path):
    shutil.move(ab_path, trash_path)


if __name__ == '__main__':
    # solve('/media/bovenson/QQ907238952/媒体/图片/壁纸')
    # solve('/media/bovenson/QQ907238952/媒体/图片/壁纸All')
    solve('/media/bovenson/QQ907238952/媒体/图片/高清壁纸')
    pass
