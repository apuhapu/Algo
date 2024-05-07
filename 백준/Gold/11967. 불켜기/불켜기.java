import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int n, cnt;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Pos>[][] switches = new ArrayList[n+1][n+1];
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				switches[i][j] = new ArrayList<>();
			}
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			switches[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]
					.add(new Pos(
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken())
							));
		}
		int[][] visited = new int[n+1][n+1];
		cnt = 1;
		visited[1][1] = 2;
		ArrayDeque<Pos> que = new ArrayDeque<>();
		for (Pos l : switches[1][1]) {
			que.add(l);
		}
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			if (visited[curr.r][curr.c]!=0) { // 이미 켠 불
				continue;
			}
			cnt++;
			visited[curr.r][curr.c] = 1;
			for (int i=0; i<4; i++) {
				int r = curr.r + dr[i];
				int c = curr.c + dc[i];
				if (r<1||r>n||c<1||c>n) {
					continue;
				}
				if (visited[r][c]==2) {
					visited[curr.r][curr.c] = 2;
					for (Pos next : switches[curr.r][curr.c]) {
						que.add(next);
					}
					break;
				}
			}
			if (visited[curr.r][curr.c] == 2) { // 도달 가능한 장소
				ArrayDeque<Pos> subQ = new ArrayDeque<>();
				subQ.add(curr);
				while (!subQ.isEmpty()) {
					Pos curr2 = subQ.poll();
					for (int i=0; i<4; i++) {
						int r = curr2.r + dr[i];
						int c = curr2.c + dc[i];
						if (r<1||r>n||c<1||c>n) {
							continue;
						}
						if (visited[r][c]==1) {
							visited[r][c]=2;
							for (Pos next : switches[r][c]) {
								que.add(next);
							}
							subQ.add(new Pos(r,c));
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}
	
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
}