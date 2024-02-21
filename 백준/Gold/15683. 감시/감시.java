import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n,m,min,wall=0;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] map;
	static int[][][] dirs = {
			{},
			{
				{1,0,0,0},
				{0,1,0,0},
				{0,0,1,0},
				{0,0,0,1}
			},
			{
				{1,0,1,0},
				{0,1,0,1}
			},
			{
				{1,1,0,0},
				{0,1,1,0},
				{0,0,1,1},
				{1,0,0,1}
			},
			{
				{1,1,1,0},
				{0,1,1,1},
				{1,0,1,1},
				{1,1,0,1},
			},
			{{1,1,1,1}}
	};
	static ArrayList<Cctv> cctvs;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		cctvs = new ArrayList<>();
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			for (int j=0; j<m; j++) {
				if (map[i][j]>0 && map[i][j] < 6) {
					cctvs.add(new Cctv(i, j, map[i][j]));
				}
				if (map[i][j] == 6) {
					wall++;
				}
			}
		}
		min = Integer.MAX_VALUE;
		findMin(0);
		System.out.println(min);
	}
	
	private static void findMin(int cnt) {
		if (cnt == cctvs.size()) {
			cal();
			return;
		}
		Cctv cctv = cctvs.get(cnt);
		if (cctv.type==5) {
			findMin(cnt+1);
			return;
		}
		for (int dir=0; dir<dirs[cctv.type].length; dir++) {
			cctv.dir = dir;
			findMin(cnt+1);
		}
	}

	private static void cal() {
		int cnt = n*m - cctvs.size() - wall;
		visited = new boolean[n][m];
		for (Cctv cctv : cctvs) {
			for (int d=0; d<4; d++) {
				if (dirs[cctv.type][cctv.dir][d]==0) {
					continue;
				}
				int r = cctv.r;
				int c = cctv.c;
				while (true) {
					r += dr[d];
					c += dc[d];
					if (r<0 || r>=n || c<0 || c>=m || map[r][c] == 6) { // 맵 밖 or 벽
						break;
					}
					if (!visited[r][c] && map[r][c] == 0) { // 새로운 지역 발견
						visited[r][c] = true;
						cnt--;
					}
				}
			}
		}
		min = Math.min(min, cnt);
	}

	static class Pos {
		int r;
		int c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	static class Cctv extends Pos {
		int type;
		int dir; // 0: 상 / 1: 우 / 2: 하 / 3: 좌
		public Cctv(int r, int c, int type) {
			super(r,c);
			this.type = type;
		}
	}
}