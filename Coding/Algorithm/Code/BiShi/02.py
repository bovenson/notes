n, X = [int(i) for i in input().split()]
foods = [int(i) for i in input().split()]

res = -1;

def solve(cur, cnt):
    global res
    if cnt > X:
        if res == -1:
            res = cnt
        else:
            res = min(res, cnt)
        return
    if cur >= len(foods):
        return
    solve(cur+1, cnt)
    solve(cur+1, cnt + foods[cur])
  
solve(0, 0)
print(res)