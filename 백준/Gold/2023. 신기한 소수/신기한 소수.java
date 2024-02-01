import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		checkSP(0, 0);
		System.out.println(sb);
	}
	
	private static void checkSP(int len, int curr) {
		if (len==0) {
			checkSP(1,2);
			checkSP(1,3);
			checkSP(1,5);
			checkSP(1,7);
			return;
		}
		if (!isPrime(curr)) {
			return;
		}
		if (len == N) {
			sb.append(curr+"\n");
			return;
		}
		for (int i=0; i<10; i++) {
			checkSP(len+1, curr*10+i);
		}
	}

	private static boolean isPrime(int a) {
		for (int i=2; i<=(int)Math.sqrt(a); i++) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}
}