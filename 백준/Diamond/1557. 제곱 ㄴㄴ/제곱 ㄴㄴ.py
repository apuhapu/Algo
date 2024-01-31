# 입력파트
k = int(input())

# 필요한 i의 범위 >> sqrt(2k)+1 / 

ran = int((2*k)**(0.5))+1

# 뫼비우스 함수
mu = [0] * ran
mu[1] = 1
for i in range(1,ran):
    for j in range(2*i,ran,i):
        mu[j] -= mu[i]

# x이하의 square_free 정수 개수 구하는 함수
def square_free(x):
    cnt = 0
    i = 1
    while i*i <= x:
        cnt += mu[i]*(x//(i*i))
        i += 1
    return cnt

# 이분탐색
start = k # k보다 작을 수 없으므로 & 이론상 1부터 n까지 square_free 수의 개수는 약 60%*n / 10**9의 경우 607927124개
end = int(2*k)
while start <= end:
    mid = (start+end) // 2
    if square_free(mid) == k:
        if square_free(mid -1) == k:
            end = mid -1  # 가장 작은 수를 찾아야 하기 때문
        else:
            print(mid)
            break
    elif square_free(mid) < k:
        start = mid + 1
    else:
        end = mid - 1
else:
    print(end)