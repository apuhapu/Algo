import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int x2 = x1;
		int y1 = Integer.parseInt(st.nextToken());
		int y2 = y1;
		for (int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			x1 = Math.min(x1, x);
			x2 = Math.max(x2, x);
			y1 = Math.min(y1, y);
			y2 = Math.max(y2, y);
		}
		System.out.println((x2-x1)*(y2-y1));
	}
}