import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int leftDust = 0, r, c, T;
	static int[] airClenaer = new int[2];
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		for (int i=0; i<r; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		init();
		for (int t=1; t<=T; t++) {
			map = diffusion();
			cleanerOn();
		}
		System.out.println(leftDust);
	}
	
	private static void init() {
		int pt = 0;
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				if (map[i][j] == -1) {
					airClenaer[pt++] = i;
					continue;
				}
				leftDust += map[i][j];
			}
		}
	}

	private static int[][] diffusion() {
		int[][] newBoard = new int[r][c];
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				if (map[i][j] < 5) {
					newBoard[i][j] += map[i][j];
					continue;
				}
				int cnt = 0;
				for (int k=0; k<4; k++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					if (nr<0||nr>=r||nc<0||nc>=c||map[nr][nc]==-1) {
						continue;
					}
					cnt++;
					newBoard[nr][nc]+= map[i][j] / 5;
				}
				newBoard[i][j] += map[i][j] - cnt*(map[i][j]/5);
			}
		}
		
		return newBoard;
	}
	
	private static void cleanerOn() {
		// upper part
		leftDust -= map[airClenaer[0]-1][0];
		for (int i = airClenaer[0]-1; i>0; i--) {
			map[i][0] = map[i-1][0];
		}
		for (int j=0; j<c-1; j++) {
			map[0][j] = map[0][j+1];
		}
		for (int i=0; i<airClenaer[0]; i++) {
			map[i][c-1] = map[i+1][c-1];
		}
		for (int j=c-1; j>1; j--) {
			map[airClenaer[0]][j] = map[airClenaer[0]][j-1];
		}
		map[airClenaer[0]][1] = 0;
		
		// lower part
		leftDust -= map[airClenaer[1]+1][0];
		for (int i = airClenaer[1]+1; i<r-1; i++) {
			map[i][0] = map[i+1][0];
		}
		for (int j=0; j<c-1; j++) {
			map[r-1][j] = map[r-1][j+1];
		}
		for (int i=r-1; i>airClenaer[1]; i--) {
			map[i][c-1] = map[i-1][c-1];
		}
		for (int j=c-1; j>1; j--) {
			map[airClenaer[1]][j] = map[airClenaer[1]][j-1];
		}
		map[airClenaer[1]][1] = 0;
	}
}