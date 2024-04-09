import heapq as hq
import sys
input = sys.stdin.readline

# input part
"""
n : number of jewels
k : number of bags
"""
n, k = map(int, input().rstrip().split())

jewel_list = []
for _ in range(n):
    m, v = map(int, input().rstrip().split())
    hq.heappush(jewel_list, (m,v)) # heap

bag_list = []
for _ in range(k):
    bag_list.append(int(input()))
bag_list.sort()

# compare part
result = 0
respository = []
for bag in bag_list:
    while jewel_list and bag >= jewel_list[0][0]:
        hq.heappush(respository, -hq.heappop(jewel_list)[1])
        
    if respository:
        result -= hq.heappop(respository)
    

print(result)