import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		HashSet<Integer> check = new HashSet<>();
		int result = 1;
		for (int i=0; i<n; i++) {
			if (check.contains(arr[i])) {
				continue;
			}
			int temp = 1;
			int k = -1;
			for (int j=0; j<n; j++) {
				int v = arr[j];
				if (v == arr[i]) {
					continue;
				}
				if (v != k) {
					k = v;
					temp = 1;
				} else {
					result = Math.max(result, ++temp);
				}
			}
			check.add(arr[i]);
		}
		System.out.println(result);
	}
}