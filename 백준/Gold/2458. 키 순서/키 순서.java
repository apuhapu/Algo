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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] hgraph = new ArrayList[n+1];
		ArrayList<Integer>[] lgraph = new ArrayList[n+1];
		for (int i=0; i<n+1; i++) {
			hgraph[i] = new ArrayList<>();
			lgraph[i] = new ArrayList<>();
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			hgraph[a].add(b);
			lgraph[b].add(a);
		}
		int cnt = 0;
		for (int s=1; s<=n; s++) {
			boolean[] visited = new boolean[n+1];
			visited[s] = true;
			int compareCnt = 1; // 자기 자신 추가
			// 높은 사람 수 탐색
			Queue<Integer> que = new ArrayDeque<>();
			que.add(s);
			while (!que.isEmpty()) {
				int curr = que.poll();
				for (int next : hgraph[curr]) {
					if (visited[next]) {
						continue;
					}
					compareCnt++;
					visited[next] = true;
					que.add(next);
				}
			}
			// 낮은 사람 수 탐색
			que.add(s);
			while (!que.isEmpty()) {
				int curr = que.poll();
				for (int next : lgraph[curr]) {
					if (visited[next]) {
						continue;
					}
					compareCnt++;
					visited[next] = true;
					que.add(next);
				}
			}
			if (compareCnt == n) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}