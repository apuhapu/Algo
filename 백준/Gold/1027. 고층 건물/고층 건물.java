import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] builds = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			builds[i] = Integer.parseInt(st.nextToken());
		}
		int[] cnts = new int[n];
		for (int from=0; from<n-1; from++) {
			cnts[from]++;
			cnts[from+1]++;
			int h = builds[from+1]-builds[from];
			int d = 1;
			for (int to=from+2; to<n; to++) {
				int h_hat = builds[to]-builds[from];
				int d_hat = to - from;
				if (1L*h_hat*d>1L*h*d_hat) {
					h = h_hat;
					d = d_hat;
					cnts[from]++;
					cnts[to]++;
				}
			}
		}
		int maxCnt = 0;
		for (int i=0; i<n; i++) {
			maxCnt = Math.max(maxCnt, cnts[i]);
		}
		System.out.println(maxCnt);
	}
}