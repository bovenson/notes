class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        return reversed(str(x))


if __name__ == '__main__':
    s = Solution()
    res = s.reverse(-123)
    print(res)