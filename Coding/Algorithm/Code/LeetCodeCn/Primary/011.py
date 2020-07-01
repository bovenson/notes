"""
旋转图像

给定一个 n × n 的二维矩阵表示一个图像。
将图像顺时针旋转 90 度。

说明：
你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:

给定 matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
"""


class Solution:
    def rotate(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: void Do not return anything, modify matrix in-place instead.
        """

        def next(tx, ty):
            return (n - 1 - ty, tx)

        n = len(matrix)
        cnt = 0
        for x in range(n):
            for y in range(x+1, n-x):
                nx, ny = next(x, y)
                cnt += 1
                while not (nx == x and ny == y) and cnt < n * n:
                    tv = matrix[ny][nx]
                    matrix[ny][nx] = matrix[y][x]
                    matrix[y][x] = tv
                    nx, ny = next(nx, ny)
                    cnt += 1
                if cnt >= n * n:
                    return

    def rotate_other(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: void Do not return anything, modify matrix in-place instead.
        """

        length = len(matrix) - 1

        def rotation(matrix, p, y):
            # 每次旋转外围一圈，p作为起始点，依次为(0, 0), (1, 1), (2, 2)...
            # y作为外圈的另一个点，在p的对角上
            # 每次旋转4个点（值交换）
            if p[1] < y:
                matrix[p[0]][p[1]], matrix[p[1]][y], matrix[y][length - p[1]], matrix[length - p[1]][p[0]] = (
                    matrix[length - p[1]][p[0]], matrix[p[0]][p[1]], matrix[p[1]][y], matrix[y][length - p[1]]
                )

                rotation(matrix, [p[0], p[1] + 1], y)

        for i in range(len(matrix) // 2):  # 后部分已经算过
            rotation(matrix, [i, i], length - i)


if __name__ == '__main__':
    s = Solution()
    m = [
        [1, 2, 3],
        [4, 5, 6],
        [7, 8, 9]
    ]
    m = [
        [5, 1, 9, 11],
        [2, 4, 8, 10],
        [13, 3, 6, 7],
        [15, 14, 12, 16]
    ]
    s.rotate(m)
    print(m)
