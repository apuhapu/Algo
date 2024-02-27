import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	static int n, t=0, size=2, stomach=0;
	static int[] dr = {-1,0,0,1}; // 물고기 탐색 우선순위
	static int[] dc = {0,-1,1,0}; // 더블 체크 필요
	static int[][] map;
	static Pos baby;
	
	public static void main(String[] args) throws IOException {
		// 입력 파트
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			for (int j=0; j<n; j++) {
				if (map[i][j] == 9) {
					map[i][j] = 0;
					baby = new Pos(i, j);
				}
			}
		}
		
		// 상어 활동
		while (true) {
			int dt = bfs();
			if (dt == 0) {
				System.out.println(t);
				break;
			} else {
				t += dt;
			}
		}
	}
	
	private static int bfs() {
		int[][] visited = new int[n][n]; // 해당 지역까지 도달하는데 필요한 시간 저장
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (i==baby.r && j==baby.c) { // 상어 위치만 0
					continue;
				}
				visited[i][j] = -1;
			}
		}
		Queue<Pos> que = new ArrayDeque<>();
		que.add(baby);
		int bestR = n;
		int bestC = n;
		int bestLen = n*n;
		
		while (!que.isEmpty()) {
			Pos curr = que.poll();
			for (int i=0; i<4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				if (nr<0||nr>=n||nc<0||nc>=n||visited[nr][nc]>=0||visited[nr][nc]>bestLen) {
					continue;
				}
				if (map[nr][nc] > size) { // 더 큰 물고기 만난 경우 >> 못지나감
					continue;
				} else if (map[nr][nc] == size || map[nr][nc] == 0) { // 같은 경우나 없는 경우는 지나갈 수만 있음
					visited[nr][nc] = visited[curr.r][curr.c] + 1;
					que.add(new Pos(nr,nc));
				} else { // 작은 물고기를 만난 경우
					visited[nr][nc] = visited[curr.r][curr.c] + 1;
					if (bestLen<visited[nr][nc]) {
						continue;
					}
					if (bestR < nr) {
						continue;
					}
					if (bestR == nr && bestC<nc) {
						continue;
					}
					bestR = nr;
					bestC = nc;
					bestLen = visited[nr][nc];
				}
			}
		}
		if (bestR<n) {
			map[bestR][bestC] = 0; // 맵에 물고기 제거
			baby.r = bestR;
			baby.c = bestC;
			if (++stomach == size) {
				size++;
				stomach = 0;
			}
			return bestLen;
		}
		// 전체를 탐색했으나 먹이를 발견 못했을 경우
		return 0;
	}

	static class Pos {
		int r;
		int c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}