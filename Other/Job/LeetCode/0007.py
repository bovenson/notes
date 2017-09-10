class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        x_s = str(x)
        _res = x_s[::-1] if x_s[0] != '-' else '-' + x_s[:0:-1]
        if int(_res) > pow(2, 31) or - pow(2, 31) > int(_res) - 1:
            return 0
        else:
            return int(_res)


if __name__ == '__main__':
    s = Solution()
    res = s.reverse(-1534236469)
    res = s.reverse(-2147483648)
    print(res)

    # 2147483648