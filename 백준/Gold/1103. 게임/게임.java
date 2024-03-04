import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n,m,max=0;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static char[][] map;
	static boolean[][] visited;
	static int[][] visited2;
	static Pos coin = new Pos(0, 0, 0);
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for (int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		visited = new boolean[n][m];
		visited2 = new int[n][m];
		visited[0][0] = true;
		move(coin);
		System.out.println(max);
		
	}
	
	private static void move(Pos curr) {
		if (max==-1) {
			return;
		}
		max = Math.max(max, curr.cnt+1);
		int diff = map[curr.r][curr.c]-'0';
		for (int i=0; i<4; i++) {
			int r = curr.r + diff*dr[i];
			int c = curr.c + diff*dc[i];
			if (r<0||r>=n||c<0||c>=m) { // 범위 밖
				continue;
			}
			if (visited[r][c]) { // 사이클 발생
				max=-1;
				return;
			}
			if (map[r][c] == 'H') { // 구멍
				continue;
			}
			if (visited2[r][c]>0&&visited2[r][c]>=curr.cnt+1) {
				continue;
			}
			visited2[r][c] = curr.cnt+1;
			visited[r][c] = true;
			move(new Pos(r,c,curr.cnt+1));
			visited[r][c] = false;
		}
	}

	static class Pos {
		int r;
		int c;
		int cnt;
		public Pos(int r, int c, int cnt) {
			this(r,c);
			this.cnt = cnt;
		}
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}