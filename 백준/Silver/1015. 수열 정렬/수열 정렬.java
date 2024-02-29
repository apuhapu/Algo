import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] series = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		Num[] arr = new Num[n];
		for (int i=0; i<n; i++) {
			arr[i] = new Num(i, series[i]);
		}
		Arrays.sort(arr);
		for (int i=0; i<n; i++) {
			series[arr[i].idx] = i;
		}
		for (int i=0; i<n; i++) {
			sb.append(series[i]).append(" ");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}
	
	static class Num implements Comparable<Num> {
		int idx;
		int val;
		public Num(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
		
		@Override
		public int compareTo(Num o) {
			if (val==o.val) {
				return idx-o.idx;
			}
			return val-o.val;
		}
		
	}
}