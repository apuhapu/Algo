import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[][] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		for (int i=0; i<n; i++) {
			graph[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		
		int max = 0;
		int result;
		for (int r=0; r<n; r++) {
			for (int c=0; c<m; c++) {
				result = cal4By4(r,c,n,m);
				max = Math.max(result, max);
			}
		}
		System.out.println(max);
	}

	private static int cal4By4(int r, int c, int n, int m) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int temp;
		if (n-r>3) {
			temp = graph[r][c] + graph[r+1][c] + graph[r+2][c] + graph[r+3][c];
			pq.add(temp);
		}
		if (n-r>2 && m-c>1) {
			temp = graph[r][c] + graph[r+1][c] + graph[r+2][c];
			temp += Math.max(Math.max(graph[r][c+1], graph[r+1][c+1]), graph[r+2][c+1]);
			pq.add(temp);
			
			temp = graph[r][c+1] + graph[r+1][c+1] + graph[r+2][c+1];
			temp += Math.max(Math.max(graph[r][c], graph[r+1][c]), graph[r+2][c]);
			pq.add(temp);
			
            temp = graph[r][c] + graph[r+1][c] + graph[r+1][c+1] + graph[r+2][c+1];
            pq.add(temp);
            temp = graph[r][c+1] + graph[r+1][c+1] + graph[r+1][c] + graph[r+2][c];
            pq.add(temp);
		}
		if (n-r>1) {
			if (m-c>2) {
				temp = graph[r][c] + graph[r][c+1] + graph[r][c+2];
				temp += Math.max(Math.max(graph[r+1][c], graph[r+1][c+1]), graph[r+1][c+2]);
				pq.add(temp);
				
				temp = graph[r+1][c] + graph[r+1][c+1] + graph[r+1][c+2];
				temp += Math.max(Math.max(graph[r][c], graph[r][c+1]), graph[r][c+2]);
				pq.add(temp);
				
				pq.add(graph[r][c] + graph[r][c+1] + graph[r+1][c+1] + graph[r+1][c+2]);
				pq.add(graph[r+1][c] + graph[r+1][c+1] + graph[r][c+1] + graph[r][c+2]);
			}
			if (m-c>1) {
				pq.add(graph[r][c] + graph[r][c+1] + graph[r+1][c] + graph[r+1][c+1]);
			}
		}
		if (n-r>0 && m-c>3) {
			pq.add(graph[r][c] + graph[r][c+1] + graph[r][c+2] + graph[r][c+3]);
		}
		
		if (pq.isEmpty()) {
			return 0;
		}
		return pq.poll();
	}
}