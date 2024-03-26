import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] graph;
	static int[] head;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		graph = new ArrayList[n+1];
		head = new int[n+1];
		Arrays.fill(head, -1);
		head[0] = 0;
		for (int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			String code = st.nextToken();
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			if (code.equals("F")) {
				union(p,q);
			} else {
				graph[p].add(q);
				graph[q].add(p);
			}
		}
		for (int start=1; start<=n; start++) {
			for (int e : graph[start]) {
				// 적의 적
				for (int f : graph[e]) {
					union(start,f);
				}
			}
		}
		int teamCnt = 0;
		for (int h : head) {
			if (h<0) {
				teamCnt++;
			}
		}
		System.out.println(teamCnt);
	}

	private static void union(int p, int q) {
		int hp = find(p);
		int hq = find(q);
		if (hp == hq) {
			return;
		}
		if (-head[hp]>=-head[hq]) {
			head[hp] += head[hq];
			head[hq] = hp;
		} else {
			head[hq] += head[hp];
			head[hp] = hq;
		}
	}

	private static int find(int p) {
		if (head[p] < 0) {
			return p;
		}
		return head[p] = find(head[p]);
	}
}