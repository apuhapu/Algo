import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 조건
 * 1. 엘레베이터 층수는 무조건 1~N 까지 표기된다 (10^K 미만)
 * 2. K자리는 오른쪽부터 채워지며 남은 왼쪽 자리는 0으로 표기된다 (k<=6)
 * 3. 반전은 1~P개까지 가능하다 (P <= 42)
 *
 * 목표
 * - 실제로 X층일 때 변경 가능한 경우의 수
 *
 */
public class Main {
	
	static int N, K, P, X, cnt;
	static int[][] diff = {
			{0,4,3,3,4,3,2,3,1,2},
			{4,0,5,3,2,5,6,1,5,4},
			{3,5,0,2,5,4,3,4,2,3},
			{3,3,2,0,3,2,3,2,2,1},
			{4,2,5,3,0,3,4,3,3,2},
			{3,5,4,2,3,0,1,4,2,1},
			{2,6,3,3,4,1,0,5,1,2},
			{3,1,4,2,3,4,5,0,4,3},
			{1,5,2,2,3,2,1,4,0,1},
			{2,4,3,1,2,1,2,3,1,0}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		cnt = 0;
		count(P, 0, X);
		System.out.println(cnt);
	}
	
	private static void count(int leftP, int idx, int change) {
		if (idx > K) {
			return;
		}
		int currNum = (change/ (int) Math.pow(10, idx)) % 10;
		for (int i=0; i<10; i++) {
			if (leftP<diff[currNum][i]) {
				continue;
			}
			int c = change + (i-currNum)*(int)Math.pow(10, idx);
			if (i != currNum && c != 0 && c<=N) {
				cnt++;
			}
			count(leftP-diff[currNum][i], idx+1, c);
		}
	}
}