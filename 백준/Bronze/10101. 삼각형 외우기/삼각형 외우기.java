import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] degs = new int[3];
		for (int i=0; i<3; i++) {
			degs[i] = Integer.parseInt(br.readLine());
		}
		StringBuilder sb = new StringBuilder();
		
		if (degs[0]+degs[1]+degs[2] != 180) {
			sb.append("Error");
		} else if (degs[0]==60&&degs[1]==60&&degs[2]==60) {
			sb.append("Equilateral");
		} else if (degs[0]==degs[1] || degs[0]==degs[2] || degs[1]==degs[2]) {
			sb.append("Isosceles");
		} else {
			sb.append("Scalene");
		}
		System.out.println(sb);
	}
}