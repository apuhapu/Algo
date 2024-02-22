import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        if (s/2==0 || n>s) {
            int[] answer = {-1};
            return answer;
        }
        int[] answer = new int[n];
        Arrays.fill(answer, s/n);
		int left = s%n;
		for (int i=n-1; i>=0; i--) {
			if (left-- == 0) {
				break;
			}
			answer[i]++;
		}
        return answer;
    }
}