import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int k,w,h;
	static int[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[] dKr = {-2,-2,-1,-1,1,1,2,2};
	static int[] dKc = {-1,1,-2,2,-2,2,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		for (int i=0; i<h; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		int minMove = move();
		System.out.println(minMove);
	}
	
	private static int move() {
		Pos start = new Pos(0,0,k);
		Queue<Pos> que = new ArrayDeque<>();
		que.add(start);
		int[][][] visited = new int[k+1][h][w];
		for (int i=0; i<k+1; i++) {
			for (int j=0; j<h; j++) {
				Arrays.fill(visited[i][j], Integer.MAX_VALUE);
			}
		}
		visited[0][0][0] = 0;
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			// move normal step
			for (int i=0; i<4; i++) {
				int r = curr.r + dr[i];
				int c = curr.c + dc[i];
				if (r<0 || r>=h || c<0 || c>=w || map[r][c] == 1) {
					continue;
				}
				if (visited[k-curr.leftK][r][c] <= visited[k-curr.leftK][curr.r][curr.c]+1) {
					continue;
				}
				visited[k-curr.leftK][r][c] = visited[k-curr.leftK][curr.r][curr.c]+1;
				que.add(new Pos(r,c,curr.leftK));
			}
			
			// move Knight step
			if (curr.leftK>0) {
				label:
				for (int i=0; i<8; i++) {
					int r = curr.r + dKr[i];
					int c = curr.c + dKc[i];
					if (r<0 || r>=h || c<0 || c>=w || map[r][c] == 1) {
						continue;
					}
					for (int j=0; j<=k-curr.leftK+1; j++) {
						if (visited[j][r][c] <= visited[k-curr.leftK][curr.r][curr.c]+1) { // 이미 더 나은 경로가 있는 경우
							continue label;
						}
					}
					visited[k-curr.leftK+1][r][c] = visited[k-curr.leftK][curr.r][curr.c]+1;
					que.add(new Pos(r,c,curr.leftK-1));
				}
			}
		}
		
		
		int minMove = Integer.MAX_VALUE;
		boolean isReach = false;
		for (int i=0; i<=k; i++) {
			if (visited[i][h-1][w-1] == Integer.MAX_VALUE) {
				continue;
			}
			isReach = true;
			minMove = Math.min(minMove, visited[i][h-1][w-1]);
		}
		if (isReach) {
			return minMove;
		} else {
			return -1;
		}
	}

	static class Pos {
		int r;
		int c;
		int leftK;
		public Pos(int r, int c, int leftK) {
			this.r = r;
			this.c = c;
			this.leftK = leftK;
		}
		
	}
}