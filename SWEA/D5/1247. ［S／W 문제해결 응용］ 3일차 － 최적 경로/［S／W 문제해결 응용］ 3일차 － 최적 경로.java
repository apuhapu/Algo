import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			int n = Integer.parseInt(br.readLine());
			Pos[] location = new Pos[n+2];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<n+2; i++) {
				location[i] = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			int[][] table = new int[n+2][n+2];
			for (int i=0; i<n+1; i++) {
				for (int j=i+1; j<n+2; j++) {
					int diff = location[i].calDiff(location[j]);
					table[i][j] = diff;
					table[j][i] = diff;
				}
			}
			
			int[][] dp = new int[1<<(n+2)][n+2];
			for (int e=2; e<n+2; e++) { // 크기가 2인 부분집합 초기 값 추가
				dp[1+(1<<e)][e] = table[0][e];
			}
						
			for (int size=3; size<n+2; size++) {
				List<Integer> subset = genSubset(n+2,size);
				for (int bit : subset) {
					for (int end=2; end<n+2; end++) {
						if ((bit&(1<<end))==0) { //부분집합의 원소가 아닌 경우
							continue;
						}
						int beforeBit = bit & ~(1<<end); // end를 제외한 경우
						int minDiff = Integer.MAX_VALUE;
						for (int i=2; i<n+2; i++) {
							if ((beforeBit&(1<<i))==0) { // 경로에 없는 경우
								continue;
							}
							int a = dp[beforeBit][i];
							minDiff = Math.min(minDiff, a+table[i][end]);
						}
						dp[bit][end] = minDiff;
					}
				}
			}	
			int result = Integer.MAX_VALUE;
			for (int f=2; f<n+2; f++) {
				result = Math.min(result, dp[(1<<(n+2))-3][f]+table[f][1]);
			}
			sb.append("#"+t+" "+result+"\n");
		}
		System.out.println(sb);
	}
	
	private static List<Integer> genSubset(int len, int size) {
		ArrayList<Integer> subsets = new ArrayList<>();
		subset(subsets, 2, 1, size, 1, len);
		return subsets;
	}

	private static void subset(ArrayList<Integer> subsets, int idx, int len, int size, int bit, int bound) {
		if (size == len) {
			subsets.add(bit);
			return;
		}
		if (bound == idx) {
			return;
		}
		subset(subsets, idx+1, len+1, size, bit+(1<<idx), bound);
		subset(subsets, idx+1, len, size, bit, bound);
	}

	static class Pos {
		int r;
		int c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public int calDiff(Pos p) {
			return Math.abs(r-p.r) + Math.abs(c-p.c);
		}
	}
}
