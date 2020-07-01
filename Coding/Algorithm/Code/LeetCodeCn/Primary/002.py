# TITLE: 买卖股票的最佳时机 II
# TIP
#   - 计算最大利润
#   - 尽可能多的交易
#   - 不能同时参加多笔交易
# SOLVE
#   - 下降就卖出

class Solution:
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        res = 0
        for i in range(1, len(prices)):
            if prices[i] > prices[i-1]:
                res += prices[i] - prices[i-1]
        return res


if __name__ == '__main__':
    s = Solution()
    print(s.maxProfit([7, 1, 5, 3, 6, 4]))
    print(s.maxProfit([1, 2, 3, 4, 5]))
    print(s.maxProfit([7, 6, 4, 3, 1]))
    print(s.maxProfit([]))
    
