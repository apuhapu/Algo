import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {

	static Deque<Integer> deque = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] command = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		for (int i=1; i<=n; i++) {
			if (command[n-i]==1) {
				deque.addFirst(i);
			} else if (command[n-i]==2) {
				int temp = deque.pollFirst();
				deque.addFirst(i);
				deque.addFirst(temp);
			} else {
				deque.addLast(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<n; i++) {
			sb.append(deque.pollFirst()).append(" ");
		}
		System.out.println(sb);
	}
}