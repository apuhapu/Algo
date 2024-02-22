import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int r,c;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		boolean[][] water = new boolean[r][c];
		boolean[][] dochiV = new boolean[r][c];
		Pos biber = null;
		Queue<Pos> flood = new ArrayDeque<>();
		Queue<Pos> dochi = new ArrayDeque<>();
		
		for (int i=0; i<r; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j=0; j<c; j++) {
				if (map[i][j] == 'D') {
					biber = new Pos(i,j);
				} else if (map[i][j] == 'S') {
					dochi.add(new Pos(i,j));
					dochiV[i][j] = true;
				} else if (map[i][j] == '*') {
					water[i][j] = true;
					flood.add(new Pos(i,j));
				}
			}
		}
		
		int time = 0;
		label:
		while (true) {
			time++;
			// 물 불어나는 파트
			int floodL = flood.size();
			for (int i=0; i<floodL; i++) {
				Pos curr = flood.poll();
				for (int k=0; k<4; k++) {
					int nr = curr.r+dr[k];
					int nc = curr.c+dc[k];
					if (nr<0||nr>=r||nc<0||nc>=c||map[nr][nc]=='X'||map[nr][nc]=='D'||water[nr][nc]) {
						continue;
					}
					water[nr][nc] = true;
					flood.add(new Pos(nr,nc));
				}
			}
			
			// 비버 굴 주변 확인 파트
			boolean isReachable = false;
			for (int k=0; k<4; k++) {
				int nr = biber.r+dr[k];
				int nc = biber.c+dc[k];
				if (nr<0||nr>=r||nc<0||nc>=c|| map[nr][nc]=='X') {
					continue;
				}
				if (water[nr][nc]) {
					if (!dochiV[nr][nc]) {
						continue;
					}
				}
				isReachable = true;
				break;
			}
			if (!isReachable) {
				time = -1;
				break label;
			}
			
			// 도치 이동 파트
			int dochiL = dochi.size();
			for (int i=0; i<dochiL; i++) {
				Pos curr = dochi.poll();
				for (int k=0; k<4; k++) {
					int nr = curr.r+dr[k];
					int nc = curr.c+dc[k];
					if (nr<0||nr>=r||nc<0||nc>=c||map[nr][nc]=='X'||water[nr][nc]||dochiV[nr][nc]) {
						continue;
					}
					if (map[nr][nc] == 'D') { // 굴 도착
						break label;
					}
					dochiV[nr][nc] = true;
					dochi.add(new Pos(nr,nc));
				}
			}
            
            if (dochi.size()==0) {
                time = -1;
                break;
            }
		}
		if (time == -1) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(time);
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