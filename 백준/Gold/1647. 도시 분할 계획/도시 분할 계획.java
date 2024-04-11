import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] head;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		head = new int[n+1];
		Arrays.fill(head, -1);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Edge(a, b, c));
		}
		
		int loadCnt = 0;
		int totalVal = 0;
		while (loadCnt < n-2) {
			Edge e = pq.poll();
			if (find(e.n1) == find(e.n2)) { // 사이클 발생
				continue;
			}
			loadCnt++;
			totalVal+=e.cost;
			union(e.n1,e.n2);
		}
		System.out.println(totalVal);
	}
	
	private static void union(int n1, int n2) {
		int h1 = find(n1);
		int h2 = find(n2);
		if (head[h1]<=head[h2]) {
			head[h1] += head[h2];
			head[h2] = h1;
		} else {
			head[h2] += head[h1];
			head[h1] = h2;
		}
	}

	private static int find(int i) {
		if (head[i]<0) {
			return i;
		}
		return head[i] = find(head[i]);
	}
	
	static class Edge implements Comparable<Edge> {
		int n1;
		int n2;
		int cost;
		public Edge(int n1, int n2, int cost) {
			this.n1 = n1;
			this.n2 = n2;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return cost-o.cost;
		}
		
	}
}