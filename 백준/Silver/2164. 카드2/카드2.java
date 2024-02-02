import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Queue<Integer> que = new LinkedList<>();
		for (int i=1; i<n+1; i++) {
			que.add(i);
		}
		boolean tic = false;
		int a;
		while (que.size()>1) {
			a = que.poll();
			if (tic) {
				que.add(a);
			}
			tic = !tic;
		}
		System.out.println(que.poll());
	}
}