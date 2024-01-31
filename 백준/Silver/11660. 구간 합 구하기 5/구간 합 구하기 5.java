import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		int[][] dp = new int[N][N];
		dp[0][0] = map[0][0];
		for (int i = 1; i < N; i++) {
			dp[0][i] = dp[0][i-1] + map[0][i];
		}
		for (int r = 1; r < N; r++) {
			dp[r][0] = dp[r-1][0] + map[r][0];
			for (int c = 1; c < N; c++) {
				dp[r][c] = dp[r][c-1] + dp[r-1][c] - dp[r-1][c-1] + map[r][c];
			}
		}
		
		int x1, y1, x2, y2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken()) - 1;
			y1 = Integer.parseInt(st.nextToken()) - 1;
			x2 = Integer.parseInt(st.nextToken()) - 1;
			y2 = Integer.parseInt(st.nextToken()) - 1;
			if (x1 == 0 && y1 == 0) {
				sb.append(dp[x2][y2]);
			} else if (x1 == 0) {
				sb.append(dp[x2][y2]-dp[x2][y1-1]);
			} else if (y1 == 0) {
				sb.append(dp[x2][y2]-dp[x1-1][y2]);
			} else {
				sb.append(dp[x2][y2]-dp[x2][y1-1]-dp[x1-1][y2]+dp[x1-1][y1-1]);
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
}