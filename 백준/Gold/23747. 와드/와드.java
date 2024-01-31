import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	private static boolean[][] visited;
	private static StringBuffer sb = new StringBuffer();
	private static int[] dr = {1,-1,0,0};
	private static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		int[] rc = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		char[][] map = new char[rc[0]][rc[1]];
		for (int i=0; i<rc[0]; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int[] currPt = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		currPt[0]--;
		currPt[1]--;
		char[] moveCommand = br.readLine().toCharArray();
		visited = new boolean[rc[0]][rc[1]];
		
		for (char com : moveCommand) {
			if (com == 'U') {
				currPt[0]--;
			} else if (com == 'D') {
				currPt[0]++;
			} else if (com == 'L') {
				currPt[1]--;
			} else if (com == 'R') {
				currPt[1]++;
			} else {
				ward(currPt, map);
			}
		}
		visited[currPt[0]][currPt[1]] = true;
		for (int i = 0; i < 4; i++) {
			int r = currPt[0] + dr[i];
			int c = currPt[1] + dc[i];
			if (r>=0 && r< rc[0] && c>=0 && c<rc[1]) {
				visited[r][c] = true;
			}
		}
		
		outputView();
	}

	
	private static void outputView() {
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				if (visited[i][j]) {
					sb.append(".");
				} else {
					sb.append("#");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}


	private static void ward(int[] currPt, char[][] map) {
		visited[currPt[0]][currPt[1]] = true;
		char area = map[currPt[0]][currPt[1]];
		Queue<Location> que = new LinkedList<>();
		que.add(new Location(currPt[0], currPt[1]));
		while (!que.isEmpty()) {
			Location a = que.poll();
			for (int i=0; i<4; i++) {
				int r = a.r + dr[i];
				int c = a.c + dc[i];
				if (r>=0 && r< map.length && c>=0 && c<map[0].length) {
					if (map[r][c] == area) {
						if (!visited[r][c]) {
							visited[r][c] = true;
							que.add(new Location(r,c));
						}
					}
				}
			}
		}
	}
	
	static class Location {
		int r;
		int c;
		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}