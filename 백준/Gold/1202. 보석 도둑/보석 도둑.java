import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Jewel[] jewels = new Jewel[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jewels[i] = new Jewel(m, v);
		}
		int[] bags = new int[k];
		for (int i=0; i<k; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(jewels);
		Arrays.sort(bags);
		
		long maxVal = 0;
		int jewelpointer = 0;
		PriorityQueue<Integer> jewelVals = new PriorityQueue<>(Collections.reverseOrder());
		for (int bag : bags) {
			while (jewelpointer<n && jewels[jewelpointer].mass<=bag) {
				jewelVals.add(jewels[jewelpointer++].value);
			}
			if (!jewelVals.isEmpty()) {
				maxVal+=jewelVals.poll();
			}
		}
		
		System.out.println(maxVal);
	}
	
	static class Jewel implements Comparable<Jewel> {
		int mass;
		int value;
		public Jewel(int mass, int value) {
			this.mass = mass;
			this.value = value;
		}
		@Override
		public int compareTo(Jewel o) {
			return mass - o.mass;
		}
	}
}