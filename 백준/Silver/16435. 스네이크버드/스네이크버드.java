import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		while (N-->0) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		while (!pq.isEmpty()) {
			int f = pq.poll();
			if (f <= L) {
				L++;
			} else {
				break;
			}
		}
		System.out.println(L);
	}
}