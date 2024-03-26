import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	static int n;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[][] map, visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int problemNum = 0;
		while (true) {
			problemNum++;
			n = Integer.parseInt(br.readLine());
			if (n == 0) {
				break;
			}
			map = new int[n][n];
			visited = new int[n][n];
			for (int i=0; i<n; i++) {
				map[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
				Arrays.fill(visited[i], Integer.MAX_VALUE);
			}
			searchCave();
			
			sb.append("Problem ").append(problemNum).append(": ").append(visited[n-1][n-1]).append("\n");
		}
		System.out.println(sb);
	}

	private static void searchCave() {
		Queue<Pos> que = new ArrayDeque<>();
		que.add(new Pos(0,0));
		visited[0][0] = map[0][0];	
		while (!que.isEmpty()) {
			Pos curr = que.poll();

			for (int i=0; i<4; i++) {
				int nr = curr.r+dr[i];
				int nc = curr.c+dc[i];
				if (nr<0 || nr>=n || nc<0 || nc>=n) { // OutOfBound
					continue;
				}
				int minusMoney = visited[curr.r][curr.c] + map[nr][nc];
				if (visited[nr][nc] > minusMoney) { // 더 짧은 경로 찾음
					visited[nr][nc] = minusMoney;
					que.add(new Pos(nr,nc));
				}
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