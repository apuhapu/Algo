import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int cnt = 0,r,c;
	static int[] dr = {-1,0,1};
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for (int i=0; i<r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i=0; i<r; i++) {
			dfs(new Pos(i,0));
		}
		System.out.println(cnt);
	}
	
	private static boolean dfs(Pos pos) {
		if (map[pos.r][pos.c] == 'x') { // 이미 막힌 경우
			return false;
		}
		map[pos.r][pos.c] = 'x';
		if (pos.c == c-1) { // 도착
			cnt++;
			return true;
		}
		for (int i=0; i<3; i++) {
			int nextR = pos.r + dr[i];
			int nextC = pos.c + 1;
			if (nextR < 0 || nextR == r) {
				continue;
			}
			if (dfs(new Pos(nextR, nextC))) {
				return true; // 탐색 완료
			}
		}
		return false; // 다 탐색했는데도 도달을 못할 경우
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