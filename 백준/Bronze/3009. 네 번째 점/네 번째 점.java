import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		HashSet<Integer> xset = new HashSet<>();
		HashSet<Integer> yset = new HashSet<>();
		for (int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (xset.contains(x)) {
				xset.remove(x);
			} else {
				xset.add(x);
			}
			if (yset.contains(y)) {
				yset.remove(y);
			} else {
				yset.add(y);
			}
		}
		System.out.println(xset.toArray(new Integer[1])[0]+" "+yset.toArray(new Integer[1])[0]);
	}
}