import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int L, C, isVowel;
	static char[] ch, out;
	static int cntVowel, cntConsonant;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		ch = br.readLine().replaceAll(" ", "").toCharArray();
		Arrays.sort(ch);
		isVowel = 0;
		for (int i=0; i<ch.length; i++) {
			if ("aeiou".indexOf(ch[i]) != -1) {
				isVowel |= (1 << i);
			}
		}
		cntVowel = 0;
		cntConsonant = 0;
		out = new char[L];
		find(0, 0);
		System.out.println(sb);
	}
	
	private static void find(int len, int start) {
		if (len == L) {
			if (cntVowel>=1 && cntConsonant>=2) {
				for (int i=0; i<L; i++) {
					sb.append(out[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i=start; i<C; i++) {
			out[len] = ch[i];
			if ((isVowel & (1<<i)) == 0) { // 자음
				cntConsonant++;	
				find(len+1, i+1);
				cntConsonant--;
			} else { // 모음
				cntVowel++;
				find(len+1, i+1);
				cntVowel--;
			}
		}
	}
}