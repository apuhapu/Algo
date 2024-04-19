import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st;
		ArrayList<Integer> list1 = new ArrayList<>(3);
		ArrayList<Integer> list2 = new ArrayList<>(3);
		for (int i=0; i<6; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			if (i%2==0) {
				list1.add(l);
			} else {
				list2.add(l);
			}
		}
		int maxL1 = findMaxIdx(list1);
		int maxL2 = findMaxIdx(list2);
		int subL1 = 0;
		int subL2 = 0;
		if (maxL1 == maxL2) {
			subL1 = (maxL1+2)%3;
			subL2 = (maxL2+1)%3;
		} else {
			subL1 = (maxL1+1)%3;
			subL2 = (maxL2+2)%3;
		}
		int s = list1.get(maxL1)*list2.get(maxL2) - list1.get(subL1)*list2.get(subL2);
		System.out.println(s*k);
	}
	
	private static int findMaxIdx(ArrayList<Integer> list) {
		int idx=0;
		for (int i=1; i<list.size(); i++) {
			if (list.get(idx) < list.get(i)) {
				idx = i;
			}
		}
		return idx;
	}
}