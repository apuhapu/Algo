import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static String T, P;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = br.readLine();
		P = br.readLine();
		kmp(T,P);
	}
	
	private static void kmp(String t, String p) {
		int[] table = findTable(p);
		int tl = t.length();
		int nl = p.length();
		ArrayList<Integer> ans = new ArrayList<>();
		int idx=0;
		for (int i=0; i<tl; i++) {
			while (idx>0 && t.charAt(i) != p.charAt(idx)) {
				idx = table[idx-1];
			}
			if (t.charAt(i)==p.charAt(idx)) {
				if (idx == nl-1) {
					ans.add(i-idx+1);
					idx = table[idx];
				} else {
					idx += 1;
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append(ans.size()).append("\n");
		for (int a : ans) {
			sb.append(a).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int[] findTable(String p) {
		int n = p.length();
		int[] table = new int[n];
		int s = 0;
		for(int e=1; e<n; e++) {
			while (s>0 && p.charAt(e) != p.charAt(s)) {
				s = table[s-1];
			}
			if (p.charAt(e) == p.charAt(s)) {
				table[e] = ++s;
			}
			
		}
		return table;
	}
}