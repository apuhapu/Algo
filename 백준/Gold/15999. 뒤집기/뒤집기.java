import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int p = 1_000_000_007;
	static int n,m,cnt;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static char[][] table;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		table = new char[n][m];
		for (int i=0; i<n; i++) {
			table[i] = br.readLine().toCharArray();
		}
		visited = new boolean[n][m];
		cnt = 0;
		for (int r=0; r<n; r++) {
			label:
			for (int c=0; c<m; c++) {
				for (int i=0; i<4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (nr<0||nr>=n||nc<0||nc>=m) {
						continue;
					}
					if (table[r][c] != table[nr][nc]) {
						continue label;
					}
				}
				cnt++;
			}
		}
		System.out.println(pow(2,cnt));
	}

	private static long pow(int a, int r) {
		if (r == 0) {
			return 1;
		}
		long temp = pow(a, r/2)%p;
		temp = (temp*temp)%p;
		if (r%2==0) {
			return temp;
		}
		return (temp*a)%p;
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