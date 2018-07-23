# coding: utf-8

"""
heap sort
"""

def heapsort(arr):
    # 构建最大堆
    for i in range(len(arr)//2-1, -1, -1):
        adjust(arr, i, len(arr))

    # 调整堆结构+交换堆顶元素与末尾元素
    for j in range(len(array)-1):
        t = arr[0]
        arr[0] = arr[j]
        arr[j] = arr[0]

        adjust(arr, 0, j)


def adjust(arr, l, r):
    # 取出当前元素
    tmp = arr[l]
    k = i * 2 + 1
    while k < r:
        if 


if __name__ == '__main__':
    l = [0, -1, 5, 3, 2, 9, 7, 8, 4]
    heapsort(l)
