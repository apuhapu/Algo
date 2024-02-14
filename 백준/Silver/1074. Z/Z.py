N, r, c = map(int, input().split())
# 1 2
# 3 4  >> 사분면 이름

r_cri = 0
c_cri = 0
find_num = 0
while N  > 0:
    diff = 2 ** (N-1)
    if r_cri + diff > r:
        # 1사분면은 find_num을 바꿀 필요 없음
        # 2사분면
        if c_cri + diff <= c:
            find_num += 2 ** (2*N - 2)
            c_cri += diff
    else:
        r_cri += diff
        # 3사분면
        if c_cri + diff > c:
            find_num += 2 * 2 ** (2*N -2)
        # 4사분면
        else:
            find_num += 3 * 2 ** (2*N -2)
            c_cri += diff
    N -= 1

print(find_num)