import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int d = (int) (Math.sqrt(w*w+h*h));
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<n; i++) {
			sb.append(Integer.parseInt(br.readLine())>d?"NE":"DA").append("\n");
		}
		System.out.print(sb);
	}
}