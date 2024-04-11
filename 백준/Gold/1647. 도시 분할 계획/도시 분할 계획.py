import sys
import heapq as hq
input = sys.stdin.readline

def findRoot(n):
    if nodes[n] == n:
        return n
    else:
        return findRoot(nodes[n])

def kruskal():
    global nodes
    nodes = [i for i in range(v+1)]
    hights = [1] * (v+1)
    cntNode = 0
    cntWeight = 0
    while cntNode < v-2:  # 마을을 두 부분으로 나눠야 하기 때문
        w, a, b = hq.heappop(edges)
        aa = findRoot(a)
        bb = findRoot(b)
        if aa == bb:  # arise cycle
            pass
        else:
            cntNode += 1
            cntWeight += w
            if hights[aa] >= hights[bb]:
                nodes[bb] = aa
                hights[aa] += 1
            else:
                nodes[aa] = bb
                hights[bb] += 1

    return cntWeight

v, e = map(int, input().rstrip().split())
edges = []  # (w,a,b)
for _ in range(e):
    a, b, c = map(int, input().rstrip().split())
    hq.heappush(edges, (c,a,b))
    
print(kruskal())