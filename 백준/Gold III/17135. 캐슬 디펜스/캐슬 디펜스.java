import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n,m,d;
	static int[] dr = {0,-1,0};
	static int[] dc = {-1,0,1};
	static int[][] map;
	static List<Integer> archerLocations = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		
		setArcher(0, 0, 0);
		int score = 0;
		for (int archer : archerLocations) {
			int temp = start(archer);
			score = Math.max(score, temp);
		}
		System.out.println(score);
	}

	private static void setArcher(int cnt, int idx, int bit) {
		if (cnt == 3) {
			archerLocations.add(bit);
			return;
		}
		if (idx == m) {
			return;
		}
		setArcher(cnt+1, idx+1, bit|(1<<idx)); // idx에 해당되는 col 성에 궁수 배치
		setArcher(cnt, idx+1, bit);
	}
	
	private static int start(int archer) {
		int score = 0;
		int[] archersIdx = new int[3];
		int idx = 0;
		for (int i=0; i<m; i++) {
			if ((archer & (1<<i)) != 0) {
				archersIdx[idx++] = i;
			}
		}
		int[][] field = new int[n][m];
		for (int i=0; i<n; i++) {
			field[i] = map[i].clone();
		}
		while (!isOver(field)) {
			// archer's turn
			Pos[] atteck = new Pos[3];
			for (int i=0; i<3; i++) {
				atteck[i] = findMonster(archersIdx[i], field);
			}
			for (Pos dest : atteck) {
				if (dest == null) {
					continue;
				}
				if (field[dest.r][dest.c] == 1) {
					score++;
					field[dest.r][dest.c] = 0;
				}
			}
			// monster's turn
			for (int i=n-1; i>0; i--) {
				field[i] = field[i-1];
			}
			field[0] = new int[m];
		}
		
		return score;
	}
	
	private static boolean isOver(int[][] field) {
		int monster = 0;
		for (int[] r:field) {
			for (int a:r) {
				monster += a;
			}
		}
		if (monster == 0) {
			return true;
		}
		return false;
	}
	
	private static Pos findMonster(int idx, int[][] field) {
		Pos start = new Pos(n-1, idx);
		if (field[n-1][idx] == 1) {
			return start;
		}
		if (d == 1) { // 사정거리 1이면 앞만 고려
			return null;
		}
		// bfs
		int[][] visited = new int[n][m];
		visited[n-1][idx] = 1;
		Queue<Pos> que = new ArrayDeque<>();
		que.add(start);
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			for (int i=0; i<3; i++) {
				int r = curr.r + dr[i];
				int c = curr.c + dc[i];
				if (r<0 || c<0 || c==m || visited[r][c]>0) { // outofbound or 이미 방문
					continue;
				}
				if (field[r][c] == 1) { // 가장 가까운 적 발견
					return new Pos(r,c);
				}
				visited[r][c] = visited[curr.r][curr.c] + 1;
				if (visited[r][c] == d) { // 사정거리 고려
					continue;
				}
				que.add(new Pos(r,c));
			}
		}
		// 사정거리 내 적을 못 찾았을 경우
		return null;
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