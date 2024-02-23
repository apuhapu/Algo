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
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			String aa = st.nextToken();
			int a = aa.charAt(aa.length()-1) - '0';
			int b = Integer.parseInt(st.nextToken());
			if (a == 0) {
				sb.append(10);
			} else {
				sb.append(pow(a,b));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int pow(int a, int b) {
		if (b == 0) {
			return 1;
		}
		int x = pow(a,b/2);
		if (b%2 == 0) {
			return (x*x)%10;
		}
		return (x*x*a)%10;
	}
}