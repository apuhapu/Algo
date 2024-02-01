import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구간 곱 구하기
 * https://www.acmicpc.net/problem/11505
 * 
 * 수의 개수 N(1 ≤ N ≤ 1,000,000)
 * 수의 변경이 일어나는 횟수 M(1 ≤ M ≤ 10,000)
 * 구간의 합을 구하는 횟수 K(1 ≤ K ≤ 10,000)
 * 
 * 사용 알고리즘 : 세그먼트 트리
 * But 덧셈과 다른 점은 배열 내 원소를 변경할 때 덧셈은 그냥 변경만큼 전체에 반영하면 되는데
 * 곱셈의 경우 나누기를 해줘야함 >> 구간의 곱을 1,000,000,007로 나눈 나머지를 출력
 * 
 * 방법 1 : 주어진 수의 역을 곱해주는 방법
 * Zp = 소수의 modular field이므로 어떤 수 a에 대한 inv는 unique하게 존재 >> x^(p-2) Fermat Little Thm
 * 분할정복 거듭제곱 사용 약 30번의 연산 필요
 * >> 문제점 : 0을 다른 수로 변경 시 0의  muti inv가 없음 >> 불가능
 * 
 * 방법 2 : 그냥 다시 처음처럼 재계산
 */
public class Main {
	private static long[] num, segTree;
	private static int P = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		num = new long[N];
		for (int i = 0; i < N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
		segTree = new long[4*N];
		init(0, N-1, 1);
		for (int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken())-1;
			if (a == 1) {
				long c = Long.parseLong(st.nextToken());
				num[b] = c;
				update(0,N-1,1,b);
			} else if (a == 2) {
				int c = Integer.parseInt(st.nextToken()) - 1;
				sb.append(multi(0,N-1,1,b,c)+"\n");
			}
		}
		System.out.println(sb);
	}
	
	private static long init(int start, int end, int idx) {
		if (start==end) {
			return segTree[idx] = num[start];
		}
		int mid = (start+end)/2;
		return segTree[idx] = (init(start, mid, 2*idx) * init(mid+1, end, 2*idx+1))%P;
	}
	
	private static long update(int start, int end, int idx, int destIdx) {
		if (destIdx<start || end<destIdx) {
			return segTree[idx];
		}
		if (start==end) {
			return segTree[idx] = num[destIdx];
		}
		int mid = (start+end)/2;
		return segTree[idx] = update(start, mid, 2*idx, destIdx)*update(mid+1, end, 2*idx+1, destIdx)%P;
	}
	
	private static long multi(int start, int end, int idx, int left, int right) {
		if (right<start || end<left) {
			return 1;
		}
		if (left <= start && end <= right) {
			return segTree[idx];
		}
		int mid = (start+end)/2;
		return (multi(start, mid, 2*idx, left, right) * multi(mid+1, end, 2*idx+1, left, right))% P;
	}
}