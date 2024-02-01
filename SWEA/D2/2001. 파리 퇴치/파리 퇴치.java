import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int N,M,cnt;
		for (int t=1; t<T+1;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cnt = 0;
			int[][] board = new int[N][N];
			int[][] dp = new int[N-M+1][N-M+1];
			for (int i=0; i<N; i++) {
				board[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			for (int r=0; r<N-M+1; r++) {
				if (r==0) {
					for (int i=0; i<M; i++) {
						for (int j=0; j<M; j++) {
							dp[r][0] += board[i][j];
							
						}
						cnt = dp[r][0];
					}
				} else {
					dp[r][0] = dp[r-1][0];
					for (int i=0; i<M; i++) {
						dp[r][0] += board[r+M-1][i] - board[r-1][i];
					}
					if (cnt < dp[r][0]) {
						cnt = dp[r][0];
					}
				}
				for (int c=1; c<N-M+1; c++) {
					dp[r][c] = dp[r][c-1];
					for (int i=0; i<M; i++) {
						dp[r][c] += board[r+i][c+M-1] - board[r+i][c-1];
					}
					if (cnt < dp[r][c]) {
						cnt = dp[r][c];
					}
				}
			}
			sb.append("#"+t+" "+cnt+"\n");
		}
		System.out.println(sb);
	}
}