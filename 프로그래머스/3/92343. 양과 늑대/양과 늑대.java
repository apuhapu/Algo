import java.util.ArrayList;
import java.util.List;

class Solution {
    
    static ArrayList<Integer>[] graph;
	static int max = 0;
    
    public int solution(int[] info, int[][] edges) {
		int n = info.length;
		graph = new ArrayList[n];
		for (int i=0; i<n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] edge : edges) {
			graph[edge[0]].add(edge[1]);
		}
		
		int sheep = 1; // root
		int wolf = 0;
		List<Integer> ways = new ArrayList<>();
		for (int a : graph[0]) {
			ways.add(a);
		}
		move(sheep, wolf, 0, ways, info);
        return max;
    }
    
    private static void move(int sheep, int wolf, int bit, List<Integer> ways, int[] info) {
		if (sheep == wolf) {
			return;
		}
		for (int node : ways) {
			List<Integer> nWays = new ArrayList<>();
			for (int a : ways) {
				if (a == node) {
					continue;
				}
				nWays.add(a);
			}
			for (int next : graph[node]) {
				nWays.add(next);
			}
			if (info[node] == 0) { // 양
				move(sheep+1, wolf, bit|(1<<node), nWays, info);
                max = Math.max(max, sheep+1);
			} else {
				move(sheep, wolf+1, bit|(1<<node), nWays, info);
                max = Math.max(max, sheep);
			}
		}
	}
}