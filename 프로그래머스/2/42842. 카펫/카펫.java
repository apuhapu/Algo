class Solution {
    public int[] solution(int brown, int yellow) {
        int range = (brown + 4) / 2;
		int area = brown + yellow;
		int[] answer = new int[2];
		for (int v=1; v<range; v++) {
			if (v*(range-v) == area) {
				answer[0] = range-v;
				answer[1] = v;
				break;
			}
		}
        return answer;
    }
}