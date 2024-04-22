import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static HashSet<Integer> broken = new HashSet<>();
	static int d,n,u,t,twinT,ponyT;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		d = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			broken.add(Integer.parseInt(st.nextToken()));
		}
		twinT = 0;
		twinSearch(1, 0, u);
		ponyT = 0;
		ponySearch(1, false);
		if (twinT < ponyT) {
			System.out.println(":blob_twintail_aww:");
		} else if (twinT > ponyT) {
			System.out.println(":blob_twintail_sad:");
		} else {
			System.out.println(":blob_twintail_thinking:");
		}
	}

	private static void ponySearch(int idx, boolean back) {
		if (idx >= Math.pow(2, d-1)) { // leaf
			if (back) {
				ponyT += u;
			}
			return;
		}
		boolean left = !broken.contains(idx*2);
		boolean right = !broken.contains(idx*2+1);
		if (left) {
			ponyT += u;
			ponySearch(2*idx, right||back);
		}
		if (right) {
			ponyT += u;
			ponySearch(2*idx+1, back);
		}
		if (back) {
			ponyT += u;
		}
	}

	private static void twinSearch(int idx, int currT, int dt) {
		if (idx >= Math.pow(2, d-1)) { // leaf
			twinT = Math.max(twinT, currT);
			return;
		}
		boolean left = !broken.contains(idx*2);
		boolean right = !broken.contains(idx*2+1);
		if (!left&&!right) { // leaf
			twinT = Math.max(twinT, currT);
			return;
		}
		
		if (left&&right) {
			twinSearch(2*idx, currT+dt+t, dt+t);
			twinSearch(2*idx+1, currT+dt+t, dt+t);
			return;
		}
		if (left) {
			twinSearch(2*idx, currT+dt, dt);
		} else {
			twinSearch(2*idx+1, currT+dt, dt);
		}
	}
}