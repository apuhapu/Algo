import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Line[] lines = new Line[n];
		StringTokenizer st;
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			lines[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(lines);
		int length = 0;
		int start = lines[0].s;
		int end = lines[0].e;
		for (int i=1; i<n; i++) {
			if (lines[i].s > end) {
				length += end - start;
				start = lines[i].s;
				end = lines[i].e;
				continue;
			}
			if (lines[i].e > end) {
				end = lines[i].e;
			}
		}
		length += end-start;
		System.out.println(length);
	}
	
	static class Line implements Comparable<Line> {
		int s, e;
		public Line(int s, int e) {
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Line o) {
			return s-o.s;
		}
	}
}