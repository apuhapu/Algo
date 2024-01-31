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
		int[] nums = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int[] dp = new int[N];
		dp[0] = nums[0];
		for (int i = 1; i < N; i++) {
			dp[i] = dp[i-1] + nums[i];
		}
		
		int s, e;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			if (s == 1) {
				sb.append(dp[e-1]);
			} else {
				sb.append(dp[e-1]-dp[s-2]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}