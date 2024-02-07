import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		for (int i=0; i<n; i++) {
			board[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		int[] command = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		for (int c : command) {
			f(c);
		}
		
		for (int i=0; i<board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				sb.append(board[i][j]);
				if (j < board[0].length-1) {
					sb.append(" ");
				} else {
					sb.append("\n");
				}
			}
		}
		System.out.println(sb);
	}

	private static void f(int i) {
		switch (i) {
			case 1:
				f1();
				break;
			case 2:
				f2();
				break;
			case 3:
				f3();
				break;
			case 4:
				f4();
				break;
			case 5:
				f5();
				break;
			case 6:
				f6();
				break;
		}
	}

	private static void f1() {
		int n = board.length;
		int m = board[0].length;
		int[][] temp = new int[n][m];
		for (int i = 0; i < n; i++) {
			temp[n - 1 - i] = board[i];
		}
		board = temp;
	}

	private static void f2() {
		int n = board.length;
		int m = board[0].length;
		int[][] temp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				temp[i][j] = board[i][m - 1 - j];
			}
		}
		board = temp;
	}

	private static void f3() {
		int n = board.length;
		int m = board[0].length;
		int[][] temp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				temp[i][j] = board[n - 1 - j][i];
			}
		}
		board = temp;
	}

	private static void f4() {
		int n = board.length;
		int m = board[0].length;
		int[][] temp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				temp[i][j] = board[j][m - 1 - i];
			}
		}
		board = temp;
	}

	private static void f5() {
		int n = board.length;
		int m = board[0].length;
		int[][] temp = new int[n][m];
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				temp[i][j] = board[i + n / 2][j];
			}
			for (int j = m / 2; j < m; j++) {
				temp[i][j] = board[i][j - m / 2];
			}
		}
		for (int i = n / 2; i < n; i++) {
			for (int j = 0; j < m / 2; j++) {
				temp[i][j] = board[i][j + m / 2];
			}
			for (int j = m / 2; j < m; j++) {
				temp[i][j] = board[i - n / 2][j];
			}
		}
		board = temp;
	}

	private static void f6() {
		int n = board.length;
		int m = board[0].length;
		int[][] temp = new int[n][m];
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				temp[i][j] = board[i][j + m / 2];
			}
			for (int j = m / 2; j < m; j++) {
				temp[i][j] = board[i + n / 2][j];
			}
		}
		for (int i = n / 2; i < n; i++) {
			for (int j = 0; j < m / 2; j++) {
				temp[i][j] = board[i - n / 2][j];
			}
			for (int j = m / 2; j < m; j++) {
				temp[i][j] = board[i][j - m / 2];
			}
		}
		board = temp;
	}
}