class Solution {
    
    static int cnt;
    
    public int solution(int n) {
        cnt = 0;
        if (n%2==0) { // 3*k+2 >> 홀수
            return 0;
        }
		count(2,n-2); // 무조건 마지막은 ++임
        return cnt;
    }
    
    private static void count(int h, int currN) {
		if (currN<3 || currN<Math.pow(3, h/2)) {
			return;
		}
		if (currN==3) {
			if (h == 2) {
				cnt++;
			}
			return;
		}
		
		if (currN%3==0 && h>=2) {
			count(h-2, currN/3);
		}
		count(h+1, currN-1);
	}
}