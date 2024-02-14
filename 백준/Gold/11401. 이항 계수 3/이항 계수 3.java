import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, k;
	static long[] fact;
	static final long p = 1_000_000_007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		combi();
	}
	
	private static void combi() {
		fact();
		if (k==0 || k==n) {
			System.out.println(1);
			return;
		}
		// little Fermat-Thm
		long result = fact[n];
		long invRF1 = recursivePower(fact[k], p-2);
		long invRF2 = recursivePower(fact[n-k], p-2);
		result = ((result*invRF1)%p*invRF2)%p;
		System.out.println(result);
	}
	
	private static void fact() {
		fact = new long[n+1];
		fact[1] = 1;
		for (int i=2; i<n+1; i++) {
			fact[i] = (fact[i-1]*i)%p;
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