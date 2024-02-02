import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n==1) {
			System.out.println(1);
		} else {
			int m = 1;
			while (m < n) {
				m *= 2;
			}
			int l = n-m/2;
			System.out.println(2*l);
		}
	}
}