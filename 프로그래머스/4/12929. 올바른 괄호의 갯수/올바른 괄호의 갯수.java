class Solution {
    
    static int answer = 0;
    
    public int solution(int n) {
        move(0,0,n);
        return answer;
    }
    
    private static void move(int up, int down, int len) {
		if (up-down<0) { // 음수 경우 제외
			return;
		}
		if (down == len) {
			answer++;
			return;
		}
		if (up > len) {
			return;
		}
		move(up+1,down,len);
		move(up,down+1,len);
	}
}