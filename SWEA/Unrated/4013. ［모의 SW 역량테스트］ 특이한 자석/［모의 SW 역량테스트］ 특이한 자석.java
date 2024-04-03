import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static final int[] scores = {1,2,4,8};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int[] magnets;
		int[] pointers;
		for (int t=1; t<=T; t++) {
			int k = Integer.parseInt(br.readLine());
			
			// 자석 정보 입력
			magnets = new int[4];
			pointers = new int[4];
			for (int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<8; j++) {
					magnets[i] += Integer.parseInt(st.nextToken())<<j; 
				}
			}
			//자석 회전
			for (int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken())-1;
				int dir = Integer.parseInt(st.nextToken());
				rotate(idx,dir,2,magnets,pointers);
			}
			
			// 점수 계산
			int score = 0;
			for (int i=0; i<4; i++) {
				score += (magnets[i]&(1<<pointers[i]))!=0?scores[i]:0;
			}
			
			sb.append("#"+t+" "+score+"\n");
		}
		System.out.println(sb);
	}

	private static void rotate(int idx, int dir, int code, int[] magnets, int[] pointers) {
		int leftPointer = (pointers[idx]+8-2)%8;
		int left = (magnets[idx]>>leftPointer)&1;
		int rightPointer = (pointers[idx]+2)%8;
		int right = (magnets[idx]>>rightPointer)&1;
		if (code==2||code==0) { // 왼쪽 톱니 확인
			if (idx!=0) { // 가장 왼쪽이면 확인할 필요 없음
				int leftOppsitePointer = (pointers[idx-1]+2)%8;
				int leftOppsite = (magnets[idx-1]>>leftOppsitePointer)&1;
				if (left+leftOppsite == 1) { // n극 + s극
					rotate(idx-1,(-1)*dir,0,magnets,pointers); // 왼쪽 톱니도 회전
				}
			}
		}
		if (code==2||code==1) { // 오른쪽 톱니 확인
			if (idx!=3) { // 가장 오른쪽이면 확인할 필요 없음
				int rightOppsitePointer = (pointers[idx+1]+8-2)%8;
				int rightOppsite = (magnets[idx+1]>>rightOppsitePointer)&1;
				if (right+rightOppsite == 1) { // n극 + s극
					rotate(idx+1,(-1)*dir,1,magnets,pointers); // 오른쪽 톱니도 회전
				}
			}
		}
		// 현재 톱니 회전
		if (dir==1) { // clockwise
			pointers[idx] = (pointers[idx]+8-1) % 8; 
		} else { // counterclockwise
			pointers[idx] = (pointers[idx]+1) % 8;
		}
	}
}
