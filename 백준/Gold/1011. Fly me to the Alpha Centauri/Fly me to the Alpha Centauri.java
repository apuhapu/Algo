import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			sb.append(calCnt(x,y)).append("\n");
		}
		System.out.println(sb);
	}

	private static int calCnt(int x, int y) {
		int cnt = 0;
		int diff = y-x;
		int n = (int) Math.sqrt(diff);
		if (n*(n+1) > diff) {
			n--;
		}
		cnt = n*2;
		diff -= n*(n+1);
		if (diff >= n+1) {
			cnt++;
			diff -= n+1;
		} 
		if (diff == 0) {
			return cnt;
		} else {
			return cnt+1;
		}
	}
}