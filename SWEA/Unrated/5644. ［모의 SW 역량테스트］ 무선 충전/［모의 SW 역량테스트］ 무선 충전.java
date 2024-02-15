import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {

	static int[] dy = {0,-1,0,1,0};
	static int[] dx = {0,0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 이동 시간
			int A = Integer.parseInt(st.nextToken()); // 충전기 개수
			int[][] move = new int[2][M];
			for (int i=0;i<2;i++) {
				move[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			// 충전기 정보 추가
			int[] perf = new int[A];
			Map[][] map = new Map[11][11];
			for (int i=1;i<11;i++) {
				for (int j=1;j<11;j++) {
					map[i][j] = new Map();
				}
			}
			for (int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				int range = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				perf[i] = p;
				addMap(x,y,i,map,range);
			}
			
			// 시뮬레이션
			Pos a = new Pos(1,1,0);
			Pos b = new Pos(10,10,0);
			List<Integer> l1 = map[1][1].bcs;
			List<Integer> l2 = map[10][10].bcs;
			int totalC = calP(l1,l2,perf);
			//System.out.println(totalC);
			for (int s=0; s<M; s++) {
				a.move(move[0][s]);
				b.move(move[1][s]);
				l1 = map[a.y][a.x].bcs;
				l2 = map[b.y][b.x].bcs;
				totalC += calP(l1,l2,perf);
				//System.out.println(totalC);
			}
			sb.append("#"+t+" "+totalC+"\n");
		}
		System.out.println(sb);
	}
	
	private static int calP(List<Integer> l1, List<Integer> l2, int[] perf) {
		PriorityQueue<Charger> pq1 = new PriorityQueue<>((x,y)-> {
			return y.p-x.p;
		});
		PriorityQueue<Charger> pq2 = new PriorityQueue<>((x,y)-> {
			return y.p-x.p;
		});
		for (int id : l1) {
			pq1.add(new Charger(id, perf[id]));
		}
		for (int id : l2) {
			pq2.add(new Charger(id, perf[id]));
		}
		if (l1.size() == 0) {
			if (l2.size() == 0) {
				return 0;
			}
			return pq2.poll().p;
		}
		if (l2.size() == 0) {
			return pq1.poll().p;
		}
		Charger c1 = pq1.poll();
		Charger c2 = pq2.poll();
		if (c1.id == c2.id) {
			if (pq1.size()==0) {
				if (pq2.size()==0) { // 공용으로 사용
					return c1.p;
				}
				return c1.p+pq2.peek().p;
			}
			if (pq2.size() == 0) {
				return c1.p+pq1.peek().p;
			}
			return c1.p+Math.max(pq1.peek().p, pq2.peek().p); // 그냥 둘 중에 높은거 사용하면 됨
		}
		return c1.p+c2.p;
	}
	
	private static void addMap(int x, int y, int i, Map[][] map, int range) {
		Queue<Pos> que = new ArrayDeque<>();
		que.add(new Pos(y,x,0));
		map[y][x].bcs.add(i); // 초기 기기 정보 추가
		boolean[][] visited = new boolean[11][11];
		visited[y][x] = true;
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			for (int idx=1; idx<5; idx++) {
				int nextX = curr.x + dx[idx];
				int nextY = curr.y + dy[idx];
				if (nextX<=0 || nextX>10 || nextY<=0 || nextY>10  || visited[nextY][nextX]) {
					continue;
				}
				visited[nextY][nextX] = true;
				map[nextY][nextX].bcs.add(i); // 충전기 정보 추가
				if (curr.dis+1<range) {
					que.add(new Pos(nextY,nextX,curr.dis+1)); // 범위가 최대가 아니면 다시 큐에 넣음
				}
			}
		}
	}

	static class Pos {
		int x;
		int y;
		int dis;
		public Pos(int y, int x, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		public void move(int idx) {
			y += dy[idx];
			x += dx[idx];
		}
	}
	
	static class Charger {
		int id;
		int p;
		public Charger(int id, int p) {
			this.id = id;
			this.p = p;
		}
	}
	
	static class Map {
		List<Integer> bcs;
		public Map() {
			bcs = new ArrayList<Integer>();
		}
	}
}
