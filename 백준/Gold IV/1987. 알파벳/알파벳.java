import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int max = 0, r, c;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static char[][] board;
	static boolean[] visited = new boolean[26];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new char[r][c];
		for (int i=0; i<r; i++) {
			board[i] = br.readLine().toCharArray();
		}
		visited[board[0][0]-'A'] = true;
		dfs(0,0,1);
		System.out.println(max);
	}

	private static void dfs(int cr, int cc, int cnt) {
		max = Math.max(max, cnt);
		for (int i=0; i<4; i++) {
			int nr = cr + dr[i];
			int nc = cc + dc[i];
			if (nr<0 || nr>=r || nc<0 || nc>=c || visited[board[nr][nc]-'A']) {
				continue;
			}
			visited[board[nr][nc]-'A'] = true;
			dfs(nr,nc,cnt+1);
			visited[board[nr][nc]-'A'] = false;
		}
	}
}