import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		f(n);
	}

	private static void f(long b) {
		long[] cnts = new long[10];
		long temp = 0;
		for (int i=1; b!=0; i*=10) {
			int B = (int) (b%10);
			b/=10;
			cnts[0]-= i;
			for (int j=0; j<B; j++) {
				cnts[j]+= (b+1)*i;
			}
			cnts[B] += b*i+1+temp; // 앞자리 제한
			for (int j=B+1; j<10; j++) {
				cnts[j] += b*i;
			}
			temp += B*i;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<10; i++) {
			sb.append(cnts[i]);
			if (i<9) {
				sb.append(" ");
			}
		}
		System.out.println(sb);
	}
}