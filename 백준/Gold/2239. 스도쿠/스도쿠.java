import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static int[] ver = new int[9], hor = new int[9], sqr = new int[9];
	static int[][] map = new int[9][9];
	static ArrayList<Pos> blanks = new ArrayList<>();
	static boolean find = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<9; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j=0; j<9; j++) {
				map[i][j] = line[j]-'0';
				inputCheck(i,j,line[j]);
			}
		}
		sudoku(0);
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void sudoku(int i) {
		if (find) {
			return;
		}
		if (i == blanks.size()) {
			find = true;
			return;
		}
		Pos curr = blanks.get(i);
		for (int n=1; n<=9; n++) {
			int idx = 3*(curr.r/3)+curr.c/3;
			if ((hor[curr.r]&(1<<n))!=0 || (ver[curr.c]&(1<<n))!=0 || (sqr[idx]&(1<<n))!=0) {
				continue;
			}
			hor[curr.r] = hor[curr.r] | (1<<n);
			ver[curr.c] = ver[curr.c] | (1<<n);
			sqr[idx] = sqr[idx]  | (1<<n);
			map[curr.r][curr.c] = n;
			sudoku(i+1);
			if (find) {
				return;
			}
			hor[curr.r] = hor[curr.r] & ~(1<<n);
			ver[curr.c] = ver[curr.c] & ~(1<<n);
			sqr[idx] = sqr[idx] & ~(1<<n);
		}
		
	}

	private static void inputCheck(int r, int c, char val) {
		if (val == '0') {
			blanks.add(new Pos(r,c));
			return;
		}
		int x = val - '0';
		// 가로
		hor[r] = hor[r] | (1<<x);
		// 세로
		ver[c] = ver[c] | (1<<x);
		// 작은 정사각형
		int idx = 3*(r/3)+c/3;
		sqr[idx] = sqr[idx] | (1<<x);
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