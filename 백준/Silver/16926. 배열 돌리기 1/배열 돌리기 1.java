import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[][] inputBoard =  new int[n][m];
		int[][] rotateBoard = new int[n][m];
		for (int i=0; i<n; i++) {
			inputBoard[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		
		
		for (int deg = 0; deg <= Math.min((n-1)/2, (m-1)/2); deg++) {
			int total = 2*(n-2*deg)+2*(m-2*deg)-4;
			int newR = r % total;
			Index head = new Index(deg, deg);
			for (int i=0; i<newR; i++) {
				head = counterClockWise(head, n, m, deg);
			}
			rotateBoard[head.row][head.col] = inputBoard[deg][deg];
			Index start = new Index(deg,deg);
			for (int i=0; i<total-1; i++) {
				head = counterClockWise(head, n, m, deg);
				start = counterClockWise(start, n, m, deg);
				rotateBoard[head.row][head.col] = inputBoard[start.row][start.col];
			}
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				System.out.print(rotateBoard[i][j]);
				if (j!=m-1) {
					System.out.print(" ");
				}
			}
			if (i != n-1) {
				System.out.println();
			}
		}
	}
	
	private static Index counterClockWise(Index idx, int n, int m, int deg) {
		int r = idx.row;
		int c = idx.col;
		if (c == deg) {
			if (r == n-deg-1) {
				c++;
			} else {
				r++;
			}
		} else if (r == n-deg-1) {
			if (c == m-deg-1) {
				r--;
			} else {
				c++;
			}
		} else if (c == m-deg-1) {
			if (r == deg) {
				c--;
			} else {
				r--;
			}
		} else {
			c--;
		}
		return new Index(r,c);
	}
	
	static class Index {
		int row;
		int col;
		public Index(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
