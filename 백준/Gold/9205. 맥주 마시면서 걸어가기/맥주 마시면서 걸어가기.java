import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {

	static Pos[] points;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		label:
		for (int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			points = new Pos[n+2];
			for (int p=0; p<n+2; p++) {
				points[p] = new Pos(br.readLine());
			}
			list = new ArrayList[n+2];
			for (int idx=0; idx<n+2; idx++) {
				list[idx] = new ArrayList<>();
			}
			for (int s=0; s<n+2; s++) {
				for (int e=s+1; e<n+2; e++) {
					if (points[s].isReachable(points[e])) {
						list[s].add(e);
						list[e].add(s);
					}
				}
			}
			Queue<Integer> que = new ArrayDeque<>();
			boolean[] visited = new boolean[n+2];
			que.add(0);
			visited[0] = true;
			while (!que.isEmpty()) {
				int curr = que.poll();
				for (int next : list[curr]) {
					if (next == n+1) {
						sb.append("happy").append("\n");
						continue label;
					}
					if (visited[next]) {
						continue;
					}
					visited[next] = true;
					que.add(next);
				}
			}
			sb.append("sad").append("\n");
		}
		System.out.println(sb);
	}
	
	static class Pos {
		int r;
		int c;
		
		public Pos(String input) {
			String[] axis = input.split(" ");
			this.r = Integer.parseInt(axis[0]);
			this.c = Integer.parseInt(axis[1]);
		}
		
		public boolean isReachable(Pos o) {
			int d1 = Math.abs(this.r-o.r)+Math.abs(this.c-o.c);
			return d1<=1000 ? true : false;
		}
	}
}