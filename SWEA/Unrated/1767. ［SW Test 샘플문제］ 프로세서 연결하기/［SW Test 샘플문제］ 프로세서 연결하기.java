import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	static int N, connCore, minWire;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[][] map;
	static List<Pos> processer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i=0; i<N; i++) {
				map[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			sb.append("#"+t+" ").append(minwire()).append("\n");
		}
		System.out.println(sb);
	}

	private static int minwire() {
		// 내부 프로세서 좌표 스캔
		processer = new ArrayList<>();
		for (int r=0; r<N-1; r++) {
			for (int c=0; c<N-1; c++) {
				if (map[r][c] == 1) {
					processer.add(new Pos(r,c));
				}
			}
		}
		connCore = 0;
		minWire = Integer.MAX_VALUE;
		count(0,0,0);	
		return minWire;
	}
	
	private static void count(int idx, int wire, int proc) {
		if (idx == processer.size()) {
			if (proc > connCore) {
				connCore = proc;
				minWire = wire;
				return;
			} else if (proc == connCore) {
				minWire = Math.min(minWire, wire);
				return;
			} else {
				return;
			}
		}
		Pos currP = processer.get(idx);
		for (int i=0; i<4; i++) {
			if (!checkDir(currP, i)) {
				continue;
			}
			int needWireCnt = connWire(currP, i, true);
			count(idx+1, wire+needWireCnt, proc+1);
			connWire(currP, i, false);
		}
		count(idx+1, wire, proc);
	}

	private static boolean checkDir(Pos p, int i) {
		int r = p.r;
		int c = p.c;
		while (true) {
			r += dr[i];
			c += dc[i];
			if (r<0 || r>N-1 || c<0 || c>N-1) { // conn
				break;
			}
			if (map[r][c] == 1) { // blocked
				return false;
			}
		}
		return true;
	}

	private static int connWire(Pos p, int i, boolean b) {
		int paint = 0;
		if (b) {
			paint = 1;
		}
		int r = p.r;
		int c = p.c;
		int wire = 0;
		while (true) {
			r += dr[i];
			c += dc[i];
			if (r<0 || r>N-1 || c<0 || c>N-1) {
				break;
			}
			map[r][c] = paint;
			wire++;
		}
		return wire;
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