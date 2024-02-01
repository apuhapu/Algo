import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] condition;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		char[] str = br.readLine().toCharArray();
		condition = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int[] localCondition = new int[4];
		int cnt = 0;
		for (int i=0; i<P; i++) {
			localCondition[conv2Idx(str[i])]++;
		}
		if (validatePassword(localCondition)) {
			cnt++;
		}
		int start = 0;
		int end = P-1;
		while (end < S-1) {
			localCondition[conv2Idx(str[start++])]--;
			localCondition[conv2Idx(str[++end])]++;
			if (validatePassword(localCondition)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	private static int conv2Idx(char c) {
		if (c == 'A') {
			return 0;
		}
		if (c == 'C') {
			return 1;
		}
		if (c == 'G') {
			return 2;
		}
		return 3;
	}
	
	private static boolean validatePassword(int[] local) {
		for (int i=0; i<condition.length; i++) {
			if (condition[i]>local[i]) {
				return false;
			}
		}
		return true;
	}
}