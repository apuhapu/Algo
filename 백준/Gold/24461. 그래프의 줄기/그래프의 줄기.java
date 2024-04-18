import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Set<Integer>[] graph = new HashSet[n];
		int[] inorders = new int[n];
		boolean[] removed = new boolean[n];
		for (int i=0; i<n; i++) {
			graph[i] = new HashSet<>();
		}
		StringTokenizer st;
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
			inorders[a]++;
			inorders[b]++;
		}
		
		Queue<Integer> que = new ArrayDeque<>();
		for (int i=0; i<n; i++) {
			if (inorders[i] == 1) {
				que.add(i);
			}
		}
		int check = que.size();
		if (check > 2) {
			while (que.size()>2 || check != 0) {
				if (check == 0) {
					check = que.size();
				}
				int leaf = que.poll();
				check--;
				if (removed[leaf]) {
					continue;
				}
				removed[leaf] = true;
				int pare = graph[leaf].toArray(new Integer[1])[0];
				inorders[leaf] = 0;
				graph[pare].remove(leaf);
				if (--inorders[pare] == 1) {
					que.add(pare);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<n; i++) {
			if (removed[i]) {
				continue;
			}
			sb.append(i).append(" ");
		}
		
		System.out.println(sb);
	}
}