import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sum = new int[n][m];
		char[] line = br.readLine().toCharArray();
		sum[0][0] = line[0]-'0';
		for (int i=1; i<m; i++) {
			sum[0][i] = sum[0][i-1] + line[i] - '0';
		}
		for (int i=1; i<n; i++) {
			line = br.readLine().toCharArray();
			sum[i][0] = sum[i-1][0] + line[0] - '0';
			for (int j=1; j<m; j++) {
				sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + (line[j]-'0');
			}
		}
		int maxL = 0;
		for (int r=0; r<n; r++) {
			for (int c=0; c<m; c++) {
				int start = 1;
				int end = Math.min(n-r, m-c);
				int len = 0;
				while (start<=end) {
					int mid = (start+end)/2;
					if (checkSq(r,c,mid)) {
						len = mid;
						start = mid+1;
					} else {
						end = mid - 1;
					}
				}
				maxL = Math.max(maxL, len); 
			}
		}
		System.out.println(maxL*maxL);
	}
	
	private static boolean checkSq(int r, int c, int len) {
		if (r+len-1>=n||c+len-1>=m) {
			return false;
		}
		int cnt = sum[r+len-1][c+len-1];
		if (r!=0&&c!=0) {
			cnt = sum[r+len-1][c+len-1] - sum[r-1][c+len-1] - sum[r+len-1][c-1] + sum[r-1][c-1];
		} else if (r!=0&&c==0) {
			cnt -= sum[r-1][len-1];
		} else if (c!=0) {
			cnt -= sum[len-1][c-1];
		}
		return cnt==len*len?true:false;
	}
}