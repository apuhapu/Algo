import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	static int n, m, v;
	static TreeSet<Integer>[] graph;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		graph = new TreeSet[n+1];
		for (int i=1; i<=n; i++) {
			graph[i] = new TreeSet<>();
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph[x].add(y);
			graph[y].add(x);
		}
		dfs(v);
		sb.append("\n");
		bfs(v);
		System.out.println(sb);
	}

	private static void dfs(int start) {
		boolean[] visited = new boolean[n+1];
		sb.append(start).append(" ");
		visited[start] = true;
		dfs(start, visited);
		sb.deleteCharAt(sb.length()-1);
	}
	
	private static void dfs(int curr, boolean[] visited) {
		for (int next : graph[curr]) {
			if (visited[next]) {
				continue;
			}
			sb.append(next).append(" ");
			visited[next] = true;
			dfs(next, visited);
		}
	}
	
	private static void bfs(int start) {
		boolean[] visited = new boolean[n+1];
		Queue<Integer> que = new ArrayDeque<>();
		que.add(start);
		visited[start] = true;
		sb.append(start).append(" ");
		while (!que.isEmpty()) {
			int curr = que.poll();
			for (int next : graph[curr]) {
				if (visited[next]) {
					continue;
				}
				visited[next] = true;
				sb.append(next).append(" ");
				que.add(next);
			}
		}
		sb.deleteCharAt(sb.length()-1);
	}
}
