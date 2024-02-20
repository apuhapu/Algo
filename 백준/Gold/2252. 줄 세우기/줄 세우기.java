import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] graph = new ArrayList[n+1];
		for (int i=1; i<n+1; i++) {
			graph[i] = new ArrayList<>();
		}
		int[] deg = new int[n+1];
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			deg[b]++;
		}
		
		// 진입차수 0인 정점 큐에 추가
		Queue<Integer> que = new ArrayDeque<>();
		for (int idx=1; idx<n+1; idx++) {
			if (deg[idx]==0) {
				que.add(idx);
			}
		}
		
		while (!que.isEmpty()) {
			int curr = que.poll();
			// curr와 연결된 정점들 모두 진입차수 -1
			for (int next : graph[curr]) {
				deg[next]--;
				if (deg[next] == 0) {
					que.add(next);
				}
			}
			sb.append(curr).append(" ");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}
}