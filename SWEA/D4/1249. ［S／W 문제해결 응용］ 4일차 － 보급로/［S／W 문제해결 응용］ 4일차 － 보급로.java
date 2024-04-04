import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	
	static final int[] dr = {1,-1,0,0};
	static final int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			for (int i=0; i<n; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j=0; j<n; j++) {
					map[i][j] = line[j]-'0';
				}
			}
			int[][] visited = new int[n][n];
			for (int i=0; i<n; i++) {
				Arrays.fill(visited[i], Integer.MAX_VALUE);
			}
			visited[0][0] = 0;
			PriorityQueue<Pos> pq = new PriorityQueue<>((x,y)->x.d-y.d);
			pq.add(new Pos(0,0,0));
			int minD = -1;
			label:
			while (!pq.isEmpty()) {
				Pos curr = pq.poll();
				for (int i=0; i<4; i++) {
					int r = curr.r + dr[i];
					int c = curr.c + dc[i];
					if (r<0||r>=n||c<0||c>=n) {
						continue;
					}
					if (r==n-1&&c==n-1) {
						minD = curr.d;
						break label;
					}
					int dis = curr.d + map[r][c];
					if (visited[r][c] > dis) {
						pq.add(new Pos(r,c,dis));
						visited[r][c] = dis;
					}
				}
			}
			sb.append("#"+t+" "+minD+"\n");
		}
		System.out.println(sb);
	}
	
	static class Pos {
		int r;
		int c;
		int d;
		public Pos(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
