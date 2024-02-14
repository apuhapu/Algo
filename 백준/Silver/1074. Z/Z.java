import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 사분면 이름
 * 1 2
 * 3 4
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int currR = 0, currC = 0, findNum = 0, diff;
		while (N>0) {
			diff = 1<<(N-1); // 한 사분면의 크기
			if (currR + diff > r) {
				// 1사분면은 findNum의 변화 X
				// 2사분면
				if (currC + diff <= c) {
					findNum += 1<<(2*N-2);
					currC += diff;
				}
			} else {
				currR += diff;
				// 3사분면
				if (currC + diff > c) {
					findNum += 2*(1<<(2*N-2));
				} else {
					findNum += 3*(1<<(2*N-2));
					currC += diff;
				}
			}
			N--;
		}
		System.out.println(findNum);
	}
}