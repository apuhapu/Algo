from collections import deque
import sys

input = sys.stdin.readline

def bfs(k):
    global cnt
    que = deque([k])
    visited[k] = True
    while que:
        x = que.popleft()
        for y in new_graph[x]:
            if not visited[y]:
                visited[y] = True
                que.append(y)
    cnt += 1

n, m = map(int, input().rstrip().split())
graph = [[]*m for _ in range(n)]
for i in range(n):
    graph[i] = list(str(input().rstrip()))

new_graph = [[] for _ in range(n*m)]  # 2차원 idx >> 1차원 idx

idx = 0
for i in range(n):
    for j in range(m):
        if graph[i][j] == 'U':
            new_graph[idx].append(idx-m)
            new_graph[idx-m].append(idx)
        elif graph[i][j] == 'D':
            new_graph[idx].append(idx+m)
            new_graph[idx+m].append(idx)
        elif graph[i][j] == 'L':
            new_graph[idx].append(idx-1)
            new_graph[idx-1].append(idx)
        elif graph[i][j] == 'R':
            new_graph[idx].append(idx+1)
            new_graph[idx+1].append(idx)
        idx += 1

cnt = 0
visited = [False]*(n*m)
for i in range(n*m):
    if not visited[i]:
        bfs(i)

print(cnt)