import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 구현 방법
 * 1. 날짜 변경
 * 2. 국경선 판단 & 인구 계산 (BFS) >> O(n^2)
 * 3. 1~2 반복 및 더 이상 인구 이동이 일어나지 않을 때 종료
 *
 */
public class Main {

	static int n, le, ri, start, offset;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[][] map, visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		le = Integer.parseInt(st.nextToken());
		ri = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		visited = new int[n][n];
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		
		int date = 0;
		start = 0;
		offset = 1;
		int max = 2000;
		while (max-->0) {
			date++;
			start += offset;
			offset = 0;
			if (!checkBorder()) {
				date--;
				break;
			}
		}
		System.out.println(date);
	}
	
	private static boolean checkBorder() {
		boolean ischange = false;
		for (int r=0; r<n; r++) {
			for (int c=0; c<n; c++) {
				if (visited[r][c]>=start) { // 이미 오늘 탐색한 경우
					continue;
				}
				Queue<Pos> que = new ArrayDeque<>(); // bfs용
				Queue<Pos> que2 = new ArrayDeque<>(); // 사람 분배용
				int totalPeople = 0; // 연합 내 총 인원
				boolean isopen = false; // 국경 개방 여부
				que.add(new Pos(r,c));
				visited[r][c] = start + offset++;
				while (!que.isEmpty()) {
					Pos p = que.poll();
					totalPeople += map[p.r][p.c];
					for (int i=0; i<4; i++) {
						int rr = p.r + dr[i];
						int cc = p.c + dc[i];
						if (rr==-1 || cc==-1 || rr==n || cc==n || visited[rr][cc] >= start) {
							continue;
						}
						int diff = Math.abs(map[rr][cc]-map[p.r][p.c]);
						if (diff<le || ri<diff) {
							continue;
						}
						visited[rr][cc] = visited[r][c];
						que.add(new Pos(rr,cc));
						
						if (!isopen) {
							isopen = true;
							if (!ischange) {
								ischange = true;
							}
						}
						
					}
					que2.add(p);
				}
				
				// 인구 분배
				if (isopen) {
					int numOfPeople = totalPeople / que2.size();
					while (!que2.isEmpty()) {
						Pos p = que2.poll();
						map[p.r][p.c] = numOfPeople;
					}
				}
			}
		}
		return ischange;
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