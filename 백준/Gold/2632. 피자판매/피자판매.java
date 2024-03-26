import java.io.*;
import java.util.*;

public class Main {
	
	static int x, m, n, wholeASize, wholeBSize;
	static int[] pizzaPieceA, pizzaPieceB;
	static HashMap<Integer, Integer> pizzaA, pizzaB;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		x = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		pizzaPieceA = new int[m];
		pizzaPieceB = new int[n];
		wholeASize = 0;
		wholeBSize = 0;
		for (int i=0; i<m; i++) {
			pizzaPieceA[i] = Integer.parseInt(br.readLine());
			wholeASize += pizzaPieceA[i];
		}
		
		for (int i=0; i<n; i++) {
			pizzaPieceB[i] = Integer.parseInt(br.readLine());
			wholeBSize += pizzaPieceB[i];
		}
		pizzaA = new HashMap<>();
		pizzaB = new HashMap<>();
		pizzaA.put(0, 1); // 0 세는 용
		pizzaB.put(0, 1);
		calAllSize();
		int totalCnt = 0;
		for (int aSize : pizzaA.keySet()) {
			int leftSize = x - aSize;
			if (pizzaB.containsKey(leftSize)) {
				totalCnt += pizzaA.get(aSize)*pizzaB.get(leftSize);
			}
		}
		System.out.println(totalCnt);
	}

	private static void calAllSize() {
		// for A
		int s = 0;
		int e;
		int size;
		while (s<m) {
			e = s;
			size = pizzaPieceA[e];
			putSize(true, size);
			e = (e+1)%m;
			while (e!=s) {
				size += pizzaPieceA[e];
				putSize(true, size);
				e = (e+1)%m;
			}
			s++;
		}
		pizzaA.put(wholeASize, 1);
		// for B
		s = 0;
		while (s<n) {
			e = s;
			size = pizzaPieceB[e];
			putSize(false, size);
			e = (e+1)%n;
			while (e!=s) {
				size += pizzaPieceB[e];
				putSize(false, size);
				e = (e+1)%n;
			}
			s++;
		}
		pizzaB.put(wholeBSize, 1);
	}
	
	private static void putSize(boolean isA, int size) {
		int cnt = 1;
		if (isA) {
			if (pizzaA.containsKey(size)) {
				cnt+=pizzaA.get(size);
			}
			pizzaA.put(size, cnt);
		} else {
			if (pizzaB.containsKey(size)) {
				cnt+=pizzaB.get(size);
			}
			pizzaB.put(size, cnt);
		}
	}
}