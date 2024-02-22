import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static StringBuilder sb = new StringBuilder();
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			PriorityQueue<Edge> pq = new PriorityQueue<>((x,y) -> x.weight-y.weight);
			for (int i=0; i<e; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				pq.add(new Edge(a,b,c));
			}
			p = new int[v+1];
			Arrays.fill(p, -1);
			int cnt = 0;
			long weight = 0;
			while (cnt<v-1) {
				Edge curr = pq.poll();
				if (union(curr.n1, curr.n2)) {
					cnt++;
					weight += curr.weight;
				}
			}
			sb.append("#"+t+" "+weight+"\n");
		}
		System.out.println(sb);
	}
	
	private static int find(int a) {
		if (p[a] < 0) {
			return a;
		}
		return p[a] = find(p[a]);
	}
	
	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa == pb) {
			return false;
		}
		if (-p[pa]>=-p[pb]) {
			p[pa] += p[pb];
			p[pb] = pa;
		} else {
			p[pb] += p[pa];
			p[pa] = pb;
		}
		return true;
	}
	
	static class Edge {
		int n1;
		int n2;
		int weight;
		public Edge(int n1, int n2, int weight) {
			this.n1 = n1;
			this.n2 = n2;
			this.weight = weight;
		}
		
		
	}
}