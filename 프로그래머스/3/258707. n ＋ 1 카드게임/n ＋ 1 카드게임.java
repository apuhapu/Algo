class Solution {
    
    static int n;
	static boolean[] visited, hand;
    
    public int solution(int coin, int[] cards) {
        n = cards.length;
		visited = new boolean[n];
		hand = new boolean[n+1];
		for (int i=0; i<n/3; i++) {
			hand[cards[i]] = true;
			visited[i] = true;
		}
        return play(coin, cards);
    }
    
    private int play(int coin, int[] cards) {
		int round = 0;
		int bdd = n/3;
		label:
		while (round<=n/3) {
			round++;
            if (round == n/3+1) {
				break;
			}
			bdd+=2;
			// 주어진 핸드에서 처리
			for (int num=1; num<=n/2; num++) {
				if (hand[num]&&hand[n+1-num]) {
					hand[num] = false;
					hand[n+1-num] = false;
					continue label;
				}
			}
			// 1개만 사서 처리
			if (coin>0) {
				for (int idx=n/3; idx<bdd; idx++) {
					int card = cards[idx];
					if (hand[n+1-card]) { // 해당 카드에 짝이 패에 있다면
						coin--;
						hand[n+1-card] = false;
						continue label;
					}
				}
			}
			// 2개를 사서 처리
			if (coin>1) {
				for (int s=n/3; s<bdd-1; s++) {
					int card1 = cards[s];
					if (visited[s]) {
						continue;
					}
					for (int e=s+1; e<bdd; e++) {
						int card2 = cards[e];
						if (visited[e]) {
							continue;
						}
						if (card1+card2 == n+1) {
							coin -= 2;
							visited[s] = true;
							visited[e] = true;
							continue label;
						}
					}
				}
			}
			break;
		}
		
		return round;
	}
}