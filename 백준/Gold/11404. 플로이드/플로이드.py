# 입력파트
n = int(input())
m = int(input())
Inf = int(10**7)
graph = [[Inf] * (n+1) for _ in range(n+1)]

# 자기에서 자기는 0
for x in range(1,n+1):
    graph[x][x] = 0

# 그래프 채우기
for _ in range(m):
    a, b, c = map(int,input().rstrip().split())
    graph[a][b] = min(c, graph[a][b])

# 플로이드 워셜
for k in range(1,n+1):
    for a in range(1,n+1):
        for b in range(1,n+1):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

# 출력 파트
for i in range(1,n+1):
    for j in range(1,n+1):
        if graph[i][j] != Inf:
            print(graph[i][j], end = ' ')
        else:
            print(0, end = ' ')
    else:
        print()