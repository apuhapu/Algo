import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		double x = 1.0*arr[0]/Math.sqrt(arr[1]*arr[1]+arr[2]*arr[2]);
		int h = (int) (arr[1]*x);
		int w = (int) (arr[2]*x);
		System.out.println(h+" "+w);
	}
}