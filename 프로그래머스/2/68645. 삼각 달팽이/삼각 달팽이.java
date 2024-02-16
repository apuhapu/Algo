class Solution {
    static int[] dr = {1,0,-1};
	static int[] dc = {0,1,-1};
    
    public int[] solution(int n) {
		int[][] tri = new int[n][];
		for (int i=0; i<n; i++) {
			tri[i] = new int[i+1];
		}
		int max = n*(n+1)/2;
		int currR = 0;
		int currC = 0;
		int dir = 0;
		tri[0][0] = 1;
		for (int cnt=2; cnt<=max; cnt++) {
			while (true) {
				currR += dr[dir];
				currC += dc[dir];
				if (currR>=n || currC>=n || tri[currR][currC] != 0) {
					currR -= dr[dir];
					currC -= dc[dir];
					dir = (dir+1)%3;
					continue;
				}
				break;
			}		
			tri[currR][currC] = cnt;
		}
		int[] answer = new int[max];
		for (int i=0; i<n; i++) {
			int f = i*(i+1)/2;
			for (int j=0; j<=i; j++) {
				answer[f+j] = tri[i][j];
			}
		}
        return answer;
    }
}