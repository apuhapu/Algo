import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int maxN = 4_000_000;
	static long[] fact, invFact;
	static final long p = 1_000_000_007;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		fact();
		for (int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			combi(n, k);
		}
		System.out.println(sb);
	}
	
	private static void combi(int n, int k) {
		if (k==0 || k==n) {
			sb.append(1).append("\n");
			return;
		}
		long result = fact[n];
		long invRF1 = invFact[k];
		long invRF2 = invFact[n-k];
		result = ((result*invRF1)%p*invRF2)%p;
		sb.append(result).append("\n");
	}
	
	private static void fact() {
		fact = new long[maxN+1];
		fact[1] = 1;
		for (int i=2; i<maxN+1; i++) {
			fact[i] = (fact[i-1]*i)%p;
		}
		// little Fermat-Thm
		invFact = new long[maxN+1];
		invFact[1] = 1;
		for (int i=2; i<maxN+1; i++) {
			invFact[i] = recursivePower(fact[i], p-2);
		}
	}
	
	private static long recursivePower(long x, long r) {
		if (r==1) {
			return x;
		}
		long y = recursivePower(x, r/2);
		if (r%2==0) {
			return (y*y)%p;
		}
		return ((y*y)%p*x)%p;
	}
}