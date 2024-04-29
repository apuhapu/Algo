import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] list = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[n][n];
		for (int len=n-2; len>=-1; len--) {
			for (int start=0; start<n; start++) {
				int end = start + len;
				if (end < 0 || end >= n) {
					continue;
				}
				if (start == 0) {
					dp[start][end] = dp[start][end+1] + 1;
					continue;
				}
				if (end == n-1) {
					dp[start][end] = dp[start-1][end] + 1;
					continue;
				}
				if (list[start-1] == list[end+1]) {
					dp[start][end] = dp[start-1][end+1];
					continue;
				}
				dp[start][end] = Math.min(dp[start-1][end]+1, dp[start][end+1]+1);
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			min = Math.min(min, dp[i][i]);
			if (i!=n-1) {
				min = Math.min(min, dp[i+1][i]);
			}
		}
		System.out.println(min);
	}
}