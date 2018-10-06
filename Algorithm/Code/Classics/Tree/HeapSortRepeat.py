def hsort(arr):
    length = len(arr)
    for i in range(length//2-1, -1, -1):
        adjust(arr, i, length)

    for i in range(length-1, -1, -1):
        t = arr[i]
        arr[i] = arr[0]
        arr[0] = t
        adjust(arr, 0, i)


def adjust(arr, l, size):
    k = l * 2 + 1
    while k < size:
        if k+1<size and arr[k] < arr[k+1]:
            k += 1
        if arr[l] > arr[k]:
            break
        t = arr[l]
        arr[l] = arr[k]
        arr[k] = t
        l = k
        k = k * 2 + 1


if __name__ == '__main__':
    a = [-1, 0, 9, -5, -6, 8, -2, 2, 3, 0, 2, 1, 0]
    hsort(a)
    print(a)

