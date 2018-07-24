def qsort(arr, l, r):
    if l >= r:
        return
    i, j = l, r
    pivot = arr[l]
    while i < j:
        while i < j and arr[j] > pivot: j -= 1
        arr[i] = arr[j]
        while i < j and arr[i] <= pivot:    i += 1
        arr[j] = arr[i]
    arr[i] = pivot
    qsort(arr, l, i-1)
    qsort(arr, i+1, r)

if __name__ == '__main__':
    a = [-1, 0, 5, 3, 2, 9, 8, 3, 2, -1, 9, 0]
    qsort(a, 0, len(a)-1)
    print(a)

