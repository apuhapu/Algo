import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int n;
	static int[] list, match;
	static boolean[] isPrime = new boolean[2000], visited;
	static ArrayList<Integer> result = new ArrayList<>(), sub;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		list = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		
		
		prime();
		for (int idx=1; idx<n; idx++) {
			int pair = list[0] + list[idx];
			if (!isPrime[pair]) { // 첫 번째 원소와 소수쌍이 되지 않음
				continue;
			}
			// 나머지 원소에 대한 소수쌍 판별을 위한 이분매칭
			if (!bip(idx)) {
				continue;
			}
			result.add(list[idx]);
		}
		
		// 결과 출력
		if (result.size() == 0) {
			sb.append(-1);
		} else {
			result.sort((x,y)->x-y);
			for (int x : result) {
				sb.append(x+" ");
			}			
		}
		System.out.println(sb);
	}

	private static boolean bip(int idx) {
		sub = new ArrayList<>(n-2);
		for (int i=1; i<n ;i++) {
			if (i == idx) {
				continue;
			}
			sub.add(list[i]);
		}
		match = new int[n-2];
		visited = new boolean[n-2];
		Arrays.fill(match, -1);
		
		int mat = 0;
		
		for (int i=0; i<n-2; i++) {
			if (match[i]!=-1) {
				continue;
			}
			Arrays.fill(visited, false);
			mat += dfs(i)?1:0;
		}
		if (mat == (n-2)/2) {
			return true;
		}
		return false;
	}

	private static boolean dfs(int a) {
		if (visited[a]) { // 이번 탐색에 이미 간 경우
			return false;
		}
		visited[a] = true;
		for (int b=0; b<n-2; b++) {
			if (b == a) {
				continue;
			}
			if (!isPrime[sub.get(a)+sub.get(b)]) { // a+b가 소수가 아닌경우
				continue;
			}
			if (match[b]==-1||dfs(match[b])) {
				match[a] = b;
				match[b] = a;
				return true;
			}
		}
		
		return false;
	}

	private static void prime() {
		Arrays.fill(isPrime, true);
		for (int i=2; i<(int)Math.sqrt(2000); i++) {
			if (isPrime[i]) {
				for (int j=2*i; j<2000; j+=i) {
					isPrime[j] = false;
				}
			}
		}
	}
}