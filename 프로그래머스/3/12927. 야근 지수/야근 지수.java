import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int w : works) {
			pq.add(w);
		}
		for (int i=0; i<n; i++) {
			int max = pq.poll();
			if (max == 0) {
				return 0L;
			}
			pq.add(--max);
		}
		long answer = 0;
		for (Integer w : pq.toArray(new Integer[works.length])) {
			answer += w*w;
		}
        return answer;
    }
}