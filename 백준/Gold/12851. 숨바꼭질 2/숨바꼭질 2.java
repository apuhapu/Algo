import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		if (k <= n) {
			System.out.println(n-k+"\n"+1);
			return;
		}
		
		int[] time = new int[100001];
		boolean findMin = false;
		int numOfWays = 0;
		
		Queue<Node> que = new ArrayDeque<>();
		que.add(new Node(n, 0));
		while (!que.isEmpty()) {
			Node node = que.poll();
			if (findMin) {
				if (node.order >= time[k]) {
					break;
				}
			}
			
			// -1
			int back = node.location-1;
			if (back == k) {
				numOfWays++;
				if (!findMin) {
					findMin = true;
					time[back] = node.order+1;
					continue;
				}
			} else if (back>=0 && back !=n) {
				if (time[back] == 0) {
					time[back] = node.order+1;
					que.add(new Node(back, node.order+1));
				} else if (time[back] == node.order+1) {
					que.add(new Node(back, node.order+1));
				}	
			}
			
			// +1
			int front = node.location+1;
			if (front == k) {
				numOfWays++;
				if (!findMin) {
					findMin = true;
					time[front] = node.order+1;
				}
			} else if (front <= 100_000 && front != n) {
				if (time[front] == 0) {
					time[front] = node.order+1;
					que.add(new Node(front, node.order+1));
				} else if (time[front] == node.order+1) {
					que.add(new Node(front, node.order+1));
				}	
			}
			
			// *2
			int jump = 2*node.location;
			if (jump == k) {
				numOfWays++;
				if (!findMin) {
					findMin = true;
					time[jump] = node.order+1;
				}
			} else if (jump <= 100_000 && jump != n) {
				if (time[jump] == 0) {
					time[jump] = node.order+1;
					que.add(new Node(jump, node.order+1));
				} else if (time[jump] == node.order+1) {
					que.add(new Node(jump, node.order+1));
				}	
			}
		}
		System.out.println(time[k]+"\n"+numOfWays);
	}
	
	static class Node {
		int location;
		int order;
		public Node(int location, int order) {
			this.location = location;
			this.order = order;
		}
	}
	
	private static int naturalMin(int a, int b) {
		if (a == 0) {
			return b;
		}
		if (b == 0) {
			return a;
		}
		return Math.min(a, b);
	}
}