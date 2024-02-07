import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[][] board = new boolean[100][100];
		int cnt=0;
		
		StringTokenizer st;
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			for (int r = l; r<l+10; r++) {
				for (int c = d; c<d+10; c++) {
					if (!board[r][c]) {
						cnt++;
						board[r][c] = true;
					}
				}
			}
		}
		
		System.out.println(cnt);
	}
}