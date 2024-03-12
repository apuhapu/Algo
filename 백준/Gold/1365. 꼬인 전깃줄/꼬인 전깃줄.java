import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int n;
	static int[] perm;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		perm = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		findMaximumIncreaingSubSeq();
	}

	private static void findMaximumIncreaingSubSeq() {
		int[] temp = new int[n];
		int end = -1;
		for (int x : perm) {
			if (end == -1) {
				temp[++end] = x;
				continue;
			}
			if (temp[end]<x) {
				temp[++end] = x;
				continue;
			} else if (temp[end] > x) {
				int upperIdx = binarySearch(temp, end, x);
				temp[upperIdx] = x;
			}	
		}
		System.out.println(n-(end+1));
	}

	private static int binarySearch(int[] temp, int len, int com) {
		int start = 0;
		int end = len;
		while (start < end) {
			int mid = (start+end)/2;
			if (temp[mid]>com) {
				end = mid;
			} else {
				start = mid+1;
			}
		}
		return end;
	}
	
}