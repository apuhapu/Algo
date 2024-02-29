import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int minT, n;
	static List<Integer> bits;
	static List<Pos> people;
	static List<Pos> stairs;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			people = new ArrayList<>(10);
			stairs = new ArrayList<>(2);
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<n; j++) {
					int x = Integer.parseInt(st.nextToken());
					if (x == 1) {
						people.add(new Pos(i,j,x));
					} else if (x>1) {
						stairs.add(new Pos(i,j,x));
					}
				}
			}
			for (Pos p : people) {
				p.calD(stairs.get(0), stairs.get(1));
			}
			minT = Integer.MAX_VALUE;
			findMinT();
			sb.append("#"+t+" "+minT+"\n");
		}
		System.out.println(sb);
	}
	
	private static void findMinT() {
		// 모든 사람 각각이 이용할 계단 목록 / 비트 마스킹 이용 : 0>>1번 계단, 1>>2번 계단
		bits = new ArrayList<>();
		genBits(0,0);
		for (int bit : bits) {
			simul(bit);
		}
	}

	private static void genBits(int idx, int bit) {
		if (idx == people.size()) {
			bits.add(bit);
			return;
		}
		genBits(idx+1, bit|1<<idx);
		genBits(idx+1, bit);
	}
	
	private static void simul(int bit) {
		int t = 0;
		PriorityQueue<Pos> s1Waitting = new PriorityQueue<>((x,y)-> x.d1-y.d1);
		PriorityQueue<Pos> s2Waitting = new PriorityQueue<>((x,y)-> x.d2-y.d2);
		for (int i=0; i<people.size(); i++) {
			if ((bit&1<<i)==0) {
				s1Waitting.add(people.get(i));
			} else {
				s2Waitting.add(people.get(i));
			}
		}
		int[] s1 = new int[3];
		int[] s2 = new int[3];
		int left = people.size();
		while (left>0) {
			t++;
			// 계단 파트
			for (int i=0; i<3; i++) {
				// 계단 1
				if (s1[i]>0) {
					s1[i]--;
					if (s1[i]==0) {
						left--; // 한 명이 건물 밖으로 나감
					}
				} 
                if (s1[i]==0) {
					// 다음 사람 계단에 투입
					if (!s1Waitting.isEmpty()) {
						Pos wait = s1Waitting.peek();
						if (wait.d1+1 <= t) {
							s1Waitting.poll();
							s1[i] = stairs.get(0).val;
						}
					}
				}
				// 계단 2
				if (s2[i]>0) {
					s2[i]--;
					if (s2[i]==0) {
						left--; // 한 명이 건물 밖으로 나감
					}
				} 
                if (s2[i]==0) {
					// 다음 사람 계단에 투입
					if (!s2Waitting.isEmpty()) {
						Pos wait = s2Waitting.peek();
						if (wait.d2+1 <= t) {
							s2Waitting.poll();
							s2[i] = stairs.get(1).val;
						}
					}
				}
			}
		}
		minT = Math.min(minT, t);
	}

	static class Pos {
		int r;
		int c;
		int val;
		int d1;
		int d2;
		public Pos(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
		public void calD(Pos c1, Pos c2) {
			d1 = Math.abs(r-c1.r)+Math.abs(c-c1.c);
			d2 = Math.abs(r-c2.r)+Math.abs(c-c2.c);
		}
	}
}
