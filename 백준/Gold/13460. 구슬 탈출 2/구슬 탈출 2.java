import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n,m,moveC=11;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		Pos red=null, blue=null;
		for (int i=0; i<n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j=0; j<m; j++) {
				if (line[j] == 'R') {
					red = new Pos(i,j);
					map[i][j] = '.';
				} else if (line[j] == 'B') {
					blue = new Pos(i,j);
					map[i][j] = '.';
				} else {
					map[i][j] = line[j];
				}
			}
		}
		
		move(0,red,blue);
		System.out.println(moveC==11?-1:moveC);
	}

	private static void move(int idx, Pos red, Pos blue) {
		if (idx == 10 || idx == moveC) {
			return;
		}
		label:
		for (int i=0; i<4; i++) {
			// 먼저 움직일 공 정하기
			int tic = red.r*dr[i]+red.c*dc[i]-(blue.r*dr[i]+blue.c*dc[i]);
			int rr=0,rc=0,br=0,bc=0;
			boolean isOut = false;
			if (tic >= 0) { // 빨간 공 먼저 이동
				int r = red.r;
				int c = red.c;
				while (true) {
					r += dr[i];
					c += dc[i];
					if (map[r][c] == '#') {
						rr = r-dr[i];
						rc = c-dc[i];
						break;
					} else if (map[r][c] == 'O') {
						rr = 0;
						rc = 0;
						isOut = true;
						break;
					}
					rr = r;
					rc = c;
				}

				// 파란 공 이동
				r = blue.r;
				c = blue.c;
				while (true) {
					r += dr[i];
					c += dc[i];
					if (map[r][c] == '#') {
						br = r-dr[i];
						bc = c-dc[i];
						break;
					}
					if (map[r][c] == 'O') {
						continue label; // 파랑 공은 나가면 끝
					}
					if (r == rr && c == rc) { // 빨간 공에 막힘
						br = r-dr[i];
						bc = c-dc[i];
						break;
					}
					br = r;
					bc = c;
				}
				if (isOut) {
					moveC = idx+1;
					return;
				} else {					
					move(idx+1, new Pos(rr,rc), new Pos(br,bc));
				}
			} else { // 파란 공 먼저
				int r = blue.r;
				int c = blue.c;
				while (true) {
					r += dr[i];
					c += dc[i];
					if (map[r][c] == '#') {
						br = r-dr[i];
						bc = c-dc[i];
						break;
					} else if (map[r][c] == 'O') { // 파란 공은 나가면 안됨
						continue label;
					}
					br = r;
					bc = c;
				}
				
				// 빨간 공 이동
				r = red.r;
				c = red.c;
				while (true) {
					r += dr[i];
					c += dc[i];
					if (map[r][c] == '#') {
						rr = r-dr[i];
						rc = c-dc[i];
						break;
					}
					if (map[r][c] == 'O') {
						moveC = idx+1;
						return;
					}
					if (r == br && c == bc) { // 파란 공에 막힘
						rr = r-dr[i];
						rc = c-dc[i];
						break;
					}
					rr = r;
					rc = c;
				}
				move(idx+1, new Pos(rr,rc), new Pos(br,bc));
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
	}
}