import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String ad = br.readLine();
		int[] table = new int[n];
		int e = 0;
		for (int s=1; s<n; s++) {
			while (e>0 && ad.charAt(e)!=ad.charAt(s)) {
				e = table[e-1];
			}
			if (ad.charAt(e) == ad.charAt(s)) {
				table[s] = ++e;
			}
		}
		System.out.println(n-table[n-1]);
	}
}