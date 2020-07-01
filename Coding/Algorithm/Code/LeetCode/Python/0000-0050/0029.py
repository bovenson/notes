#!/bin/python3
# coding: utf-8

"""
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
"""
import re

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-19 13:45"


# class Solution(object):
#     def divide(self, dividend, divisor):
#         """
#         :type dividend: int
#         :type divisor: int
#         :rtype: int
#         """
#         def bg_eq(bin_a, bin_b):
#             return int(bin_a) >= int(bin_b)
#
#         def sub_bin(bin_a, bin_b):
#             bin_a = bin_a[::-1]
#             bin_b = bin_b[::-1]
#             carry = 0
#             res = ''
#             for index in range(len(bin_a)):
#                 _a = int(bin_a[index])
#                 _b = int(bin_b[index]) if len(bin_b) > index else 0
#                 if carry + _a < _b:
#                     res += str(carry + _a - _b + 2)
#                     carry = -1
#                 else:
#                     res += str(carry + _a - _b)
#                     carry = 0
#             res = res[::-1]
#             import re
#             res = re.sub('^0*', '', res)
#             return res
#
#         bin_dividend = bin(abs(dividend))[2:]
#         bin_divisor = bin(abs(divisor))[2:]
#         # print bin_dividend
#         if dividend > 2147483647 or dividend < -2147483648:
#             return 2147483647
#         if divisor > 2147483647 or divisor < -2147483648:
#             return 2147483647
#         # print len(bin_dividend)
#         # print bin_dividend
#
#         # binary division
#         i = 0
#         dividend_len = len(bin_dividend)
#         sub_dividend = ''
#         res = ''
#         # flag = False
#         # print bin_dividend
#         # print bin_divisor
#         while i <= dividend_len:
#             if len(sub_dividend) > 0 and bg_eq(sub_dividend, bin_divisor):
#                 # print 'aaa: ', sub_dividend
#                 # print 'bbb: ', bin_divisor
#                 sub_dividend = sub_bin(sub_dividend, bin_divisor)
#                 # print 'res: ', sub_dividend
#                 res += '1'
#                 # flag = False
#                 if i < dividend_len:
#                     sub_dividend += bin_dividend[i]
#                 i += 1
#             else:
#                 if i < dividend_len:
#                     # break
#                     sub_dividend += bin_dividend[i]
#                 res += '0'
#                 # if flag:
#                 #     res += '0'
#                 #     flag = False
#                 # elif len(res) > 0:
#                 #     flag = True
#                 i += 1
#
#         if len(res) == 0:
#             return 0
#         res = int(res, 2)
#         if (dividend < 0 and divisor > 0) or (dividend > 0 and divisor < 0):
#             res = -res
#         if res > 2147483647 or res < -2147483648:
#             return 2147483647
#         return res


class Solution(object):
    def divide(self, dividend, divisor):
        res = 0
        positive = (dividend < 0) is (divisor < 0)
        dividend, divisor = abs(dividend), abs(divisor)
        while dividend >= divisor:
            temp, i = divisor, 1
            while dividend >= temp:
                dividend -= temp
                res += i
                i <<= 1
                temp <<= 1
        if not positive:
            res = -res
        return min(2147483647, max(-2147483648, res))


if __name__ == "__main__":
    # print Solution().divide(10, 3)
    print Solution().divide(-10, 3)
    print Solution().divide(5, 2)
    print Solution().divide(1, 1)
    print Solution().divide(-2147483648, -1)
    print int(-2147483648)
    # print re.sub('^-?0b', '', bin(-10))
