import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] wires) {
        
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i=0; i<n+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int[] edge : wires) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		int ans = n;
		for (int[] edge : wires) {
			Queue<Integer> que = new ArrayDeque<>();
			boolean[] visited = new boolean[n+1];
			visited[edge[0]] = true;
			visited[edge[1]] = true; // 미리 못가게 막음
			int cnt = 1;
			que.add(edge[0]);
			while (!que.isEmpty()) {
				int curr = que.poll();
				for (int next : graph.get(curr)) {
					if (visited[next]) {
						continue;
					}
					visited[next] = true;
					que.add(next);
					cnt++;
				}
			}
			ans = Math.min(ans, Math.abs(2*cnt-n));
		}
        
        return ans;
    }
}