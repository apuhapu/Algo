import sys
n = int(sys.stdin.readline())
lst = []
for _ in range(n):
    lst.append(sys.stdin.readline().rstrip())
slst = list(set(lst))
slst.sort()
slst.sort(key=len)

for ii in slst:
    print(ii)