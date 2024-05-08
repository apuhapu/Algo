import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] points;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		points = new int[4][2];
		StringTokenizer st;
		for (int i=0; i<2; i++) {
			st = new StringTokenizer(br.readLine());
			points[2*i][0] = Integer.parseInt(st.nextToken());
			points[2*i][1] = Integer.parseInt(st.nextToken());
			points[2*i+1][0] = Integer.parseInt(st.nextToken());
			points[2*i+1][1] = Integer.parseInt(st.nextToken());
		}
		int t1 = ccw(0,1,2);
		int t2 = ccw(0,1,3);
		int s1 = ccw(2,3,0);
		int s2 = ccw(2,3,1);
		if (t1*t2>0 || s1*s2>0) {
			System.out.println(0);
			return;
		}
		if (t1==0 && t2==0) { // 모두 일직선
			int isVertical = 0;
			if (points[0][0]==points[1][0]) {
				isVertical = 1;
			}
			int l1 = points[0][isVertical];
			int l2 = points[1][isVertical];
			int l3 = points[2][isVertical];
			int l4 = points[3][isVertical];
			int d = Math.abs(l1-l2) + Math.abs(l3-l4);
			int maxi = Math.max(l1, Math.max(l2, Math.max(l3, l4)));
			int mini = Math.min(l1, Math.min(l2, Math.min(l3, l4)));
			int l = maxi - mini;
			if (l>d) {
				System.out.println(0);
			} else {
				System.out.println(1);
			}
			return;
		}
		System.out.println(1);
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