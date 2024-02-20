import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int n;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static char[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int normalCnt = see(false);
		int colorErrorCnt = see(true);
		System.out.println(normalCnt + " " + colorErrorCnt);
	}

	private static int see(boolean isError) {
		visited = new boolean[n][n];
		int bdcnt = 0;
		for (int r=0; r<n; r++) {
			for (int c = 0; c < n; c++) {
				if (!visited[r][c]) {
					bfs(r,c,isError);
					bdcnt++;
				}
			}
		}
		return bdcnt;
	}

	private static void bfs(int r, int c, boolean isError) {
		visited[r][c] = true;
		Queue<Pair> que = new ArrayDeque<>();
		que.add(new Pair(r, c));
		char color = map[r][c];
		while (!que.isEmpty()) {
			Pair curr = que.poll();
			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				if (nr<0 || nr>=n || nc<0 || nc>=n || visited[nr][nc]) {
					continue;
				}
				if (color == map[nr][nc]) {
					visited[nr][nc] = true;
					que.add(new Pair(nr, nc));
					continue;
				}
				if (isError) { // 색약인 경우
					if (color != 'B' && map[nr][nc] != 'B') { // 둘 다 파랑색이 아니면 추가
						visited[nr][nc] = true;
						que.add(new Pair(nr, nc));
					}
				}
			}
		}
	}
	
	static class Pair {
		int r;
		int c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
}