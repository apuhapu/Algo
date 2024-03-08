import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] locations = new int[3][2];
		for (int i=0; i<3; i++) {
			locations[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		int v1_x = locations[0][0] - locations[1][0];
		int v1_y = locations[0][1] - locations[1][1];
		int v2_x = locations[2][0] - locations[1][0];
		int v2_y = locations[2][1] - locations[1][1];
		int outer = v1_x * v2_y - v1_y * v2_x;
		
		int result = 0;
		if (outer > 0) {
			result = -1;
		} else if (outer < 0) {
			result = 1;
		}
		System.out.println(result);
	}
}