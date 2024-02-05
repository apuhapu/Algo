import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] towers = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int[] rev = new int[n];
		PriorityQueue<Tower> que = new PriorityQueue<>((a,b) -> a.h-b.h);
		for (int i=n-1; i>=0; i--) {
			while (!que.isEmpty() && que.peek().compare(towers[i])) {
				rev[que.poll().order] = i+1;
			}
			que.add(new Tower(i, towers[i]));
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < rev.length; i++) {
			sb.append(rev[i]);
			if (i<rev.length-1) {
				sb.append(" ");
			}
		}
		
		System.out.println(sb);
	}
	
	static class Tower {
		int order;
		int h;
		public Tower(int order, int h) {
			this.order = order;
			this.h = h;
		}
		public boolean compare(int h) {
			if (h>= this.h) {
				return true;
			}
			return false;
		}
	}
	
}