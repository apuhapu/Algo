from collections import deque
import sys
input = sys.stdin.readline

# 입력
n = int(input())
graph = [list(map(int, input().rstrip().split())) for _ in range(n)]

# 1순위 윗 방향 2순위 왼쪽 방향(추가로 과정이 필요함)
dr = [-1,0,0,1]
dc = [0,-1,1,0]

# 초기 상어 위치
tic = False
for i in range(n):
    for j in range(n):
        if graph[i][j] == 9:
            x, y = i, j
            tic = True
            graph[i][j] = 0
            break
    if tic:
        break

t = 0
size = 2
stomach = 0

def bfs():
    global x,y,size,stomach
    visited = [[-1] * n for _ in range(n)]
    visited[x][y] = 0
    que = deque([(x,y)])

    # 최단거리를 찾았을 때 r,c의 좌표와 그때의 거리
    max_r, max_c = n, n
    dis = 401

    # 최단거리를 찾았을 때 True로 바뀌는 스위치
    switch = False

    while que:
        a, b = que.popleft()
        # 최단거리보다 짧은지 확인 >> 이것보다 멀다면 탐색할 이유가 없음
        if visited[a][b] < dis:
            for i, j in zip(dr,dc):
                r = a + i
                c = b + j
                # grpah안에 있고 방문 여부 확인
                if 0 <= r < n and 0 <= c < n and visited[r][c] == -1:

                    # case 1 : 먹이 획득(size보다 작은 먹이 발견 시)
                    if 0 < graph[r][c] < size:
                        dis = visited[a][b] + 1 # 최단거리 변경

                        # 같은 거리의 먹이보다 위에 있고 왼쪽에 있는지 확인
                        if r < max_r or (r == max_r and c < max_c):
                            max_r, max_c = r, c
                            switch = True
                            visited[r][c] = visited[a][b] + 1
                        # 기존 최단거리보다 우선순위에서 밀려날 때
                        else:
                            visited[r][c] = -2

                    # case 2 : 통과하는 경우(먹이와 size가 같거나 먹이가 없는 경우)
                    elif graph[r][c] == size or graph[r][c] == 0:
                        visited[r][c] = visited[a][b] + 1
                        que.append((r,c))
                    # case 3 : 먹이의 크기가 size보다 커서 통과를 못하는 경우
                    else:
                        visited[r][c] = -2
    else:
        # 먹이 발견 시
        if switch:
            graph[max_r][max_c] = 0   # 먹이 칸을 0으로 변경
            stomach += 1              # 먹이 먹은 표시

            # 상어 size 성장
            if stomach == size:
                size += 1
                stomach = 0
            
            x, y = max_r, max_c # 새롭게 상어 위치 좌표 변경
            return visited[x][y]
            
        # 먹이를 발견 못했을 경우
        else:
            return 0

# 출력파트
while True:
    dt = bfs()
    if dt == 0:
        print(t)
        break
    else:
        t += dt