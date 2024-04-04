import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] map = new int[10];
		int one = 0;
		for (int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<10; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {					
					map[i] += 1 << j;
					one++;
				}
			}
		}
		if (one < 4) { // 1*1만 사용
			System.out.println(one);
		} else if (one == 100) { // 5*5만 사용
			System.out.println(4);
		} else {
			PriorityQueue<Paper> ways = new PriorityQueue<>();
			genBits(one, 0, 5, ways); // 1 개수를 토대로 가능한 모든 경우의 수 탐색
			int cnt = -1;
					
			while (!ways.isEmpty()) {
				Paper curr = ways.poll();
				if (isPossible(curr.bit, map)) {
					cnt = curr.cnt;
					break;
				}
			}
			System.out.println(cnt);
		}
	}

	private static boolean isPossible(int bit, int[] map) {
		int[] visited = new int[10];
		for (int i=0; i<10; i++) {
			visited[i] = map[i];
		}	
		put(bit, 0, visited);
		
		if (visited[0]==-1) {
			return true;
		}
		return false;
	}

	private static void put(int bit, int idx, int[] visited) {
		if (visited[0] == -1) { // 모든 블럭을 채웠을 경우
			return;
		}
		if (bit == 0) { // 성공적으로 다 넣음
			visited[0] = -1;
			return;
		}
		if (idx == 100) { // 끝까지 탐색
			return;
		}
		int r = idx/10;
		int c = idx%10;
		if ((visited[r]&(1<<c))==0) { // 현재 좌표 값이 0인 경우
			put(bit, idx+1, visited);
		} else {
			if ((bit/10)%10>0) { // 둘 수 있는 1*1이 남은 경우
				visited[r] = visited[r]-(1<<c);
				put(bit-10, idx+1, visited);
				if (visited[0] == -1) {
					return;
				}
				visited[r] = visited[r]+(1<<c);
			}
			for (int size=2; size<=5; size++) {
				if (check(size, r, c, visited)) {
					if ((bit/(int) Math.pow(10, size))%10>0) { // 남은 종이가 있는 경우
						for (int i=0; i<size; i++) {
							for (int j=0; j<size; j++) {
								visited[r+i]-=(1<<(c+j));
							}
						}
						int newBit = bit - (int) Math.pow(10, size);
						put(newBit, idx+1, visited);
						if (visited[0] == -1) {
							return;
						}
						for (int i=0; i<size; i++) {
							for (int j=0; j<size; j++) {
								visited[r+i]+=(1<<(c+j));
							}
						}
					}
				} else { // 해당 사이즈가 안맞을 경우
					return;
				}
			}
		}
	}

	private static boolean check(int size, int r, int c, int[] visited) {
		if (r+size-1 == 10 || c+size-1 == 10) {
			return false;
		}
		// 아랫 변 확인
		int nr = r + size - 1;
		for (int nc=c; nc<c+size; nc++) {
			if ((visited[nr]&(1<<nc))==0) {
				return false;
			}
		}
		// 오른쪽 변 확인
		int nc = c + size -1;
		for (int rr=r; rr<r+size-1; rr++) {
			if ((visited[rr]&(1<<nc))==0) {
				return false;
			}
		}
		
		return true;
	}

	private static void genBits(int left, int bit, int idx, PriorityQueue<Paper> ways) {
		if (left == 0) {
			ways.add(new Paper(bit));
			return;
		}
		if (idx == 0) {
			return;
		}
		for (int i=0; i<=5; i++) { // 가능한 종이의 개수는 0~5개
			int nextLeft = left-idx*idx*i;
			if (nextLeft < 0) { // 가지 치기
				return;
			}
			genBits(nextLeft, bit+(int)Math.pow(10, idx)*i, idx-1, ways);
		}
	}

	static class Paper implements Comparable<Paper>{
		int bit;
		int cnt=0;
		public Paper(int bit) {
			this.bit = bit;
			for (int i=0, target = bit/10; i<5; i++, target/=10) {
				this.cnt += target%10;
			}
		}
		@Override
		public int compareTo(Paper o) {
			return this.cnt - o.cnt;
		}
	}
}