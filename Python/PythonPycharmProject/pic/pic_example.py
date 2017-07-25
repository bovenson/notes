#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: pic_example.py
DATE: 17-7-25 上午10:25
DESC: 
"""
from PIL import Image


# 读取
img = Image.open('/home/bovenson/Downloads/Other/mnist-test/4.3192.jpg')

# 打印图片信息
print('Filename:\t', img.filename)
print('Format:\t', img.format)
print('Mode:\t', img.mode)
print('Size:\t', img.size)
print('Width:\t', img.width)
print('Height:\t', img.height)
print('Palette:\t', img.palette)
print('Info:\t', img.info)

# 旋转图片
img = img.rotate(180)

img.save('r-4.3192.jpg')
