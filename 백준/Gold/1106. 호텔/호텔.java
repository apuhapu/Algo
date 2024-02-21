import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] dp = new int[n+1][c+1]; // 도시 idx x 고객 수
		for (int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 비용
			int y = Integer.parseInt(st.nextToken()); // 고객 수
			for (int p=1; p<c+1; p++) {
				if (p<y) { // 추가되는 도시의 고객수 단위가 현재 p 보다 클 경우
					if (dp[i-1][p] == 0) {
						dp[i][p] = x;
					} else {
						dp[i][p] = Math.min(dp[i-1][p], x);
					}
				} else {
					if (dp[i-1][p] == 0) {
						dp[i][p] = dp[i][p-y]+x;
					} else {
						dp[i][p] = Math.min(dp[i-1][p], dp[i][p-y]+x);
					}
				}
			}
		}	
		System.out.println(dp[n][c]);
		
	}
}