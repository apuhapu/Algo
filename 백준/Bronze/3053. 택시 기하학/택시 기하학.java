import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double r = Double.parseDouble(br.readLine());
		StringBuilder sb = new StringBuilder();
		sb.append(r*r*Math.PI).append("\n");
		sb.append(2*r*r);
		System.out.println(sb);
	}
}