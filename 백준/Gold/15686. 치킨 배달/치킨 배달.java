import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n,m,result = Integer.MAX_VALUE,house = 0;
	static int[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static Pos[] cList;
	static ArrayList<Pos> chicken;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		chicken = new ArrayList<>();
		for (int i=0; i<n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					house++;
				} else if (map[i][j] == 2) {
					chicken.add(new Pos(i,j));
				}
			}
		}
		
		f(0,0,0);
		System.out.println(result);
		
	}
	
	private static void f(int cnt, int idx, int bit) {
		if (cnt == m) {
			result = Math.min(result, chickenD(bit,house));
			return;
		}
		if (idx == chicken.size()) {
			return;
		}
		f(cnt+1, idx+1, bit|(1<<idx));
		f(cnt, idx+1, bit);
	}
	
	private static int chickenD(int bit, int houseCnt) {
		int result = 0;
		int[][] visited = new int[n][n];
		for (int i=0; i<n; i++) {
			Arrays.fill(visited[i], -1);
		}
		Queue<Pos> que = new ArrayDeque<>();
		for (int i=0; i<chicken.size(); i++) {
			if ((bit&(1<<i))!=0) {
				Pos ch = chicken.get(i);
				visited[ch.r][ch.c] = 0; // 치킨집은 거리 0
				que.add(ch);
			}
		}
		label:
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			for (int i=0; i<4; i++) {
				int r = curr.r + dr[i];
				int c = curr.c + dc[i];
				if (r<0||r>=n||c<0||c>=n||visited[r][c]>=0) {
					continue;
				}
				visited[r][c] = visited[curr.r][curr.c] + 1;
				que.add(new Pos(r, c));
				if (map[r][c] == 1) {
					result += visited[r][c];
					if (--houseCnt == 0) {
						break label;
					}
				}
			}
		}
		
		
		return result;
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