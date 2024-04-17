import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int lastTime = 24*60*60;
	static int[] dt;
	static PriorityQueue<Train> trains;
	
	public static void main(String[] args) throws IOException {
		init();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sTime = br.readLine().split(":");
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken().substring(1));
			int e = Integer.parseInt(st.nextToken().substring(1));
			String[] time = st.nextToken().split(":");
			int t = Integer.parseInt(time[0])*60*60 + Integer.parseInt(time[1])*60;
			trains.add(new Train(s, e, t));
		}
		
		int currStation = 225;
		int currTime = Integer.parseInt(sTime[0])*60*60 + Integer.parseInt(sTime[1])*60 + Integer.parseInt(sTime[2]);
		while (currStation < 272 & !trains.isEmpty()) {
			Train train = trains.poll();
			
			if (train.sPlatform>currStation || train.ePlatform<=currStation) {
				continue;
			}
			int partialTime = dt[currStation-209] - dt[train.sPlatform-209] - 20 + train.startTime;
			if (currTime > partialTime) { // 현재 역으로 도착하는 시간이 현재 시간보다 과거인 경우
				continue;
			}
			currStation = train.ePlatform;
			currTime = dt[train.ePlatform-209] - dt[train.sPlatform-209] - 20 + train.startTime;
			
		}
		
		
		StringBuilder sb = new StringBuilder();
		if (currStation != 272 || currTime>=lastTime) {
			sb.append(-1);
		} else {
			int h = currTime/(60*60);
			currTime %= 60*60;
			int M = currTime/60;
			int m = currTime%60;
			if (h<10) {
				sb.append(0).append(h);
			} else {
				sb.append(h);
			}
			sb.append(":");
			if (M<10) {
				sb.append(0).append(M);
			} else {
				sb.append(M);
			}
			sb.append(":");
			if (m<10) {
				sb.append(0).append(m);
			} else {
				sb.append(m);
			}
		}
		System.out.println(sb);
	}
	
	private static void init() {
		dt = new int[272-209+1];
		HashMap<Integer, Integer> special = new HashMap<>();
		special.put(211, 3);
		special.put(221, 4);
		special.put(222, 4);
		special.put(223, 3);
		special.put(226, 3);
		special.put(239, 3);
		special.put(246, 4);
		special.put(248, 5);
		special.put(250, 4);
		special.put(251, 3);
		special.put(257, 3);
		special.put(267, 3);
		for (int idx=210; idx<=272; idx++) {
			int time = special.containsKey(idx) ? special.get(idx) : 2;
			dt[idx-209] = dt[idx-209-1] + 60*time;
			if (idx!=210) {
				dt[idx-209]+=20;
			}
		}
		
		trains = new PriorityQueue<>();
	}

	static class Train implements Comparable<Train> {
		int sPlatform;
		int ePlatform;
		int startTime;
		public Train(int sPlatform, int ePlatform, int startTime) {
			this.sPlatform = sPlatform;
			this.ePlatform = ePlatform;
			this.startTime = startTime;
		}
		@Override
		public int compareTo(Train o) {
			int a = dt[272-209] - dt[this.sPlatform-209] - 20 + this.startTime;
			int b = dt[272-209] - dt[o.sPlatform-209] - 20 + o.startTime;
			return a-b;
		}
	}
}