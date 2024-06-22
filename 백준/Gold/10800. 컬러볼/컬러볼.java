import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Ball[] balls = new Ball[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            balls[i] = new Ball(i,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(balls);

        int[] result = new int[n];
        int[] sizes = new int[200_001];
        int sum = 0;
        int tempSize = 0;
        ArrayList<Ball> temp = new ArrayList<>();

        for (Ball ball : balls) {
            if (tempSize != ball.size) {
                tempSize = ball.size;
                for (Ball b : temp) {
                    sizes[b.color] += b.size;
                    sum += b.size;
                }
                temp.clear();
            }
            temp.add(ball);
            result[ball.no] = sum - sizes[ball.color];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

    static class Ball implements Comparable<Ball> {
        int no, color, size;

        public Ball(int no, int color, int size) {
            this.no = no;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Ball o) {
            return size - o.size;
        }
    }
}