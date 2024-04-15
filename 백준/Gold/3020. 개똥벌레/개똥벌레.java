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
		int h = Integer.parseInt(st.nextToken());
		int[] down = new int[n/2];
		int[] up = new int[n/2];
		for (int i=0; i<n; i++) {
			int x = Integer.parseInt(br.readLine());
			if (i%2==0) {
				down[i/2] = x;
			} else {
				up[i/2] = x;
			}
		}
		Arrays.sort(down);
		Arrays.sort(up);
		int cnt = n;
		int hs = 0;
		for (int i=1; i<=h; i++) {
			int tempCnt = biSearch(i, down)+biSearch(h-i+1,up);
			if (cnt > tempCnt) {
				cnt = tempCnt;
				hs = 1;
			} else if (cnt == tempCnt) {
				hs++;
			}
		}
		System.out.println(cnt+" "+hs);
	}
	
	private static int biSearch(int h, int[] arr) {
		int s = 0;
		int e = arr.length-1;
		int out = -1;
		while (s<=e) {
			int mid = (s+e)/2;
			if (arr[mid]>=h) {
				out = mid;
				e = mid-1;
			} else {
				s = mid+1;
			}
		}
		if (out == -1) {
			return 0;
		}
		return arr.length - out;
	}
}