import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int b = n/5;
		while (b>=0) {
			if ((n-5*b)%3 == 0) {
				b = (n-5*b)/3+b;
				break;
			}
			b--;
		}
		System.out.println(b);
	}
}