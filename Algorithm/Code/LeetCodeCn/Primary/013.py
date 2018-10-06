"""
给定一个 32 位有符号整数，将整数中的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
"""

class Solution:
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        i = 1
        res = 0
        if x < 0:
            x = -x
            flag = True
        else:
            flag = False
        while abs(x) > 0:
            res *= 10
            res += x % pow(10, i)
            x //= pow(10, i)
        if flag:
            res = -res
        if res < pow(-2, 31) or res >= pow(2, 31):
            return 0
        return res


if __name__ == '__main__':
    s = Solution()
    res = s.reverse(-11234)
    print(res)
