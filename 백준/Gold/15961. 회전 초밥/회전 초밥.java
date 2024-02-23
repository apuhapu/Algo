import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] table = new int[n];
		for (int i=0; i<n; i++) {
			table[i] = Integer.parseInt(br.readLine());
		}
		int[] foodCnt = new int[d+1];
		foodCnt[c]++;
	
		int currCategory = 1;
		int start = 0;
		int end = k-1;
		for (int i=0; i<k; i++) {
			if (foodCnt[table[i]]++ == 0) {
				currCategory++;
			}
		}
		int maxCategory = currCategory;
		for (int i=0; i<n; i++) {
			if (--foodCnt[table[start++]] == 0) {
				currCategory--;
			}
			end = (end+1)%n;
			if (foodCnt[table[end]]++ == 0) {
				currCategory++;
			}
			maxCategory = Math.max(maxCategory, currCategory);
		}
		System.out.println(maxCategory);
	}
}