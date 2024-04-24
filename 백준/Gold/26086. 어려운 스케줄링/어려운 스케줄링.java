import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int comm, p;
		int[][] query = new int[q][2];
		int lastSort = -1;
		for (int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			comm = Integer.parseInt(st.nextToken());
			query[i][0] = comm;
			if (comm == 1) { // 정렬
				lastSort = i;
			} else if (comm == 0) {
				p = Integer.parseInt(st.nextToken());
				query[i][1] = p;
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0; i<lastSort; i++) {
			if (query[i][0] == 0) {
				list.add(query[i][1]);
			}
		}
		Collections.sort(list);
		ArrayDeque<Integer> deque = new ArrayDeque<>(list);
		
		boolean reverse = false;
		for (int i=lastSort+1; i<q; i++) {
			if (query[i][0]==0) {
				if (!reverse) {
					deque.addFirst(query[i][1]);
				} else {
					deque.addLast(query[i][1]);
				}
			} else {
				reverse = !reverse;
			}
		}
		
		if (reverse) {
			for (int i=0; i<k-1; i++) {
				deque.pollLast();
			}
			System.out.println(deque.pollLast());
		} else {
			for (int i=0; i<k-1; i++) {
				deque.pollFirst();
			}
			System.out.println(deque.pollFirst());
		}
	}
}