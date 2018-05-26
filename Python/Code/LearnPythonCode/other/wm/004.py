k, r, c = (int(i) for i in input().split())

m = [[0 for _ in range(c)] for _ in range(r)]
res = [-1, -1]

for i in range(r):
    j = 0
    for num in input().split():
         m[i][j] = int(num)
         j += 1


def solve(_r, _c):
    if _r >= r or _c >= c or m[_r][_c] > k or res[0] >= 0:
        return

    if m[_r][_c] == k:
        res[0] = _r
        res[1] = _c
    else:
        solve(_r, _c+1) or solve(_r+1, _c)

solve(0, 0)
print(res[0], res[1])
