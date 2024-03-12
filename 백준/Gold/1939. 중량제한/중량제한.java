import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, f1, f2;
	static HashMap<Integer, Integer>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new HashMap[n+1];
		for (int i=1; i<n+1; i++) {
			graph[i] = new HashMap<>();
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (graph[a].containsKey(b)) { // 해당 경로가 이미 있었을 경우
				int maxV = Math.max(c, graph[a].get(b));
				graph[a].put(b, maxV);
			} else { // 첫 경로일 경우
				graph[a].put(b, c);
			}
			if (graph[b].containsKey(a)) { // 해당 경로가 이미 있었을 경우
				int maxV = Math.max(c, graph[b].get(a));
				graph[b].put(a, maxV);
			} else { // 첫 경로일 경우
				graph[b].put(a, c);
			}
		}
		st = new StringTokenizer(br.readLine());
		f1 = Integer.parseInt(st.nextToken());
		f2 = Integer.parseInt(st.nextToken());
		findMaxWeightBtwF();
	}
	
	private static void findMaxWeightBtwF() {
		int start = 1;
		int end = 1_000_000_000;
		int result=0;
		while (start<=end) {
			int mid = (start+end)/2;
			if (check(mid)) {
				result = mid;
				start = mid+1;
				continue;
			} else {
				end = mid-1;
			}
		}
		System.out.println(result);
	}

	private static boolean check(int mid) {
		boolean[] visited = new boolean[n+1];
		visited[f1] = true;
		Queue<Integer> que = new ArrayDeque<>();
		que.add(f1);
		while (!que.isEmpty()) {
			int curr = que.poll();
			for (int next : graph[curr].keySet()) {
				if (visited[next]) {
					continue;
				}
				if (graph[curr].get(next)<mid) { // 허용 제한보다 낮을 경우
					continue;
				}
				if (next == f2) {
					return true;
				}
				visited[next] = true;
				que.add(next);
			}
		}
		return false;
	}

}