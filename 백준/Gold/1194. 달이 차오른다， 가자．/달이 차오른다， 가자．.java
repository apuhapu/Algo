import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[][][] visited;
	static char[][] map;
	static Pos start = null;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		
		for (int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			if (start != null) {
				continue;
			}
			for (int j=0; j<m; j++) {
				if (map[i][j] == '0') {
					start = new Pos(i,j);
				}
			}
		}
		visited = new int[1<<6][n][m];
		int minMove = Integer.MAX_VALUE;
		Queue<Pos> que = new ArrayDeque<>();
		que.add(start);
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			for (int i=0; i<4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				if (nr<0||nr>=n||nc<0||nc>=m||map[nr][nc]=='#') { // 맵에서 나가거나 벽에 막힘
					continue;
				}
				if (map[nr][nc]=='1') { // 출구 도착
					if (minMove > curr.moveCnt()+1) {
						minMove = curr.moveCnt()+1;
					}
					continue;
				}
				if (map[nr][nc]>='A'&&map[nr][nc]<='F') { // 문에 도달 시
					int key = map[nr][nc]-'A';
					if ((curr.key&(1<<key))!=0) { // 해당 키를 가지고 있을 때
						if (visited[curr.key][nr][nc] == 0||visited[curr.key][nr][nc] > curr.moveCnt()+1) { // 해당 지역의 기록보다 현재 기록이 더 짧을 때
							visited[curr.key][nr][nc] = curr.moveCnt()+1;
							que.add(new Pos(nr,nc,curr.key));
						}
					}
					continue;
				}
				
				if (map[nr][nc]>='a'&&map[nr][nc]<='f') { // 키에 도달 시
					int key = map[nr][nc]- 'a';
					if ((curr.key&(1<<key))==0) { // 현재 갖고 있는 키가 아닌 경우
						int nKey = curr.key|(1<<key);
						if (visited[curr.key][nr][nc] == 0||visited[nKey][nr][nc] > curr.moveCnt()+1) { // 해당 지역의 기록보다 현재 기록이 더 짧을 경우
							visited[nKey][nr][nc] = curr.moveCnt()+1;
							que.add(new Pos(nr,nc,nKey));
						}
						continue;
					}
					// 갖고 있는 경우는 그냥 빈 칸처럼 생각함 >> continue 처리 안함
				}
				// 그냥 빈 칸
				if (visited[curr.key][nr][nc] == 0||visited[curr.key][nr][nc] > curr.moveCnt()+1) {
					visited[curr.key][nr][nc] = curr.moveCnt()+1;
					que.add(new Pos(nr,nc,curr.key));
				}
			}
		}
		if (minMove == Integer.MAX_VALUE) {
			minMove = -1;
		}
		System.out.println(minMove);
	}
	
	static class Pos {
		int r;
		int c;
		int key=0;
		public Pos(int r, int c, int key) {
			this.r = r;
			this.c = c;
			this.key = key;
		}
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public int moveCnt() {
			return visited[this.key][this.r][this.c];
		}
	}
}