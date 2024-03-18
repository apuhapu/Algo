import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int start = 0;
		int end = 0;
		int currSum = arr[0];
		int minL = n+1;
		while(true) {
			if (currSum >= s) {
				if (end-start+1 < minL) {
					minL = end-start+1;
				}
				currSum -= arr[start++];
			} else {
				if (end == n-1) {
					break;
				}
				currSum += arr[++end];
			}
		}
		if (minL == n+1) {
			System.out.println(0);
		} else {
			System.out.println(minL);
		}
	}
}