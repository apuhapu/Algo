import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static char[][] board;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		board = new char[n][n];
		for (int i=0; i<n; i++) {
			board[i] = br.readLine().toCharArray();
		}
		sb.append("(");
		tree(new Pos(0,0), new Pos(n-1,n-1));
		sb.append(")");
		if (sb.length()==3) {
			sb.deleteCharAt(2);
			sb.deleteCharAt(0);
		}
		System.out.println(sb);
	}
	
	private static void tree(Pos lt, Pos rb) {
		int len = rb.r - lt.r + 1;
		if (len == 1) {
			sb.append(board[lt.r][lt.c]);
		} else {
			int[] temp = new int[4];
			boolean allSame = true;
			int[] sr = {lt.r, lt.r, lt.r+len/2, lt.r+len/2};
			int[] er = {lt.r+len/2, lt.r+len/2, rb.r+1, rb.r+1};
			int[] sc = {lt.c, lt.c+len/2, lt.c, lt.c+len/2};
			int[] ec = {lt.c+len/2, rb.c+1, lt.c+len/2, rb.c+1};
			for (int i=0; i<4; i++) {
				boolean isSame = true; // 현재 파트의 통일 여부
				char check = board[sr[i]][sc[i]];
	label:		for (int r=sr[i]; r<er[i]; r++) {
					for (int c=sc[i]; c<ec[i]; c++) {
						if (board[r][c] != check) {
							isSame = false;
							allSame = false;
							break label;
						}
					}
				}
				if (isSame) {
					sb.append(check);
					temp[i] = check;
				} else {
					temp[i] = -1;
					sb.append("(");
					tree(new Pos(sr[i], sc[i]), new Pos(er[i]-1, ec[i]-1));
					sb.append(")");
				}
			}
			if (allSame) {
				int x = temp[0];
				for (int i=1; i<4; i++) {
					if (x != temp[i]) {
						allSame = false;
						break;
					}
				}
				if (allSame) {
					sb.deleteCharAt(sb.length()-1);
					sb.deleteCharAt(sb.length()-1);
					sb.deleteCharAt(sb.length()-1);
				}
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