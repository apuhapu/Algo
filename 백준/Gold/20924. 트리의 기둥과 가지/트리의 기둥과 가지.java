import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int[] visited;
	static ArrayList<Edge>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int root = Integer.parseInt(st.nextToken());
		graph = new ArrayList[n+1];
		for (int i=1;i<n+1;i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(b,d));
			graph[b].add(new Edge(a,d));
		}
		visited = new int[n+1];
		int trunk = dfs(root, true); // 기가 노드의 인덱스
		int branch = dfs(trunk, false); // 가장 긴 가지의 인덱스
		int blen = visited[branch] - visited[trunk];
		System.out.println(visited[trunk]+" "+blen);
	}
	
	static int dfs(int start, boolean code) {
		if (code && graph[start].size()>1) { // 바로 기가 노드일 시
			return start;
		}
		Stack<Integer> stack = new Stack<>();
		stack.add(start);
		int idx = start;
		while (!stack.isEmpty()) {
			int node = stack.pop();
			if (code && graph[node].size()>2) { // 기가 노드를 찾을 시
				return node;
			}
			for (Edge e : graph[node]) {
				if (visited[e.to] == 0 && e.to != start) {
					visited[e.to] = visited[node] + e.weight;
					if (visited[idx] < visited[e.to]) {
						idx = e.to;
					}
					stack.push(e.to);
				}
			}
		}
		return idx;
	}
	
	static class Edge {
		int to;
		int weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}