import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[3][m];
        int temp = 0;
        for (int i = 0; i < m; i++) {
            temp += arr[0][i];
            dp[0][i] = temp;
        }

        for (int r=1; r<n; r++) {
            // left to right
            dp[1][0] = dp[0][0] + arr[r][0];
            for (int j=1; j<m; j++) {
                dp[1][j] = Math.max(dp[1][j-1], dp[0][j]) + arr[r][j];
            }
            // right to left
            dp[2][m-1] = dp[0][m-1] + arr[r][m-1];
            for (int j=m-2; j>=0; j--) {
                dp[2][j] = Math.max(dp[2][j+1], dp[0][j]) + arr[r][j];
            }
            // compare
            for (int c=0; c<m; c++) {
                dp[0][c] = Math.max(dp[1][c], dp[2][c]);
            }
        }

        System.out.println(dp[0][m-1]);
    }

}