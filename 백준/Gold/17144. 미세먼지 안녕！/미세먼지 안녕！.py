import sys
# input = sys.stdin.readline

# 입력파트
r, c, t = map(int, input().rstrip().split())
board = [list(map(int, input().rstrip().split())) for _ in range(r)]

dr = [1,-1,0,0]
dc = [0,0,1,-1]

def isinside(x,y):
    if 0<=x<r and 0<=y<c and board[x][y] != -1:
        return True
    else:
        return False

def diffusion(new_board):
    board = [l[:] for l in new_board]
    for i in range(r):
        for j in range(c):
            if board[i][j] == -1:
                pass
            else:
                if board[i][j] < 5:
                    pass
                else:
                    cnt = 0
                    for k in range(4):
                        rr = i + dr[k]
                        cc = j + dc[k]
                        if isinside(rr,cc):
                            cnt += 1
                            new_board[rr][cc] += board[i][j]//5
                    else:
                        new_board[i][j] -= cnt * (board[i][j]//5)

def oncleaner():
    global dust
    for i in range(2):
        a = air_cleaner[i]
        if i == 0:  # counterclockwise
            dust -= new_board[a-1][0]  # 정화된 공기
            for p in range(a-1,0,-1):  # 아래로
                new_board[p][0] = new_board[p-1][0]
            for q in range(c-1):  # 왼쪽으로
                new_board[0][q] = new_board[0][q+1]
            for s in range(a):  # 위로
                new_board[s][c-1] = new_board[s+1][c-1]
            for t in range(c-1, 1, -1): # 오른쪽으로
                new_board[a][t] = new_board[a][t-1]
            new_board[a][1] = 0 # 공기청정기에서 나온 공기
        else: # clockwise
            dust -= new_board[a+1][0]
            for s in range(a+1,r-1):  # 위로
                new_board[s][0] = new_board[s+1][0]
            for q in range(c-1):  # 왼쪽으로
                new_board[r-1][q] = new_board[r-1][q+1]
            for p in range(r-1,a,-1):  # 아래로
                new_board[p][c-1] = new_board[p-1][c-1]
            for t in range(c-1, 1, -1): # 오른쪽으로
                new_board[a][t] = new_board[a][t-1]
            new_board[a][1] = 0 # 공기청정기에서 나온 공기

new_board = [[0]*c for _ in range(r)]
air_cleaner = []  # 공기청정기 c 좌표
dust = 0  # 남은 먼지

# 1차 발산
for i in range(r):
    for j in range(c):
        if board[i][j] == -1: # 공기청정기 있대
            air_cleaner.append(i)
            new_board[i][j] = -1
        else:
            dust += board[i][j]
            if board[i][j] < 5:
                new_board[i][j] += board[i][j]
            else:
                cnt = 0
                for k in range(4):
                    rr = i + dr[k]
                    cc = j + dc[k]
                    if isinside(rr,cc):
                        cnt += 1
                        new_board[rr][cc] += board[i][j]//5
                else:
                    new_board[i][j] += board[i][j] - cnt * (board[i][j]//5)

oncleaner()

# 출력파트
if t == 1:
    print(dust)
else:
    for _ in range(t-1):
        board = [l[:] for l in new_board]
        diffusion(new_board)
        oncleaner()
    else:
        print(dust)