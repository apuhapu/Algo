import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		BigInteger[][] res = new BigInteger[N][2];
		StringTokenizer st;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			res[i][0] = new BigInteger(st.nextToken());
			res[i][1] = new BigInteger(st.nextToken());
		}
		String max = "1000000000"+"000000000"+"000000000"+"000000000"+"000000000"+"000000000"+"000000000"+"000000000"+"000000000"+"000000000";
		BigInteger min = new BigInteger(max);
		for (int i=0; i< (1<<N); i++) {
			BigInteger sour = BigInteger.ONE;
			BigInteger bitter = BigInteger.ZERO;
			for (int j=0; j<N; j++) {
				if ((i & (1<<j)) != 0) {
					sour = sour.multiply(res[j][0]);
					bitter = bitter.add(res[j][1]);
				}
			}
			if (!bitter.equals(BigInteger.ZERO)) {
				BigInteger diff = sour.subtract(bitter).abs();
				min = min.min(diff);
			}
		}
		System.out.println(min);
	}
}