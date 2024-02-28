import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int maxCnt = 0, n, m;
	static int[] info;
	static boolean[] visited;
	static List<Integer>[] cowNeeds;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cowNeeds = new ArrayList[n+1];
		for (int i=1; i<n+1; i++) {
			cowNeeds[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			for (int j=0; j<s; j++) {
				cowNeeds[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		spreadCow();
		System.out.println(maxCnt);
	}

	private static void spreadCow() {
		info = new int[m+1];
		visited = new boolean[m+1];
		for (int i=1; i<n+1; i++) {
			Arrays.fill(visited, false);
			if (check(i)) {
				maxCnt++;
			}
		}
	}

	private static boolean check(int i) {
		for (int home : cowNeeds[i]) {
			if (!visited[home]) {
				visited[home] = true;
				if (info[home]==0||check(info[home])) { // 해당 축사에 없는 경우 or 해당 축사의 있는 소가 다른 공간에 갈 수 있는 경우
					info[home] = i;
					return true;
				}
			}
		}
		return false;
	}
}