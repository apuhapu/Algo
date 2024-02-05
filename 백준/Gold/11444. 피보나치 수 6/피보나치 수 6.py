A = [[1,1],[1,0]]

def multiMatrix(n):
    if n == 1:
        return A
    else:
        temp = multiMatrix(n//2)
        temp = oper(temp, temp)
        if n % 2 == 1:
            temp = oper(temp, A)
        return temp

def oper(A, B):
    moduler = 1000000007
    C = [[0,0],[0,0]]
    C[0][0] = (A[0][0]*B[0][0] + A[0][1]*B[1][0]) % moduler
    C[0][1] = (A[0][0]*B[0][1] + A[0][1]*B[1][1]) % moduler
    C[1][0] = (A[1][0]*B[0][0] + A[1][1]*B[1][0]) % moduler
    C[1][1] = (A[1][0]*B[0][1] + A[1][1]*B[1][1]) % moduler
    return C

n = int(input())
mat = multiMatrix(n)
print(mat[0][1])