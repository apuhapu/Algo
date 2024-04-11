import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] a = br.readLine().toCharArray();
		char[] b = br.readLine().toCharArray();
		int[][] dp = new int[a.length+1][b.length+1];
		for (int i=1; i<=a.length; i++) {
			for (int j=1; j<=b.length; j++) {
				if (a[i-1]==b[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		sb.append(dp[a.length][b.length]).append("\n");
		int p=a.length, q=b.length;
		Stack<Character> stack = new Stack<>();
		while (true) {
			if (dp[p][q]==0) {
				break;
			}
			if (dp[p][q] == dp[p-1][q]) {
				p--;
			} else if (dp[p][q] == dp[p][q-1]) {
				q--;
			} else {
				stack.push(a[p-1]);
				p--;
				q--;
			}
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}
}