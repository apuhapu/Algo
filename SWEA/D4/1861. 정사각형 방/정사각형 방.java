import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[] dr = {1,-1,0,0};
		int[] dc = {0,0,1,-1};
		for (int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] board = new int[N][N];
			int[][] visited = new int[N][N];
			for (int i=0; i<N; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			Location find = new Location(-1,-1);
			int moveCnt = 0;
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					if (visited[r][c] == 0) {
						visited[r][c] = 1;
						Queue<Location> que = new ArrayDeque<>();
						que.add(new Location(r,c));
						while (!que.isEmpty()) {
							Location n = que.poll();
							for (int i=0;i<4;i++) {
								int x = n.r + dr[i];
								int y = n.c + dc[i];
								if (0<=x && x<N && 0<=y && y<N && board[x][y] == board[n.r][n.c] + 1) {
									if (visited[x][y] != 0) {
										visited[r][c] += visited[x][y];
										break;
									}
									visited[x][y] = -1;
									visited[r][c]++;
									que.add(new Location(x,y));
								}
							}
						}
						if (moveCnt < visited[r][c]) {
							moveCnt = visited[r][c];
							find.r = r;
							find.c = c;
						} else if (moveCnt == visited[r][c]) {
							if (board[r][c] < board[find.r][find.c]) {
								find.r = r;
								find.c = c;
							}
						}
					}
				}
			}
			sb.append("#"+t+" "+board[find.r][find.c]+" "+moveCnt+"\n");
		}
		System.out.println(sb);
	}
	
	static class Location {
		int r;
		int c;
		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}