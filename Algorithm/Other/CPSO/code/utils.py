#!/bin/python3
# coding: utf-8

"""
"""
import copy

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-16 15:54"


# def list_index(l: list, element)->int:
#     # print(l)
#     element = str(element)
#     # print(element)
#     for _i in range(len(l)):
#         if str(l[_i]) == element:
#             return _i
#         # else:
#         #     print(l[_i], ' <--> ', element)
#     raise ValueError(str(element) + ' is not in list')


def get_swap_pair(l1: list, l2: list)->list:
    """l1, l2为两个元素集合相同的序列, 返回l2变成l1需要交换的位置对 l1 - l2"""
    # print(l1)
    # print(l2)
    _l1 = copy.deepcopy(l1)
    _l2 = copy.deepcopy(l2)
    # print(type(_l1[0]))
    # print(type(_l2[0]))
    # print(_l1)
    # print(_l2)
    if len(_l1) != len(_l2):
        raise Exception("长度不同的序列不可转换")

    _res = []
    for i in range(len(_l2)):
        if _l1[i] != _l2[i]:
            # print(_l2)
            _swap_pos = _l2.index(_l1[i])
            t = _l2[i]
            _l2[i] = _l2[_swap_pos]
            _l2[_swap_pos] = t
            _res.append((i, _swap_pos))
    return _res


if __name__ == "__main__":
    # gl1 = [5, 3, 4, 2, 1]
    # gl2 = [2, 3, 1, 4, 5]
    gl1 = [20, 820, 719, 978, 586, 243, 887, 440, 816, 519, 841, 925, 247, 229, 225, 766, 579, 249, 650, 483, 408, 2000, 839, 606, 825, 798, 740, 513, 740, 214, 786, 54, 276, 703, 865, 760, 274, 318, 408, 614, 608, 443, 207, 7, 717, 422, 829, 283, 294, 611]
    gl2 = [798, 820, 719, 978, 586, 408, 243, 440, 20, 247, 841, 443, 579, 229, 225, 513, 703, 249, 650, 483, 887, 2000, 608, 214, 825, 816, 740, 766, 740, 7, 786, 283, 276, 519, 865, 760, 54, 318, 408, 614, 839, 422, 207, 606, 717, 925, 829, 274, 294, 611]

    res = get_swap_pair(gl1, gl2)
    print(res)
