import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static StringBuffer sb = new StringBuffer();
	private static boolean[] visited;
	private static int N, M, cnt;
	private static int[] inDeg;
	private static ArrayList<ArrayList<Integer>> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
		    graph.add(new ArrayList<Integer>());
		}
		inDeg = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			inDeg[b]++;
		}
		visited = new boolean[N+1];
		cnt = 0;
		while (cnt<N) {
			int p = nextNum();
			sb.append(p);
			visited[p] = true;
			cnt++;
			for (int e:graph.get(p)) {
				inDeg[e]--;
			}
			if (cnt<N) {
				sb.append(" ");
			}
		}
		
		System.out.println(sb);
	}
	
	private static int nextNum() {
		for (int i = 1; i < N+1; i++) {
			if (inDeg[i] == 0 && !visited[i]) {
				return i;
			}
		}
		return 0;
	}
	
}