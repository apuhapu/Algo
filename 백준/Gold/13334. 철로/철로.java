import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Location implements Comparable<Location> {
		int id;
		int point;
		public Location(int id, int point) {
			super();
			this.id = id;
			this.point = point;
		}
		@Override
		public int compareTo(Location o) {
			// TODO Auto-generated method stub
			return point-o.point;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Location> pq1 = new PriorityQueue<>();
		PriorityQueue<Location> pq2 = new PriorityQueue<>();
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int o = Integer.parseInt(st.nextToken());
			pq1.add(new Location(i, Math.min(h, o)));
			pq2.add(new Location(i, Math.max(h, o)));
		}
		int d = Integer.parseInt(br.readLine());
		
		boolean[] deleted = new boolean[n];
		int maxCnt = 0;
		HashSet<Integer> set = new HashSet<>();
		int s=0,e=0;
		while (!pq2.isEmpty()) {
			e = pq2.peek().point;
			s = e-d;
			while (!pq2.isEmpty() && pq2.peek().point == e) {
				int idx = pq2.poll().id;
				if (!deleted[idx]) {					
					set.add(idx);
				}
			}
			while (!pq1.isEmpty() && pq1.peek().point < s) {
				int idx = pq1.poll().id;
				set.remove(idx);
				deleted[idx] = true;
			}
			maxCnt = Math.max(maxCnt, set.size());
		}
		System.out.println(maxCnt);
	}
}