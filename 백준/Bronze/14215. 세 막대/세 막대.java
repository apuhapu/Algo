import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] sticks = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int maxIdx = 0;
		for (int i=1; i<3; i++) {
			if (sticks[maxIdx] < sticks[i]) {
				maxIdx = i;
			}
		}
		int round = 0;
		if (sticks[maxIdx] >= sticks[(maxIdx+1)%3] + sticks[(maxIdx+2)%3]) {
			round += 2*(sticks[(maxIdx+1)%3] + sticks[(maxIdx+2)%3]) - 1;
		} else {
			round += sticks[0]+sticks[1]+sticks[2];
		}
		System.out.println(round);
	}
}