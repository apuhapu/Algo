import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int group = 9, n, m;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[][] map;
	static HashMap<Integer,HashSet<Pos>> boundary = new HashMap<>();
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		for (int r=0; r<n; r++) {
			for (int c=0; c<m; c++) {
				if (map[r][c]!=1) {
					continue;
				}
				grouping(new Pos(r,c));
			}
		}

		graph = new int[group-9][group-9];
		for (int s=0; s<group-9; s++) {
			HashSet<Pos> bounds = boundary.get(10+s);
			for (Pos bound : bounds) {
				make(bound);
			}
		}
		
		int output = prim();
		System.out.println(output);
	}
	
	private static int prim() {
		int visitCnt = 1;
		int totalCost = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[group-9];
		visited[0] = true;
		for (int i=1; i<group-9; i++) {
			if (graph[0][i]>0) {
				pq.add(new Edge(i, graph[0][i]));
			}
		}
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			if (visited[curr.nextNode]) {
				continue;
			}
			visited[curr.nextNode] = true;
			visitCnt++;
			totalCost+=curr.cost;
			if (visitCnt == group-9) {
				return totalCost;
			}
			for (int i=1; i<group-9; i++) {
				if (visited[i]||graph[curr.nextNode][i]==0) {
					continue;
				}
				pq.add(new Edge(i, graph[curr.nextNode][i]));
			}
		}
		return -1;
	}

	private static void make(Pos bound) {
		int g1 = map[bound.r][bound.c];
		label:
		for (int i=0; i<4; i++) {
			int len = 1;
			int r = bound.r + dr[i];
			int c = bound.c + dc[i];
			if (r<0||r>=n||c<0||c>=m||map[r][c]!=0) {
				continue;
			}
			while (true) {
				len++;
				r += dr[i];
				c += dc[i];
				if (r<0||r>=n||c<0||c>=m) {
					continue label;
				}
				if (map[r][c]!=0) {
					len--;
					if (len<2) { // 다리 길이 2 미만은 버림
						continue label;
					}
					int g2 = map[r][c];
					if (graph[g1-10][g2-10] > len || graph[g1-10][g2-10] == 0) {
						graph[g1-10][g2-10] = len;
						graph[g2-10][g1-10] = len;
					}
					break;
				}
			}
		}
	}

	private static void grouping(Pos pos) {
		map[pos.r][pos.c] = ++group;
		boundary.put(group, new HashSet<>());
		Queue<Pos> que = new ArrayDeque<>();
		que.add(pos);
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			for (int i=0; i<4; i++) {
				int r = curr.r + dr[i];
				int c = curr.c + dc[i];
				if (r<0||r>=n||c<0||c>=m) {
					continue;
				}
				if (map[r][c] == 0) { // 가장자리
					boundary.get(group).add(curr);
					continue;
				}
				if (map[r][c] == 1) {
					map[r][c] = group;
					que.add(new Pos(r,c));
				}
			}
		}
	}

	static class Pos {
		int r;
		int c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int hashCode() {
			return 100*r+c;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pos other = (Pos) obj;
			return this.hashCode()==other.hashCode();
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int nextNode;
		int cost;
		@Override
		public int compareTo(Edge o) {
			return cost-o.cost;
		}
		public Edge(int nextNode, int cost) {
			this.nextNode = nextNode;
			this.cost = cost;
		}
	}
}