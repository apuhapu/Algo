import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    
    static ArrayList<Edge>[] graph;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList[n+1];
		for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
		for (int[] e : fares) {
			graph[e[0]].add(new Edge(e[1],e[2]));
			graph[e[1]].add(new Edge(e[0],e[2]));
		}
		int[] distA = dijk(n, a);
		int[] distB = dijk(n, b);
		int[] distS = dijk(n, s);
		int minCost = Integer.MAX_VALUE;
		for (int carpool = 1; carpool<=n; carpool++) { // carpool까지 같이 타고간다는 뜻
			minCost = Math.min(minCost, distS[carpool]+distA[carpool]+distB[carpool]);
		}
        return minCost;
    }
    
    private int[] dijk(int n, int start) {
		int[] dist = new int[n+1];
		for (int i=1; i<=n; i++) {
			if (i==start) {
				continue;
			}
			dist[i] = 100_000_000;
		}
		// pq는 Edge를 저장하기 보다는 출발점에서 저장된 목적지까지 가는 방법 중 하나의 cost를 가지고 있음
		PriorityQueue<Edge> pq = new PriorityQueue<>((x,y) -> x.weight-y.weight);
		pq.add(new Edge(start,0));
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (dist[e.node]<e.weight) { // pq에서 뽑은 해당 노드까지 가는 비용이 이전에 반영한 비용보다 비쌀 경우 반영할 필요 없음 (이미 반영됨)
				continue;
			}
			for (Edge conn : graph[e.node]) {
				if (dist[conn.node] > e.weight + conn.weight) { //자신을 거쳐가는 경우가 더 빠른 경우
					dist[conn.node] = e.weight + conn.weight;
					pq.add(new Edge(conn.node, dist[conn.node]));
				}
			}
		}
		return dist;
	}
    
    static class Edge {
		int node;
		int weight;
		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
}