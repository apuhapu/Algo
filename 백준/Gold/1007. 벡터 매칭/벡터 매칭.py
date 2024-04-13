import sys
input = sys.stdin.readline

def sum_vectors(s_x,s_y):
    return ((s_x)**2+(s_y)**2)**0.5

def cal_v(visited):
    x, y = 0, 0
    for i in range(n):
        if visited[i]:
            x -= v[i][0]
            y -= v[i][1]
        else:
            x += v[i][0]
            y += v[i][1]
    return sum_vectors(x,y)

def pick_vectors(cnt, key):
    global min_sum
    if cnt == n//2:
        min_sum = min(min_sum, cal_v(check))
    else:
        for i in range(key, n):
            if check[i]:
                continue
            else:
                if n-i+1 < n//2 - cnt: # 가지치기
                    break
                check[i] = True
                pick_vectors(cnt+1, i+1)
                check[i] = False
                    
t = int(input())
for _ in range(t):
    n = int(input())
    v = []
    m_a, m_b = 0, 0
    for _ in range(n):
        a, b = map(int, input().rstrip().split())
        v.append((a,b))
                    
    check = [False]*n
    min_sum = 20*10**5

    pick_vectors(0, 0)
    print(min_sum)