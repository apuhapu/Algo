import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[n+1][3];
		for (int i=1; i<n+1; i++) {
			cost[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		int[][] dp = new int[n+1][3];
		dp[1] = cost[1];
		for (int i=2; i<n+1; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
		}
		
		System.out.println(Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]));
	}
}