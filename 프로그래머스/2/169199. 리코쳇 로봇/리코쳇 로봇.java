import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    static final int[] dr = {1,-1,0,0};
	static final int[] dc = {0,0,1,-1};
    
    public int solution(String[] board) {
        Pos R = null, G = null;
		int n = board.length;
		int m = board[0].length();
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (board[i].charAt(j) == 'R') {
					R = new Pos(i,j);
				} else if (board[i].charAt(j) == 'G') {
					G = new Pos(i,j);
				}
			}
		}
		boolean[][] visited = new boolean[n][m];
		Queue<Pos> que = new ArrayDeque<>();
		que.add(R);
		visited[R.r][R.c] = true;
		int answer = -1;
		BFS:
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			for (int i=0; i<4; i++) {
				int r = curr.r;
				int c = curr.c;
				while (true) {
					r += dr[i];
					c += dc[i];
					if (r<0 || r>=n || c<0 || c>=m || board[r].charAt(c)=='D') {
						r -= dr[i];
						c -= dc[i];
						break;
					}
				}
				if (r == G.r && c == G.c) {
					answer = curr.cnt+1;
					break BFS;
				}
				if (visited[r][c]) {
					continue;
				}
				visited[r][c] = true;
				que.add(new Pos(r,c,curr.cnt+1));
			}
		}
        return answer;
    }
    class Pos {
		int r;
		int c;
		int cnt;
		public Pos(int r, int c) {
			this(r,c,0);
		}
		public Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
}