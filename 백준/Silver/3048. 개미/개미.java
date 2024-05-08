import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int l, r, t;
	static String ll, rr;
	static StringBuilder sb = new StringBuilder();
	static boolean[] order;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		ll = br.readLine();
		rr = br.readLine();
		t = Integer.parseInt(br.readLine());
		order = new boolean[l+r];
		for (int i=l; i<l+r; i++) {
			order[i] = true;
		}
		move(0);
		print();
	}
	
	private static void move(int currT) {
		if (currT==t) {
			return;
		}
		int pointer = 0;
		while (pointer < l+r-1) {
			if (!order[pointer]&&order[pointer+1]) {
				order[pointer] = true;
				order[pointer+1] = false;
				pointer+=2;
			} else {
				pointer++;
			}
		}
		move(currT+1);
	}

	private static void print() {
		int lp = l-1;
		int rp = 0;
		for (int i=0; i<l+r; i++) {
			if (order[i]) {
				sb.append(rr.charAt(rp++));
			} else {
				sb.append(ll.charAt(lp--));
			}
		}
		System.out.println(sb);
	}

}