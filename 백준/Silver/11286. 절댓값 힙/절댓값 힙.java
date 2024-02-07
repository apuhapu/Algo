import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> {
			if (Math.abs(x) == Math.abs(y)) {
				return x-y;
			}
			return Math.abs(x)-Math.abs(y);
		});
		for (int i=0; i<n; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (pq.size() == 0) {
					sb.append("0");
				} else {
					sb.append(pq.poll());
				}
				sb.append("\n");
			} else {
				pq.add(x);
			}
		}
		System.out.print(sb);
	}
	
}