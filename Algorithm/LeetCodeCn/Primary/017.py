"""
字符串转整数（atoi）
实现 atoi，将字符串转为整数。

在找到第一个非空字符之前，需要移除掉字符串中的空格字符。如果第一个非空字符是正号或负号，选取该符号，并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。如果第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。

当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。

若函数不能执行有效的转换，返回 0。

说明：

假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。如果数值超过可表示的范围，则返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

示例 1:

输入: "42"
输出: 42
示例 2:

输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
示例 3:

输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
示例 4:

输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
示例 5:

输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。
"""


class Solution:
    def myAtoi(self, s):
        """
        :type str: str
        :rtype: int
        """
        s = s.strip()
        flag = False
        if len(s) > 0 and s[0] in '+-':
            flag = False if s[0] == '+' else True
            s = s[1:]
        res = 0
        i = 0
        while i < len(s) and s[i].isdigit():
            i += 1
        if i > 0:
            res = int(s[:i])
        res = res if not flag else -res
        if res < pow(-2, 31):
            res = pow(-2, 31)
        elif res >= pow(2, 31):
            res = pow(2, 31) - 1
        return res


if __name__ == '__main__':
    s = Solution()
    res = s.myAtoi('w -91283472332')
    print(res)

"""
import re
matcher = re.compile(r'^ *([-\+]?\d+)')

class Solution:
    def myAtoi(self, str):
        '''
        Using the stupid stub that the site provided, overriding `str`.
        '''
        match = matcher.match(str)

        if not match:
            return 0
        else:
            num = int(match.group(1))
            if num > 0:
                return min(num, 2**31 - 1)
            elif num < 0:
                return max(num, -2**31)
            else:
                return 0
"""
