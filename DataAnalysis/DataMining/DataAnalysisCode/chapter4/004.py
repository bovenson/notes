#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: 004.py
DATE: 17-9-27 下午2:34
DESC: Feature extraction using wavelet analysis
"""
from scipy.io import loadmat
import pywt

input_file = './data/leleccum.mat'


mat = loadmat(input_file)
signal = mat['leleccum'][0]

coeffs = pywt.wavedec(signal, 'bior3.7', level=5)   # 返回结果为level+1个数组，第一个数组为逼近系数数组，后面的一次是系数数组
print(coeffs)
