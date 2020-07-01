def has(num):
    res = 0
    for o in range(1, num+1):
        if num % o == 0:
            res += 1
    return res
z = int(input())
def solve(n):
    if n == 1:
        # print(1)
        pass
    elif n == 2:
        # print(2)
        pass
    else:
        arr = [1]
        i, j, k = 0, 0, 1
        while n - 1 > k:
            if arr[i] * 2 <= arr[j] * 3:
                arr.append(arr[i] * 2)
                i += 1
            else:
                arr.append(arr[j] * 3)
                j += 1
            k += 1
        arr.append(arr[k-1] * 2 if arr[k-1] % 2 == 1 else arr[k-1] * 3)
        # arr.append(min(arr[k-1] * 2, arr[k-1] * 3))
        # arr.append(min(arr[k-1] * 3, arr[k-1] * 2, arr[k-2] * 3, arr[k-2] * 2))
        print(n, ' * ', arr[k])
        for q in range(arr[k]):
            if has(q) >= n:
                print(n, ' - ', q)
        if has(arr[k]) != n:
            print(n, " ERROR")

for l in range(1, z+1):
    solve(l)
    print('----')