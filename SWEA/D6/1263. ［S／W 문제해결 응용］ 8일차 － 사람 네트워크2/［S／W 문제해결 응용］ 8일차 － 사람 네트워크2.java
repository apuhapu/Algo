import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int inf = 2000;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			graph = new int[n][n];
			for (int s=0; s<n; s++) {
				for (int e=0; e<n; e++) {
					int x = Integer.parseInt(st.nextToken());
					if (s==e) {
						continue;
					}
					if (x == 0) {
						graph[s][e] = inf;
					} else {
						graph[s][e] = 1;
					}
				}
			}
			for (int k=0; k<n; k++) {
				for (int s=0; s<n; s++) {
					if (k==s) {
						continue;
					}
					for (int e=s+1; e<n; e++) {
						if (k==e || s==e) {
							continue;
						}
						graph[s][e] = Math.min(graph[s][e], graph[s][k]+graph[k][e]);
						graph[e][s] = graph[s][e];
					}
				}
			}

			int min = inf;
			for (int p=0; p<n; p++) {
				int curr = 0;
				for (int o=0; o<n; o++) {
					if (p==o) {
						continue;
					}
					curr += graph[p][o];
				}
				min = Math.min(min, curr);
			}
			sb.append("#"+t+" "+min+"\n");
		}
		System.out.println(sb);
	}
}