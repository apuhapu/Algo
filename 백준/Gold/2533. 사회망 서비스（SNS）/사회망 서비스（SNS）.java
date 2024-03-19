import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] graph;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n+1];
		for (int i=1; i<n+1; i++) {
			graph[i] = new ArrayList<>();
		}
		StringTokenizer st;
		int u,v;
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		dp = new int[n+1][2];
		searchTree(1,0);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	private static void searchTree(int currNode, int pareNode) {
		if (pareNode!=0&&graph[currNode].size() == 1) { // 리프 노드
			dp[currNode][0] = 0;
			dp[currNode][1] = 1;
			return;
		}
		int earlyCnt = 0;
		for (int childNode : graph[currNode]) {
			if (childNode == pareNode) {
				continue;
			}
			searchTree(childNode, currNode);
			earlyCnt += dp[childNode][1];
		}
		// 현재 노드 얼리 어답터 X
		dp[currNode][0] = earlyCnt;
		
		// 현재 노드 얼리 어답터 O
		for (int childNode : graph[currNode]) {
			if (childNode == pareNode) {
				continue;
			}
			dp[currNode][1] += Math.min(dp[childNode][0], dp[childNode][1]);
		}
		dp[currNode][1]++;
	}
}