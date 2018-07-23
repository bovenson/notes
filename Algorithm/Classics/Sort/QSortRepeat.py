def qsort(arr, l, r):
    if l >= r:
        return
    i, j = l, r
    base = arr[i]
    while i < j:
        while i < j and arr[j] > base:  j -= 1
        arr[i] = arr[j]
        while i < j and arr[i] <= base: i += 1
        arr[j] = arr[i]
    arr[j] = base
    qsort(arr, l, j-1)
    qsort(arr, j+1, r)


if __name__ == '__main__':
    ar = [0, 4, 2, 1, 3, 1, 5, 5, 9, 7, 6]
    qsort(ar, 0, len(ar)-1)
    print(ar)
