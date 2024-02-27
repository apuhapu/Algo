import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int v,e,start;
	static ArrayList<Edge>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		graph = new ArrayList[v+1];
		for (int i=1; i<=v; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Edge(v, w));
		}
		int[] distance = new int[v+1];
		Arrays.fill(distance, -1);
		PriorityQueue<Edge> pq = new PriorityQueue<>((x,y) -> x.weight-y.weight);
		pq.add(new Edge(start, 0));
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			if (distance[curr.end]!=-1&&distance[curr.end]<=curr.weight) {
				continue;
			}
			distance[curr.end] = curr.weight;
			for (Edge next : graph[curr.end]) {
				pq.add(new Edge(next.end, next.weight+distance[curr.end]));
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=v; i++) {
			if (distance[i] == -1) {
				sb.append("INF");
			} else {
				sb.append(distance[i]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static class Edge {
		int end;
		int weight;
		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}
}