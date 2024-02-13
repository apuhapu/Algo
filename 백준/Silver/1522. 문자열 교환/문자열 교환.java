import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 구현 방법
 * 1. 전체 문자열에서 a의 개수 카운트 O(N)
 * 2. 윈도우의 크기를 N으로 하여 가장 많이 a를 포함하고 있는 구간 찾기 (투 포인터 사용 및 원형이므로 전체 탐색) O(N)
 * 3. 1에서 구한 개수 - 2에서 구한 최대 값 = 교환 횟수
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cntA = 0;
		char[] ch = br.readLine().toCharArray();
		for (char a : ch) {
			if (a == 'a') {
				cntA++;
			}
		}
		int start = 0;
		int end = 0 + cntA - 1;
		int sub = 0;
		
		for (int i=0; i<cntA; i++) {
			if (ch[i] == 'a') {
				sub++;
			}
		}
		int subMax = sub;
		for (int i=0; i<ch.length; i++) {
			end = (end+1)%ch.length;
			if (ch[end] == 'a') {
				sub++;
			}
			if (ch[start++] == 'a') {
				sub--;
			}
			
			subMax = Math.max(subMax, sub);
			if (subMax == cntA) {
				break;
			}
		}
		System.out.println(cntA-subMax);
	}
}