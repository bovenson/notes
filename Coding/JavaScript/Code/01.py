k = int(input())
for i in range(k):
  s = set()
  n = int(input())
  sl = [int(r) for r in input().split()]
  for item in sl:
    s.add(item)
  if len(s) > 3:
    print("NO")
  elif len(s) < 3:
    print("YES")
  else:
    ll = []
    for i in s:
      ll.append(i)
    ll.sort()
    if ll[1] * 2 == ll[0] + ll[2]:
      print("YES")
    else:
      print("NO")