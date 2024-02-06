from collections import deque

def compare(x, y):
    if x == 0:
        return y
    elif y == 0:
        return x
    else:
        return min(x, y)

N, K = map(int, input().split())
if N >= K:
    print(N-K)
    print(1)
else:
    lst = [0] * min(100000+1,2*K-N+1)
    visited = [100001] * min(100000+1,2*K-N+1)
    deq = deque([(N,0)])

    while deq:
        p, q = deq.popleft()
        if q <= visited[p]:
            visited[p] = q

            if p - 1 >= 0 and p - 1 != N:
                if p - 1 == K:
                    if lst[K] == lst[p] + 1:
                        cnt += 1
                    elif lst[K] < lst[p] + 1 and lst[K] != 0:
                        pass
                    elif lst[K] > lst[p] + 1:
                        cnt = 1
                        lst[K] = lst[p] + 1
                    else:
                        cnt = 1
                        lst[K] = lst[p] + 1
                else:
                    deq.append((p - 1, q + 1))
                    lst[p - 1] = compare(lst[p - 1], lst[p] + 1)

            if p + 1 < len(visited) and p + 1 != N:
                if p + 1 == K:
                    if lst[K] == lst[p] + 1:
                        cnt += 1
                    elif lst[K] < lst[p] + 1 and lst[K] != 0:
                        pass
                    elif lst[K] > lst[p] + 1:
                        cnt = 1
                        lst[K] = lst[p] + 1
                    else:
                        cnt = 1
                        lst[K] = lst[p] + 1
                else:
                    deq.append((p + 1, q + 1))
                    lst[p + 1] = compare(lst[p + 1], lst[p] + 1)

            if 2 * p < len(visited) and 2 * p != N:
                if 2 * p == K:
                    if lst[K] == lst[p] + 1:
                        cnt += 1
                    elif lst[K] < lst[p] + 1 and lst[K] != 0:
                        pass
                    elif lst[K] > lst[p] + 1:
                        cnt = 1
                        lst[K] = lst[p] + 1
                    else:
                        cnt = 1
                        lst[K] = lst[p] + 1
                else:              
                    deq.append((2 * p, q + 1))
                    lst[2 * p] = compare(lst[2 * p], lst[p] + 1)

    print(lst[K])
    print(cnt)