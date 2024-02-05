import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, w;
	static int[][] enemy,dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			enemy = new int[2][n];
			for (int i=0; i<2; i++) {
				enemy[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			// n = 1
			if (n == 1) {
				if (enemy[0][0]+enemy[1][0] <= w) {
					sb.append(1).append("\n");
				} else {
					sb.append(2).append("\n");
				}
				continue;
			}
			
			dp = new int[3][n+1];
			
			// not connected between start and end
			int cnt;
			dp[0][0] = 0; // 전 인덱스가 둘 다 채워진 경우
			dp[1][0] = 1; // 현 인덱스의 위가 채워진 경우
			dp[2][0] = 1; // 현 인덱스의 아래가 채워진 경우
			genDp(1);
			cnt = dp[0][n];
			
			// only connected at upper part
			if (enemy[0][0] + enemy[0][n-1] <= w) {
				dp[0][1] = 1;
				dp[1][1] = 2;
				if (enemy[1][0] + enemy[1][1] <= w) {
					dp[2][1] = 1;
				} else {
					dp[2][1] = 2;
				}
				genDp(2);
				cnt = Math.min(cnt, dp[2][n-1]+1);
			}
			
			// only connected at lower part
			if (enemy[1][0] + enemy[1][n-1] <= w) {
				dp[0][1] = 1;
				dp[2][1] = 2;
				if (enemy[0][0] + enemy[0][1] <= w) {
					dp[1][1] = 1;
				} else {
					dp[1][1] = 2;
				}
				genDp(2);
				cnt = Math.min(cnt, dp[1][n-1]+1);
			}
			
			// connected both side
			if (enemy[0][0] + enemy[0][n-1] <= w && enemy[1][0] + enemy[1][n-1] <= w) {
				dp[1][0] = 0;
				dp[2][0] = 0;
				dp[0][1] = 0;
				dp[1][1] = 1;
				dp[2][1] = 1;
				genDp(2);
				cnt = Math.min(cnt, dp[0][n-1]+2);
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void genDp(int start) {
		for (int i = start; i < n+1; i++) {
			dp[0][i] = Math.min(dp[1][i-1], dp[2][i-1]) + 1;
			if (enemy[0][i-1]+enemy[1][i-1] <= w) {
				dp[0][i] = Math.min(dp[0][i], dp[0][i-1]+1);
			}
			if (i>1 && enemy[0][i-1]+enemy[0][i-2] <= w && enemy[1][i-1]+enemy[1][i-2] <= w) {
				dp[0][i] = Math.min(dp[0][i], dp[0][i-2]+2);
			}
			
			if (i<n) {
				dp[1][i] = dp[0][i] + 1;
				if (enemy[0][i-1]+enemy[0][i] <= w) {
					dp[1][i] = Math.min(dp[1][i], dp[2][i-1]+1);
				}
				
				dp[2][i] = dp[0][i] + 1;
				if (enemy[1][i-1]+enemy[1][i] <= w) {
					dp[2][i] = Math.min(dp[2][i], dp[1][i-1]+1);
				}
			}
		}
	}
}