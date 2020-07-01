# coding: utf-8

"""
heap sort
"""

def heapsort(arr):
    # 构建最大堆
    for i in range(len(arr)//2-1, -1, -1):
        adjust(arr, i, len(arr))

    # 调整堆结构+交换堆顶元素与末尾元素
    for j in range(len(arr)-1, -1, -1):
        t = arr[0]
        arr[0] = arr[j]
        arr[j] = t

        adjust(arr, 0, j)


def adjust(arr, l, size):
    # 取出当前元素
    k = l * 2 + 1
    while k < size:
        if k + 1 < size and arr[k] < arr[k+1]:
            k += 1
        if arr[l] > arr[k]:
            break
        t = arr[l]
        arr[l] = arr[k]
        arr[k] = t
        l = k
        k = k * 2 + 1


if __name__ == '__main__':
    l = [0, -1, 5, 3, 2, 9, 7, 8, 4]
    heapsort(l)
    print(l)
