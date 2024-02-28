class Solution {
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
		int[][] dp = new int[n][];
		dp[0] = new int[n];
		dp[1] = new int[n-1];
		for (int i=0; i<n-1; i++) {
			dp[1][i] = matrix_sizes[i][0]*matrix_sizes[i][1]*matrix_sizes[i+1][1];
		}
		for (int diff=2; diff<n; diff++) {
			dp[diff] = new int[n-diff];
			for (int i=0; i<n-diff; i++) {
				for (int j=0; j<diff; j++) {
					int leftMatR = matrix_sizes[i][0];
					int leftMatC = matrix_sizes[i+j][1];
					int rightMatC = matrix_sizes[i+diff][1];
					int opCnt = dp[j][i] + dp[diff-1-j][i+1+j] + leftMatR*leftMatC*rightMatC;
					if (j==0) {
						dp[diff][i] = opCnt;
					} else {
						dp[diff][i] = Math.min(dp[diff][i], opCnt);
					}
					
				}
			}
		}
        return dp[n-1][0];
    }
}