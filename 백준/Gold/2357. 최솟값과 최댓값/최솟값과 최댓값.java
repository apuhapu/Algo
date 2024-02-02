import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 최소값과 최대값
 * https://www.acmicpc.net/problem/2357
 *
 * N(1 ≤ N ≤ 100,000)개의 정수 / 각각의 정수들은 1이상 1,000,000,000이하의 값
 * a, b의 쌍이 M(1 ≤ M ≤ 100,000)개
 *
 * 주어진 구간에서 최대, 최소값을 찾는 문제
 * 
 */
public class Main {
	private static int[] num;
	private static int[][] segTree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		num = new int[N];
		for (int i=0; i<N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		segTree = new int[4*N][2];
		init(0,N-1,1);
		int a,b;
		int[] mM;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			mM = findMinMax(0,N-1,1,a,b);
			sb.append(mM[0]+ " "+ mM[1]+"\n");
		}
		System.out.println(sb);
	}

	private static int[] init(int start, int end, int idx) {
		if (start==end) {
			segTree[idx][0] = num[start];
			segTree[idx][1] = num[start];
			return segTree[idx];
		}
		int mid = (start+end)/2;
		int[] f = init(start, mid, 2*idx);
		int[] b = init(mid+1, end, 2*idx+1);
		segTree[idx][0] = Math.min(f[0], b[0]);
		segTree[idx][1] = Math.max(f[1], b[1]);
		return segTree[idx];
	}
	
	private static int[] findMinMax(int start, int end, int idx, int left, int right) {
		if (right < start || end < left) {
			int[] temp = new int[2];
			temp[0] = Integer.MAX_VALUE;
			temp[1] = Integer.MIN_VALUE;
			return temp;
		}
		if (left <= start && end <= right) {
			return segTree[idx];
		}
		int mid = (start+end)/2;
		int[] f = findMinMax(start, mid, 2*idx, left, right);
		int[] b = findMinMax(mid+1, end, 2*idx+1, left, right);
		int[] result = new int[2];
		result[0] = Math.min(f[0], b[0]);
		result[1] = Math.max(f[1], b[1]);
		return result;
	}
}