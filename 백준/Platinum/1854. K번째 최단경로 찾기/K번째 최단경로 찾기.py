import sys
import heapq
input = sys.stdin.readline

v, e, k = map(int, input().rstrip().split())
graph = [[] for _ in range(v+1)]

for _ in range(e):
    a, b, w = map(int, input().rstrip().split())
    graph[a].append((w,b))

start = 1

def dijkstra(graph, start, k):
    dis = [[] for _ in range(v+1)]
    heapq.heappush(dis[start], (0,0))
    heap = []
    heapq.heappush(heap,(0, start))

    while heap:
        # x : 꺼낸 w, y : 꺼낸 노드
        x, y = heapq.heappop(heap)

        # 꺼낸 w가 기존 dis값 k개 중 가장 큰 것 보다 크면 무시
        if len(dis[y]) == k:
            z1, z = dis[y][0]
            if z < x:
                continue

        # 꺼낸 노드와 연결된 간선
        for w, b in graph[y]:
            distance = x + w
            
            # distance가 dis[b] 최대값 보다 작으면 갱신
            if len(dis[b]) < k:
                heapq.heappush(dis[b], (-distance, distance))
                heapq.heappush(heap, (distance, b))
            elif dis[b][0][1] > distance:
                heapq.heappop(dis[b])
                heapq.heappush(dis[b], (-distance, distance))
                heapq.heappush(heap, (distance, b))                

    return dis
    
output = dijkstra(graph, start, k)

for lst in output[1:]:
    if len(lst) < k:
        print(-1)
    elif lst[0][1] == 987654321:
        print(-1)
    else:
        print(lst[0][1])