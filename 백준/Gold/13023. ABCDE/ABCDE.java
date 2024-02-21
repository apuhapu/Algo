import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] visited;
	static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		graph = new ArrayList[n];
		for (int i=0; i<n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		boolean isFive = false;
		for (int i=0; i<n; i++) {
			visited = new int[n];
			visited[i] = 1;
			if (dfs(i)) {
				isFive = true;
				break;
			}
		}
		
		if (isFive) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	private static boolean dfs(int curr) {
		if (visited[curr]==5) {
			return true;
		}
		for (int next : graph[curr]) {
			if (visited[next]>0) {
				continue;
			}
			visited[next] = visited[curr]+1;
			if (dfs(next)) {
				return true;
			}
			visited[next] = 0;
		}
		return false;
	}

}