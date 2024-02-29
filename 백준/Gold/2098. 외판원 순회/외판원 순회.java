import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int n;
	static int[][] dp, table;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		table = new int[n][n];
		for (int i=0; i<n; i++) {
			table[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		dp = new int[1<<n][n]; // dp[visited][현재위치] >> visited를 돌았다는 가정하 나머지를 다 돌았을 때 남은 최소 이동거리
		for (int i=0; i<(1<<n); i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(tsp(1,0)); // 처음은 0이라고 가정
	}

	private static int tsp(int bit, int curr) {
		if (bit == (1<<n) - 1) {
			if (table[curr][0] == 0) {
				return 100_000_000;
			}
			return table[curr][0]; // 마지막 도시에서 다시 처음으로
		}
		if (dp[bit][curr]!=-1) { // 이미 계산된 경우
			return dp[bit][curr];
		}
		
		dp[bit][curr] = 100_000_000;
		for (int i=0; i<n; i++) {
			if ((bit&1<<i)!=0 || table[curr][i]==0) { // 이미 갔거나 경로가 없을 경우
				continue;
			}
			dp[bit][curr] = Math.min(dp[bit][curr], tsp(bit|1<<i,i)+table[curr][i]);
		}
		return dp[bit][curr];
	}
	
}