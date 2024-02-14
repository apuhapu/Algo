import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int max = 1_000_000;
	static int[] nums = new int[max+1];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			nums[Integer.parseInt(st.nextToken())]++;
		}
		BigInteger cnt = BigInteger.ZERO;
		int[] mu = new int[max+1];
		mu[1] = 1;
		for (int i=1; i<=max; i++) {
			int muti = nums[i];
			for (int j=2*i; j<=max; j+=i) {
				mu[j] -= mu[i];
				muti += nums[j];
			}
			
			cnt = cnt.add(
					BigInteger.valueOf(mu[i])
					.multiply(BigInteger.valueOf(muti))
					.multiply(BigInteger.valueOf(muti-1))
					.multiply(BigInteger.valueOf(muti-2))
					.divide(BigInteger.valueOf(6))
					);
		}
		
		
		System.out.println(cnt);
	}
	
	
}