import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Waiting[] waits = new Waiting[n];
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			waits[i] = new Waiting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(waits);
		
		ArrayList<Integer> list = new ArrayList<>();
		PriorityQueue<Computer> freeCom = new PriorityQueue<>((x,y)->x.idx-y.idx);
		PriorityQueue<Computer> workingCom = new PriorityQueue<>((x,y)->x.endTime-y.endTime);
		for (Waiting wait : waits) {
			// free comp 최신화
			while (!workingCom.isEmpty()) { // 사용 중인 컴퓨터 중 사용이 끝난 컴퓨터를 free comp에 채우기
				Computer temp = workingCom.peek();
				if (temp.endTime>wait.start) { // 현재 시간 기준 모든 working com이 사용 중인 경우
					break;
				}
				freeCom.add(workingCom.poll());
			}
			if (!freeCom.isEmpty()) { // 비어있는 컴퓨터가 있는 경우
				Computer com = freeCom.poll();
				list.set(com.idx, list.get(com.idx)+1); // 사용횟수 1 추가
				com.endTime = wait.end;
				workingCom.add(com);
			} else { // 비어있는 컴퓨터가 없어 새로 추가해야하는 경우
				workingCom.add(new Computer(wait.end, list.size()));
				list.add(1); // 사용 횟수 추가
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append("\n");
		for (int cnt : list) {
			sb.append(cnt).append(" ");
		}
		System.out.println(sb);
	}
	
	static class Computer {
		int endTime;
		int idx;
		public Computer(int endTime, int idx) {
			this.endTime = endTime;
			this.idx = idx;
		}
	}
	static class Waiting implements Comparable<Waiting> {
		int start;
		int end;
		public Waiting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Waiting o) {
			return start - o.start;
		}
	}
}