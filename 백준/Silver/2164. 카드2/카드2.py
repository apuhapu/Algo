from collections import deque
n = int(input())
lst = [x for x in range(1,n+1)]
deq = deque(lst)

while len(deq) > 1:
    deq.popleft()
    a = deq.popleft()
    deq.append(a)

print(deq[0])
    