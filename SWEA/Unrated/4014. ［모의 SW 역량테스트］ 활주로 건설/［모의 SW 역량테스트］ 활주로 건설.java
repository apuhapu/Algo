import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int[][] map = new int[n][n]; 
			for (int i=0; i<n; i++) {
				map[i] = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			int cnt = 0;
			for (int i=0; i<n; i++) {
				// 가로 확인
				int temp = 1; // 여유 칸
				for (int j=1; j<n; j++) {
					if (Math.abs(map[i][j]-map[i][j-1])>=2) { // 2칸 이상 차이 나는 경우
						break;
					}
					if (map[i][j] == map[i][j-1]) { // 고저차가 없는 경우
						temp = Math.min(x, temp+1); // 여유 칸 추가
					} else if (map[i][j] - map[i][j-1] == 1) { // 1칸 위로 이동
						if (temp != x) { // 여유 칸이 x가 아닐 경우 >> 경사로 설치 불가
							break;
						}
						temp = 1;
					} else { // 1칸 아래로 이동
						if (temp < 0) { // 여유 칸이 음수인 경우 >> 경사로 설치 불가
							break;
						}
						temp = -x+1;
					}
					if (j==n-1) { // 무사히 마지막 칸 도달
						if (temp >= 0) { // 여유 칸이 음수가 아닌 경우 >> 맵 밖으로 경사로 나가는 경우 제외
							cnt++;
						}
					}
				}
				// 세로 확인
				temp = 1;
				for (int j=1; j<n; j++) {
					if (Math.abs(map[j][i]-map[j-1][i])>=2) { // 2칸 이상 차이 나는 경우
						break;
					}
					if (map[j][i] == map[j-1][i]) { // 고저차가 없는 경우
						temp = Math.min(x, temp+1); // 여유 칸 추가
					} else if (map[j][i] - map[j-1][i] == 1) { // 1칸 위로 이동
						if (temp != x) { // 여유 칸이 x가 아닐 경우 >> 경사로 설치 불가
							break;
						}
						temp = 1;
					} else { // 1칸 아래로 이동
						if (temp < 0) { // 여유 칸이 음수인 경우 >> 경사로 설치 불가
							break;
						}
						temp = -x+1;
					}
					if (j==n-1) { // 무사히 마지막 칸 도달
						if (temp >= 0) { // 여유 칸이 음수가 아닌 경우 >> 맵 밖으로 경사로 나가는 경우 제외
							cnt++;
						}
					}
				}
			}
			
			sb.append("#"+t+" "+cnt+"\n");
		}
		System.out.println(sb);
	}
}
