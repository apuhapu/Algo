import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] combi = new int[31][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<31; i++) {
			combi[i] = new int[i+1];
			for (int j=0; j<i+1; j++) {
				if (j==0 || j==i) {
					combi[i][j] = 1;
				} else {
					combi[i][j] = combi[i-1][j]+combi[i-1][j-1];
				}
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			sb.append(combi[n][r]).append("\n");
		}
		System.out.println(sb);		
	}

}