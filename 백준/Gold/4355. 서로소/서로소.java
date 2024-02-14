import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			long n = Long.parseLong(br.readLine());
			if (n == 0) {
				break;
			}
			eulerPhi(n);
		}
		System.out.println(sb);
	}
	
	private static void eulerPhi(long n) {
		if (n == 1) {
			sb.append(0).append("\n");
			return;
		}
		
		long output = 1;
		Map<Long,Integer> primes = factorizaion(n);
		for (long p : primes.keySet()) {
			output *= (p-1)*Math.pow(p, primes.get(p)-1);
		}
		sb.append(output).append("\n");
	}
	
	private static Map<Long,Integer> factorizaion(long n) {
		Map<Long,Integer> primes = new HashMap<>(); // <밑, 지수> 꼴
		long d=2;
		int cnt=0;
		long max = (long) Math.sqrt(n);
		while (d <= max) {
			if (n<d) {
				break;
			}
			if (n%d==0) {
				primes.put(d, ++cnt);
				n/=d;
			} else {
				d++;
				cnt=0;
			}
		}
		if (n!=1) {
			primes.put(n, 1);
		}
		return primes;
	}
}