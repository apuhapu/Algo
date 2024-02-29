import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n,m;
	static int[] dr = {-1,-1,0,0,1,1};
	static int[] dc = {-1,1,-1,1,-1,1};
	static int[] info;
	static boolean[] visited;
	static char[][] map;
	static List<Integer>[] graph;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new char[n][m];
			int leftSeat = 0;
			for (int i=0; i<n; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j=0; j<m; j++) {
					if (map[i][j]=='x') {
						continue;
					}
					leftSeat++;
				}
			}
			
			graph = new ArrayList[n*m];
			for (int i=0; i<n; i++) {
				for (int j=0; j<m; j++) {
					graph[i*m+j] = new ArrayList<>();
					if (map[i][j]=='x') {
						continue;
					}
					for (int k=0; k<6; k++) {
						int r = i+dr[k];
						int c = j+dc[k];
						if (r<0||r>=n||c<0||c>=m||map[r][c]=='x') {
							continue;
						}
						graph[i*m+j].add(r*m+c);
					}
				}
			}
			visited = new boolean[n*m];
			info = new int[n*m];
			Arrays.fill(info, -1);
			int impSeat = 0;
			for (int i=0; i<n; i++) {
				for (int j = 0; j < m; j+=2) {
					Arrays.fill(visited, false);
					if (search(m*i+j)) {
						impSeat++;
					}
				}
			}
			sb.append(leftSeat-impSeat).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean search(int curr) {
		for (int next:graph[curr]) {
			if (!visited[next]) {
				visited[next] = true;
				if (info[next] == -1 ||(!visited[info[next]]&&search(info[next]))) {
					info[curr] = next;
					info[next] = curr;
					return true;
				}
			}
		}
		return false;
	}
}