import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, c;
	static int[] house;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		house = new int[n];
		for (int i=0; i<n; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(house);
		int start = 1;
		int end = (house[n-1]-house[0])/(c-1);
		int maxDiff = 1;
		while (start<=end) {
			int diff = (start+end)/2;
			if (check(diff)) {
				maxDiff = diff;
				start = diff+1;
			} else {
				end = diff - 1;
			}
		}
		System.out.println(maxDiff);
	}

	private static boolean check(int diff) {
		int curr = house[0]+diff;
		int cnt = 1;
		for (int i=1; i<n; i++) {
			if (curr<=house[i]) {
				cnt++;
				curr = house[i]+diff;
			}
			if (cnt >= c) {
				return true; 
			}
		}
		return false;
	}
}