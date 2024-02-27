import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n+1];
		
		for (int i=1; i<=n; i++) {
			if (i+1<=n) {
				dp[i+1] = compare(dp[i]+1, dp[i+1]);
			}
			if (2*i<=n) {
				dp[2*i] = compare(dp[i]+1, dp[2*i]);
			}
			if (3*i<=n) {
				dp[3*i] = compare(dp[i]+1, dp[3*i]);
			}
		}
		System.out.println(dp[n]);
	}

	private static int compare(int i, int j) {
		if (i == 0) {
			return j;
		}
		if (j == 0) {
			return i;
		}
		return Math.min(i, j);
	}
	
}