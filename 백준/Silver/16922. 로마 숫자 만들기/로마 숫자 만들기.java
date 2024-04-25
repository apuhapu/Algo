import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

	static int n;
	static HashSet<Integer> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		set = new HashSet<>();
		gen(0,n,0);
		System.out.println(set.size());
	}

	private static void gen(int idx, int left, int bit) {
		if (left == 0) {
			int sum = 0;
			sum += 50*(bit/1000000);
			bit %= 1000000;
			sum += 10*(bit/10000);
			bit %= 10000;
			sum += 5*(bit/100);
			bit %= 100;
			sum += bit;
			set.add(sum);
			return;
		}
		if (idx==4) {
			return;
		}
		for (int i=0; i<=left; i++) {
			gen(idx+1,left-i,bit+i*(int)Math.pow(10, 2*idx));
		}
	}
}