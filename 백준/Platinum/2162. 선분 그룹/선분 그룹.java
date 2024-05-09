import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[][] points;
	static int[] heads;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		points = new int[2*n][2];
		StringTokenizer st;
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			points[2*i][0] = Integer.parseInt(st.nextToken());
			points[2*i][1] = Integer.parseInt(st.nextToken());
			points[2*i+1][0] = Integer.parseInt(st.nextToken());
			points[2*i+1][1] = Integer.parseInt(st.nextToken());
		}
		
		heads = new int[n];
		Arrays.fill(heads, -1);
		
		for (int p=1; p<n; p++) {
			int a1 = 2*p;
			int a2 = 2*p+1;
			for (int q=0; q<p; q++) {
				if (find(p)==find(q)) {
					continue;
				}
				int b1 = 2*q;
				int b2 = 2*q+1;
				
				int t1 = ccw(a1,a2,b1);
				int t2 = ccw(a1,a2,b2);
				int s1 = ccw(b1,b2,a1);
				int s2 = ccw(b1,b2,a2);
				if (t1*t2>0 || s1*s2>0) { // distinct
					continue;
				}
				if (t1==0 && t2==0) { // 모두 일직선
					int isVertical = 0;
					if (points[a1][0]==points[a2][0]) {
						isVertical = 1;
					}
					int l1 = points[a1][isVertical];
					int l2 = points[a2][isVertical];
					int l3 = points[b1][isVertical];
					int l4 = points[b2][isVertical];
					int d = Math.abs(l1-l2) + Math.abs(l3-l4);
					int maxi = Math.max(l1, Math.max(l2, Math.max(l3, l4)));
					int mini = Math.min(l1, Math.min(l2, Math.min(l3, l4)));
					int l = maxi - mini;
					if (l>d) { // distinct
						continue;
					} else {
						union(p,q);
						continue;
					}
				}
				union(p,q);
			}
		}
		int numOfGroup = 0;
		int maxGroupSize = 0;
		for (int i=0; i<n; i++) {
			if (heads[i]<0) {
				numOfGroup++;
				maxGroupSize = Math.max(maxGroupSize, -heads[i]);
			}
		}
//		System.out.println(Arrays.toString(heads));
		System.out.println(numOfGroup+"\n"+maxGroupSize);
	}
	
	private static int find(int idx) {
		if (heads[idx]<0) {
			return idx;
		}
		return heads[idx] = find(heads[idx]);
	}
	
	private static void union(int p, int q) {
		int hp = find(p);
		int hq = find(q);
		if (hp == hq) {
			return;
		}
		if (heads[hp]>=heads[hq]) {
			heads[hp] += heads[hq];
			heads[hq] = hp;
		} else {
			heads[hq] += heads[hp];
			heads[hp] = hq;
		}
	}
	
	private static int ccw(int a, int b, int c) {
		long[] v1 = new long[2];
		v1[0] = points[a][0] - points[b][0];
		v1[1] = points[a][1] - points[b][1];
		long[] v2 = new long[2];
		v2[0] = points[c][0] - points[b][0];
		v2[1] = points[c][1] - points[b][1];
		long temp = v1[0]*v2[1]-v1[1]*v2[0];
		if (temp==0) {
			return 0;
		} else if (temp>0) {
			return 1;
		} else {
			return -1;
		}
	}
}