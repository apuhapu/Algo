import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int blackMax=0, whiteMax=0, n;
	static boolean[] checkSlash, checkBackSlash;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		ArrayList<Integer> black = new ArrayList<>(),
				white = new ArrayList<>();
		for (int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<n; c++) {
				if (st.nextToken().equals("1")) { // 기물을 놓을 수 있다면
					if ((r+c)%2==0) { // black
						black.add(10*r+c);
					} else {
						white.add(10*r+c);
					}
				}
			}
		}
		// 대각선 비숍 있는지 확인하는 배열
		checkSlash = new boolean[2*n-1];
		checkBackSlash = new boolean[2*n-1];
		
		if (!black.isEmpty()) {
			bishop(0, 0, black);
		}
		if (!white.isEmpty()) {
			bishop(0, 0, white);
		}
		System.out.println(blackMax+whiteMax);
	}


	private static void bishop(int cnt, int idx, ArrayList<Integer> board) {
		if (idx == board.size()) {
			int r = board.get(idx-1)/10;
			int c = board.get(idx-1)%10;
			if ((r+c)%2==0) {
				blackMax = Math.max(blackMax, cnt);
			} else {
				whiteMax = Math.max(whiteMax, cnt);
			}
			return;
		}
		int r = board.get(idx)/10;
		int c = board.get(idx)%10;
		
		bishop(cnt, idx+1, board); // 해당 자리에 안 놓고 넘기기
		if (checkSlash[r+c]||checkBackSlash[n-1-r+c]) { // 해당 자리에 못 놓을 경우
			return;
		}
		checkSlash[r+c] = true;
		checkBackSlash[n-1-r+c] = true;
		bishop(cnt+1, idx+1, board);
		checkSlash[r+c] = false;
		checkBackSlash[n-1-r+c] = false;
	}
}