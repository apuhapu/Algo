import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[] len;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			len = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			if (len[0]==0 && len[1]==0 && len[2]==0) {
				break;
			}
			sb.append(find()).append("\n");
		}
		System.out.println(sb);
	}

	private static String find() {
		int maxIdx = 0;
		for (int i=1; i<3; i++) {
			if (len[maxIdx]<len[i]) {
				maxIdx = i;
			}
		}
		if (len[maxIdx]>=len[(maxIdx+1)%3] + len[(maxIdx+2)%3]) {
			return "Invalid";
		}
		if (len[0]==len[1]&&len[0]==len[2]&&len[1]==len[2]) {
			return "Equilateral";
		}
		if (len[0]==len[1]||len[0]==len[2]||len[1]==len[2]) {
			return "Isosceles ";
		}
		return "Scalene";
	}
}