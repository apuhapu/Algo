import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int diff = Integer.MAX_VALUE,n;
	static int[] population;
	static int[][] graph;
	static List<Integer> bits;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		population = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<n+1; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		graph = new int[n+1][];
		
		for (int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i] = new int[Integer.parseInt(st.nextToken())];
			for (int j=0; j<graph[i].length; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// check link
		boolean[] visited = new boolean[n+1];
		List<Integer> groupPopulation = new ArrayList<>();
		int numOfGroup = 0;
		for (int i=1; i<n+1; i++) {
			if (visited[i]) {
				continue;
			}
			int numOfP = population[i];
			visited[i] = true;
			numOfGroup++;
			Queue<Integer> que = new ArrayDeque<>();
			que.add(i);
			while (!que.isEmpty()) {
				int curr = que.poll();
				for (int next : graph[curr]) {
					if (visited[next]) {
						continue;
					}
					numOfP += population[next];
					visited[next] = true;
					que.add(next);
				}
			}
			groupPopulation.add(numOfP); // 그룹 인구 추가
		}
			
		if (numOfGroup>2) { // distinct group 3개 이상이면 2개의 연결된 선거구로 분리 불가능
			diff = -1;
		} else if (numOfGroup == 2) { // 그대로 사용
			diff = Math.abs(groupPopulation.get(0)-groupPopulation.get(1));
		} else { // 인위적으로 분리해야 할 경우
			bits = new ArrayList<>();
			combination(1,n,0);
			calMinDiff();
		}
		System.out.println(diff);
	}

	private static void calMinDiff() {
		label:
		for (int bit : bits) {
			List<Integer> pops = new ArrayList<>();
			boolean[] group = new boolean[n+1];
			boolean[] visited = new boolean[n+1];
			for (int i=1; i<n; i++) {
				if ((bit&(1<<i))==0) {
					continue;
				}
				group[i] = true;
			}
			// bfs
			for (int i=1; i<n+1; i++) {
				if (visited[i]) {
					continue;
				}
				if (pops.size()==2) {
					continue label; // 2개로 분리 불가
				}
				boolean color = group[i];
				visited[i] = true;
				Queue<Integer> que = new ArrayDeque<>();
				que.add(i);
				int popNum = population[i];
				while (!que.isEmpty()) {
					int curr = que.poll();
					for (int next : graph[curr]) {
						if (visited[next] || group[next] != color) { // 같은 선거구가 아닐경우
							continue;
						}
						popNum += population[next];
						visited[next] = true;
						que.add(next);
					}
				}
				pops.add(popNum);
			}
			if (pops.size()==2) {
				diff = Math.min(diff, Math.abs(pops.get(0)-pops.get(1)));
			}
		}
	}

	private static void combination(int idx, int n, int bit) {
		if (idx == n) { // n까지 안 세는 이유는 어짜피 TFF와 FTT는 중복이기 때문이다
			bits.add(bit);
			return;
		}
		combination(idx+1, n, bit);
		combination(idx+1, n, bit|(1<<idx));
	}
}