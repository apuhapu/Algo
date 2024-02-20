import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n,m;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static Queue<Pos> que = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		// 외부 공기 방문 처리
		que.add(new Pos(0,0));
		bfs();
		
		// 치즈 좌표 큐로 저장
		Queue<Pos> cheese = new ArrayDeque<>();
		int left = 0;
		for (int i=0; i<n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					cheese.add(new Pos(i, j));
					left++;
				}
			}
		}
		int hour = 0;
		int beforeCnt = left;
		while (!cheese.isEmpty()) {
			for (int idx=0; idx<beforeCnt; idx++) {
				Pos curr = cheese.poll();
				boolean ismelt = false;
				for (int i=0; i<4; i++) {
					int r = curr.r + dr[i];
					int c = curr.c + dc[i];
					if (visited[r][c]) { // 주변에 외부공기가 있을 시
						que.add(curr);
						left--;
						ismelt = true;
						break;
					}
				}
				if (!ismelt) { // 안녹으면 다시 
					cheese.add(curr);
				}
			}
			
			// 모든 치즈 탐색 완료
			bfs(); // 녹은 치즈 반영
			if (left!=0) {
				beforeCnt = left;
			}
			hour++;
		}
		System.out.println(hour);
		System.out.println(beforeCnt);
	}
	
	private static void bfs() {
		for (int i=0; i<que.size(); i++) {
			Pos curr = que.poll();
			visited[curr.r][curr.c] = true;
			que.add(curr);
		}
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			for (int i=0; i<4; i++ ) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				if (nr<0 || nr>=n || nc<0 || nc>=m || visited[nr][nc] || map[nr][nc] == 1) {
					continue;
				}
				visited[nr][nc] = true;
				que.add(new Pos(nr, nc));
			}
		}
	}

	static class Pos {
		int r;
		int c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
}