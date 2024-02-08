import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution {

    static int n, dish, diff;
    static int[][] s;
    static Map<Integer, Integer> score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            n = Integer.parseInt(br.readLine());
            s = new int[n][n];
            score = new HashMap<>();
            for (int i = 0; i < n; i++) {
                s[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
            diff = Integer.MAX_VALUE;
            divide(0,0);
            fill();
            calDiff();
            sb.append("#"+t+" "+diff+"\n");
        }
        System.out.println(sb);
    }

    static void divide(int len, int start) {
        if (len == n/2) {
            score.put(dish, -1);
            return;
        }
        for (int i=start; i<n; i++) {
            dish = dish | 1<<i;
            divide(len+1, i+1);
            dish = dish & ~(1<<i);
        }
    }

    static void fill() {
        for (int d:score.keySet()) {
            int sc = 0;
            int[] key = new int[n/2];
            int i=0;
            int j=0;
            while (i<n/2) {
                if ((d&1<<j)!=0) {
                    key[i++] = j;
                }
                j++;
            }
            for (int a=0; a<n/2; a++) {
                for (int b=a+1;b<n/2; b++) {
                    sc += s[key[a]][key[b]] + s[key[b]][key[a]];
                }
            }

            score.put(d, sc);
        }
    }

    static void calDiff() {
        Iterator<Integer> itr = score.keySet().iterator();
        while (itr.hasNext()) {
            int key = itr.next();
            if (score.get(key)==-1) {
                continue;
            }
            int rev = reverse(key);
            int d = Math.abs(score.get(key)-score.get(rev));
            score.put(rev, -1);
            if (diff > d) {
                diff = d;
            }
        }

    }

    static int reverse(int bit) {
        int rev = 0;
        for (int i=0; i<n; i++) {
            if ((bit & 1<<i) == 0) {
                rev += 1<<i;
            }
        }
        return rev;
    }
}