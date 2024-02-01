import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2042
 * 구간 합 구하기
 * 
 * 수의 개수 N(1 ≤ N ≤ 1,000,000)
 * 수의 변경이 일어나는 횟수 M(1 ≤ M ≤ 10,000)
 * 구간의 합을 구하는 횟수 K(1 ≤ K ≤ 10,000)
 *
 * 알고리즘 : 세그먼트 트리
 * 
 */
public class Main {
	
	private static BigInteger[] num, segTree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		num = new BigInteger[N];
		for (int i = 0; i < N; i++) {
			num[i] = new BigInteger(br.readLine());
		}
		segTree = new BigInteger[4*N];
		init(0, N-1, 1);
		int a, b;
		for (int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken())-1;
			if (a == 1) {
				BigInteger c = new BigInteger(st.nextToken());
				BigInteger diff = c.subtract(num[b]);
				num[b] = c;
				update(0,N-1,1,b,diff);
			} else if (a == 2) {
				int c = Integer.parseInt(st.nextToken())-1;
				sb.append(sum(0,N-1,1,b,c)+"\n");
			}
		}
		System.out.println(sb);
	}
	
	private static BigInteger init(int start, int end, int idx) {
		if (start==end) {
			segTree[idx] = num[start];
			return segTree[idx];
		}
		int mid = (start+end) / 2;
		segTree[idx] = init(start, mid, 2*idx).add(init(mid+1, end, 2*idx+1));
		return segTree[idx];
	}
	
	private static BigInteger sum(int start, int end, int idx, int left, int right) {
		if (left > end || right < start) {
			return BigInteger.ZERO;
		}
		if (left <= start && end <= right) {
			return segTree[idx];
		}
		int mid = (start+end) / 2;
		return sum(start, mid, 2*idx, left, right).add(sum(mid+1, end, 2*idx+1, left, right));
	}
	
	private static void update(int start, int end, int idx, int cIdx, BigInteger value) {
		if (cIdx < start || end < cIdx) {
			return;
		}
		segTree[idx] = segTree[idx].add(value);
		if (start==end) {
			return;
		}
		int mid = (start+end)/2;
		update(start, mid, 2*idx, cIdx, value);
		update(mid+1, end, 2*idx+1, cIdx, value);
	}
}