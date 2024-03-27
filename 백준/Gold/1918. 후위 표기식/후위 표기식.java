import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main {
	
	static HashMap<Character, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map.put('(', 0);
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		StringBuilder sb = new StringBuilder();
		char[] sour = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		for (char a : sour) {
			if (a >= 'A' && a <= 'Z') {
				sb.append(a);
			} else if (a == '(') {
				stack.add(a);
			} else if (a == ')') {
				char curr;
				while (true) {
					curr = stack.pop();
					if (curr == '(') {
						break;
					}
					sb.append(curr);
				}
			} else { // +, -, *, /
				while (!stack.isEmpty()) {
					if (map.get(stack.peek())>=map.get(a)) {
						sb.append(stack.pop());
					} else {
						break;
					}
				}
				stack.add(a);
			}
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}
	
}