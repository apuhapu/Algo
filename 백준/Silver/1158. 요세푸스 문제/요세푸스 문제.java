import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer().append("<");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Queue<Integer> que = new ArrayDeque<>();
		for (int i=1; i<=n; i++) {
			que.add(i);
		}
		int cnt=0;
		while (!que.isEmpty()) {
			if (++cnt==k) {
				sb.append(que.poll());
				if (!que.isEmpty()) {
					sb.append(", ");
				} else {
					sb.append(">");
				}
				cnt = 0;
			} else {
				que.add(que.poll());
			}
		}
		System.out.println(sb);
	}
}