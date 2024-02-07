import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] nums = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int[] dp = new int[n];
		int len = 1;
		dp[0] = nums[0]; // min

		int[] before = new int[n];
		Arrays.fill(before, -1);
		int[] idx = new int[n];
		Arrays.fill(idx, -1);
		idx[0] = 0;
		
		for (int i=1; i<n; i++) {
			if (nums[i] > dp[len-1]) {
				before[i] = idx[len-1];
				idx[len] = i;
				dp[len++] = nums[i];
			} else if (nums[i] < dp[len-1]) {
				int start = 0;
				int end = len-1;
				while (start<end) {
					int mid = (start+end) / 2;
					if (dp[mid] >= nums[i]) {
						end = mid;
					} else if (dp[mid] < nums[i]) {
						start = mid + 1;
					}
				}
				dp[start] = nums[i];
				idx[start] = i;
				before[i] = start!=0?idx[start-1]:-1;
			}
		}

		int[] result = new int[len];
		int lastIdx = idx[len-1];
		for (int i = len-1; i>=0; i--) {
			result[i] = nums[lastIdx];
			if (i>0) {
				lastIdx = before[lastIdx];
			}
		}
		sb.append(len).append("\n");
		for (int i=0; i<len; i++) {
			sb.append(result[i]);
			if (i < len-1) {
				sb.append(" ");
			}
		}
		System.out.println(sb);
	}
}