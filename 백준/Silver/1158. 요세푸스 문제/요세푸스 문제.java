import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] segTree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer().append("<");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		segTree = new int[4*n]; // 구간 별 남은 사람 수 저장
		init(1,n,1);
		int x = k;
		for (int i=0; i<n; i++) {
			int next = edit(1,n,1,x);
			sb.append(next);
			if (i<n-1) {
				sb.append(", ");
			} else {
				sb.append(">");
			}
			if (i<n-1) {
				x = (x+k-1)%segTree[1];
				if (x == 0) {
					x = segTree[1];
				}
			}
		}
		System.out.println(sb);
	}
	
	private static int init(int start, int end, int idx) {
		if (start == end) {
			return segTree[idx] = 1;
		}
		int mid = (start+end) / 2;
		segTree[idx] = init(start, mid, 2*idx) + init(mid+1, end, 2*idx+1);
		return segTree[idx];
	}
	
	private static int edit(int start, int end, int idx, int diff) {
		segTree[idx]--;
		if (start==end) {
			return start;
		}
		int mid = (start+end) / 2;
		if (segTree[2*idx]<diff) {
			return edit(mid+1, end, 2*idx+1, diff-segTree[2*idx]);
		}
		return edit(start, mid, 2*idx, diff);
	}

}