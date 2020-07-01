a, b, k = [int(i) for i in input().split()]

def isBeauty(num, a, b):
  while(num > 1):
    if num % 10 != a and num % 10 != b:
      return False
    num //= 10
  return True

res = 0
for i in range(k+1):
  j = k - i
  sum = i * a + j * b
  if isBeauty(sum, a, b):
    m = min(i, j);
    t = 1
    for p in range(m):
      t *= k - p
    for p in range(2, m+1):
      t //= p
    res += t
    res %= 1000000007
print(res)
