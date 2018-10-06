# quick sort

def qsort(arr, l, r):
    if l < r:
        i, j = l, r
        key = arr[l]

        while i < j:
            while i < j and arr[j] > key:
                j -= 1
            arr[i] = arr[j]

            while i < j and arr[i] <= key:
                i += 1
            arr[j] = arr[i]

        arr[j] = key
        qsort(arr, l, j-1)
        qsort(arr, j+1, r)


if __name__ == '__main__':
    ar = [1, 4, 3, 2, 6, 0, 6, 5, 8]
    qsort(ar, 0, len(ar)-1)
    print(ar)

