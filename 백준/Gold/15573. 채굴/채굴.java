import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static int n,m;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[][] ores;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken()) + 2*n + m + 2; // 공기 층 포함
		ores = new int[n+1][m+2];
		for (int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<m+1; j++) {
				ores[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 채굴기 강도 D에 대한 이분 탐색
		int d=0;
		int start=1;
		int end=1_000_000;
		while (start<=end) {
			int mid = (start+end)/2;
			int breakCnt = mining(mid);
			if (breakCnt < k) { // 목표 채굴량 미달인 경우
				start = mid+1;
			} else {
				d = mid;
				end = mid-1;
			}
		}
		
		System.out.println(d);
	}

	private static int mining(int d) {
		boolean[][] visited = new boolean[n+1][m+2];
		int cnt = 1;
		ArrayDeque<Pos> que = new ArrayDeque<>();
		que.add(new Pos(0,0)); // 공기층
		visited[0][0] = true;
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			for (int i=0; i<4; i++) {
				int r = curr.r + dr[i];
				int c = curr.c + dc[i];
				if (r<0||r>=n+1||c<0||c>=m+2||visited[r][c]) {
					continue;
				}
				if (ores[r][c] > d) { // 현재 채굴기로 못 부시는 경우
					continue;
				}
				cnt++;
				visited[r][c] = true;
				que.add(new Pos(r,c));
			}
		}
		return cnt;
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