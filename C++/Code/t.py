class Bind:
    def __init__(self, lv, cm):
        self.level = lv
        self.commision = cm

    def __gt__(self, other):
        return self.level > other.level

    def __str__(self):
        return str(self.level)

def solve(level, commision, worker):
    binds = []
    maxv = []
    res = 0

    for i in range(len(level)):
        binds.append(Bind(level[i], commision[i]))

    binds.sort()
    worker.sort()

    for bind in binds:
        maxv.append(bind.commision if len(maxv) == 0 else max(maxv[-1], bind.commision))
    i = 0
    for wk in worker:
        while lvl[i] <= wk: i += 1
        if i > 0:   res += maxv[i-1]
    return res

lvl = [1,2,3,4,7]
cms = [2,1,15,20,25]
wkr = [2,3,4,6]
print(solve(lvl, cms, wkr))
