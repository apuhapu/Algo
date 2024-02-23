import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			Pos start = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			Pos end = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			int n = Integer.parseInt(br.readLine());
			int passCnt = 0;
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				int ds = start.dSquare(x, y);
				int de = end.dSquare(x, y);
				boolean isInnerStart = (r*r-ds > 0);
				boolean isInnerEnd = (r*r-de > 0);
				if (isInnerStart != isInnerEnd) {
					passCnt++;
				}
			}
			sb.append(passCnt+"\n");
		}
		System.out.println(sb);
	}
	
	static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int dSquare(int px, int py) {
			int dx = x - px;
			int dy = y - py;
			return dx*dx+dy*dy;
		}
		
	}
}