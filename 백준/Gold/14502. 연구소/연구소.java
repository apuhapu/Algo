import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int maxSafeZone = 0,n,m,startCnt=0;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[][] map;
	static Queue<Pos> virus = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			for (int j=0; j<m; j++) {
				if (map[i][j] == 0) {
					startCnt++;
				} else if (map[i][j] == 2) {
					virus.add(new Pos(i,j));
				}
			}
		}
		block(3,0);
		System.out.println(maxSafeZone);
	}
	
	private static void block(int leftBlock, int idx) {
		if (leftBlock == 0) {
			maxSafeZone = Math.max(maxSafeZone, calSafeZone());
			return;
		}
		if (idx == n*m) {
			return;
		}
		int r = idx/m;
		int c = idx%m;
		if (map[r][c]==0) {
			map[r][c] = 1;
			block(leftBlock-1, idx+1);
			map[r][c] = 0;
		}
		block(leftBlock, idx+1);
		
	}

	private static int calSafeZone() {
		int safe = startCnt-3;
		Queue<Pos> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];
		for (Pos vi : virus) {
			que.add(vi);
			visited[vi.r][vi.c] = true;
		}
		
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			for (int i=0; i<4; i++) {
				int r = curr.r + dr[i];
				int c = curr.c + dc[i];
				if (r<0||r>=n||c<0||c>=m||visited[r][c]||map[r][c]!=0) {
					continue;
				}
				safe--;
				visited[r][c] = true;
				que.add(new Pos(r,c));
			}
		}
		return safe;
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