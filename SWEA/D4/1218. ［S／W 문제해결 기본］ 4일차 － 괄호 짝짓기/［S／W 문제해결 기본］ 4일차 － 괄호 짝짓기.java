import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {

	private static final char[] front = {'(', '[', '{', '<'};
	private static final char[] back = {')', ']', '}', '>'};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		for (int t=1; t<=10; t++) {
			int l = Integer.parseInt(br.readLine());
			char[] ch = br.readLine().toCharArray();
			Stack<Character> st = new Stack<>();
			boolean check = true;
			for (char c : ch) {
				int i = isBack(c);
				if (i!=-1) {
					char top = st.pop();
					if (front[i] == top) {
						continue;
					} else {
						break;
					}
				}
				st.push(c);
			}
			if (st.size() != 0) {
				check = false;
			}
			if (check) {
				sb.append("#"+t+" 1\n");
			} else {
				sb.append("#"+t+" 0\n");
			}
		}
        System.out.println(sb);
	}

	private static int isBack(char c) {
		for (int i=0; i<4; i++) {
			if (back[i]==c) {
				return i;
			}
		}
		return -1;
	}
	
	
}