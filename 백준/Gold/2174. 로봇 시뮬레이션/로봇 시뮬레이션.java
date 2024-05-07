import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int a,b,n,m;
	static int[] dy = {1,0,-1,0};
	static int[] dx = {0,1,0,-1};
	static int[][] table;
	static Robot[] robots;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		table = new int[b+1][a+1];
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		robots = new Robot[n+1];
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			table[y][x] = i;
			robots[i] = new Robot(
					y,
					x,
					st.nextToken()
					);
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			String comm = st.nextToken();
			int repeat = Integer.parseInt(st.nextToken());
			if (comm.equals("F")) {
				if (!walk(idx, repeat)) {
					return;
				}
			} else {
				repeat %= 4;
				int temp = comm.equals("L") ? 4-repeat : repeat;
				robots[idx].dir = (robots[idx].dir+temp)%4;
			}
		}
		System.out.println("OK");
	}
	
	private static boolean walk(int idx, int repeat) {
		Robot robot = robots[idx];
		table[robot.y][robot.x] = 0;
		int x = robot.x;
		int y = robot.y;
		for (int i=0; i<repeat; i++) {
			x += dx[robot.dir];
			y += dy[robot.dir];
			if (x==0||y==0||x>a||y>b) {
				System.out.println("Robot "+idx+" crashes into the wall");
				return false;
			}
			if (table[y][x]!=0) {
				System.out.println("Robot "+idx+" crashes into robot "+table[y][x]);
				return false;
			}
		}
		table[y][x] = idx;
		robot.x = x;
		robot.y = y;
		return true;
	}

	static class Robot {
		int y, x, dir;
		public Robot(int y, int x, String dir) {
			this.y = y;
			this.x = x;
			if (dir.equals("N")) {
				this.dir = 0;				
			} else if (dir.equals("E")) {
				this.dir = 1;
			} else if (dir.equals("S")) {
				this.dir = 2;
			} else {
				this.dir = 3;
			}
		}
	}
}