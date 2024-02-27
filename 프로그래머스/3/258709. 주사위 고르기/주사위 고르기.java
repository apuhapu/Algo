import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
    
    static List<Integer> bits = new ArrayList<>(), sums;
	static int bestChoice = 0, bestScore = 0;
	static int n;
    
    public int[] solution(int[][] dice) {
        n = dice.length;
		combiDice(n, 0, 0, 0);
		play(dice);
        return convbit2arr();
    }
    
    private static void combiDice(int n, int idx, int len, int bit) {
		if (len == n/2) {
			bits.add(bit);
			return;
		}
		if (idx==n-1) { // 중복 카운팅을 미리 제거 (12를 세면 34가 자동으로 계산됨)
			return;
		}
		combiDice(n, idx+1, len, bit);
		combiDice(n, idx+1, len+1, bit|1<<idx);
	}
    
    private static void play(int[][] dice) {
		for (int a : bits) {
			List<Integer> aD = new ArrayList<>(n/2);
			List<Integer> bD = new ArrayList<>(n/2);
			for (int i=0; i<n; i++) {
				if ((a&1<<i)!=0) {
					aD.add(i);
				} else {
					bD.add(i);
				}
			}
			HashMap<Integer, Integer> sumA = indiPlay(aD, dice);
			HashMap<Integer, Integer> sumB = indiPlay(bD, dice);
			int winA = 0;
			int winB = 0;
			for (int aS : sumA.keySet()) {
				for (int bS : sumB.keySet()) {
					if (aS>bS) { // a승
						winA += sumA.get(aS)*sumB.get(bS);
					} else if (aS<bS) { // b승
						winB += sumA.get(aS)*sumB.get(bS);
					} else { // 무승부
						continue;
					}
				}
			}
			if (winA>winB) {
				if (bestScore < winA) { // 기존 최고 스코어 보다 높은 승률이면
					bestScore = winA;
					bestChoice = a;
				}
				continue;
			} else {
				if (bestScore < winB) { // 기존 최고 스코어 보다 높은 승률이면
					bestScore = winB;
					bestChoice = ~a;
				}
				continue;
			}
		}
	}
    
    private static HashMap<Integer, Integer> indiPlay(List<Integer> aD, int[][] dice) {
		HashMap<Integer, Integer> aSums = new HashMap<>();
		sums = new ArrayList<>();
		calDiceSum(aD, 0, 0, dice);
		for (int sum : sums) {
			if (aSums.get(sum)==null) {
				aSums.put(sum, 1);
			} else {
				aSums.put(sum, aSums.get(sum)+1);
			}
		}
		return aSums;
	}
    
    private static void calDiceSum(List<Integer> aD, int idx, int curr, int[][] dice) {
		if (idx == n/2) {
			sums.add(curr);
			return;
		}
		for (int i=0; i<6; i++) {
			calDiceSum(aD, idx+1, curr+dice[aD.get(idx)][i], dice);
		}
	}
    
    private static int[] convbit2arr() {
		int[] result = new int[n/2];
		int idx = 0;
		for (int i=0; i<n; i++) {
			if ((bestChoice&1<<i)!=0) {
				result[idx++] = i+1;
			}
		}
		return result;
	}
}