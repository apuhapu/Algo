import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		Gift[] gifts = new Gift[n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			gifts[i] = new Gift(Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
		}
		Arrays.sort(gifts);
		
		long maxH = 0;
		int s=0;
		int e=0;
		long currSum = gifts[0].happiness;
		while (true) {
			int diff = gifts[e].price - gifts[s].price;
			if (diff >= d) { // 최소 가격차 이상
				currSum -= gifts[s++].happiness;
			} else {
				maxH = Math.max(maxH, currSum);
				if (e == n-1) {
					break;
				}
				currSum += gifts[++e].happiness;
			}
		}
		
		
		System.out.println(maxH);
	}
	
	static class Gift implements Comparable<Gift> {
		int price;
		long happiness;
		public Gift(int price, long happiness) {
			this.price = price;
			this.happiness = happiness;
		}
		@Override
		public int compareTo(Gift o) {
			return price - o.price;
		}
	}
}