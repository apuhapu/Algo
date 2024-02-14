import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	static int[][] matches = {
			{0,0,0,0,0,1,1,1,1,2,2,2,3,3,4},
			{1,2,3,4,5,2,3,4,5,3,4,5,4,5,5}
	};
	static int[] isPo = new int[4];
	static int[][] result, temp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		result = new int[6][3];
		for (int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			boolean isFive = true;
			for (int r=0; r<6; r++) {
				int sum = 0;
				for (int c=0; c<3; c++) {
					result[r][c] = Integer.parseInt(st.nextToken());
					sum += result[r][c];
				}
				if (sum != 5) {
					isFive = false;
				}
			}
			if (isFive) {
				temp = new int[6][3];
				if (check(0)) {
					isPo[i] = 1;
				}
			}
		}
		
		for (int i=0; i<4; i++) {
			sb.append(isPo[i]);
			if (i<3) {
				sb.append(" ");
			}
		}
		System.out.println(sb);
	}

	private static boolean check(int idx) {
		if (idx == 15) {
			return true;
		} else {
			for (int i=0; i<3; i++) {
				if (temp[ matches[0][idx] ][i]<result[ matches[0][idx] ][i]
						&& temp[ matches[1][idx] ][2-i]<result[matches[1][idx]][2-i]) {
					temp[matches[0][idx]][i]++;
					temp[matches[1][idx]][2-i]++;
					if (check(idx+1)) {
						return true;
					}
					temp[matches[0][idx]][i]--;
					temp[matches[1][idx]][2-i]--;
				}
			}
			return false;
		}
	}

}