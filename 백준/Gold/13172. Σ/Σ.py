import sys
input = sys.stdin.readline

def divGcd(a, b):
    # n > m
    if a < b:
        n, m = b, a
    else:
        n, m = a, b

    # 유클리드 알고리즘
    while True:
        mod = n % m
        if mod == 0:
            gcd = m
            break
        else:
            n, m = m, mod
    
    return a//gcd, b//gcd

def fact(a, b):
    if b == 0:
        return 1
    elif b == 1:
        return a
    elif b % 2 == 1:
        return (a * (fact(a, (b-1)/2)**2)) %1000000007
    else:
        return (fact(a, b/2) ** 2) % 1000000007

m = int(input())
sol = 0
for _ in range(m):
    n, s = map(int, input().rstrip().split())
    n, s = divGcd(n, s)
    n_inv = fact(n, 1000000007-2)
    sol += (s * n_inv) % 1000000007

print(sol%1000000007)