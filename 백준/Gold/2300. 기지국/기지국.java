import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int maxCost = 2_000_000;

    static int n;
    static Pos[] towers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        towers = new Pos[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            towers[i] = new Pos(x, y);
        }
        Arrays.sort(towers);

        int[] dp = new int[n+1];
        Arrays.fill(dp, maxCost);
        dp[0] = 0;
        dp[1] = 2*towers[0].y;
        for (int i = 1; i < n; i++) {
            int h = towers[i].y;
            for (int j = i; j >= 0; j--) {
                h = Math.max(h, towers[j].y); // j~i 번째까지 최대 높이
                int temp = Math.max(towers[i].x-towers[j].x, 2*h);
                dp[i+1] = Math.min(dp[i+1], dp[j] + temp);
            }
        }

        System.out.println(dp[n]);

    }

    static class Pos implements Comparable<Pos> {
        int x, y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = Math.abs(y);
        }

        @Override
        public int compareTo(Pos o) {
            return x-o.x;
        }
    }
}