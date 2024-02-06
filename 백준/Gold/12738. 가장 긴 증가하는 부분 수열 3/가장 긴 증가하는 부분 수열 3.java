import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] nums = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int[] dp = new int[n];
		int len = 1;
		dp[0] = nums[0]; // min
		
		for (int i=1; i<n; i++) {
			if (nums[i] > dp[len-1]) {
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
				
			}
		}
		System.out.println(len);
	}
}