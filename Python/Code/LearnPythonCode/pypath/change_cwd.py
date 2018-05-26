#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: change_cwd.py
DATE: 17-9-6 下午3:02
DESC: 
"""

from contextlib import contextmanager

@contextmanager
def cwd(path):
    from os import getcwd, chdir
    cwd = getcwd()
    chdir(path)
    yield
    chdir(cwd)

# >>> os.getcwd()
# '/home/user'
#
# >>> with cwd('/usr/share'):
# ...     print(os.path.abspath('./test'))
# ...
# /usr/share/test
#
# >>> os.getcwd()
# '/home/user'
