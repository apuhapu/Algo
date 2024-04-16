import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int idx;
		long x;
		long y;
		long r;
		Node pare;
		Queue<Node> children;
		public Node(int idx, long x, long y, long r) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.r = r;
			children = new ArrayDeque<>();
		}
		@Override
		public int compareTo(Node o) {
			return (int) (o.r-this.r);
		}
	}
	
	static Node[] nodes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		nodes = new Node[n+1];
		Node[] sort = new Node[n+1];
		nodes[0] = new Node(0,0,0,2_000_000);
		sort[0] = nodes[0];
		StringTokenizer st;
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			long r = Long.parseLong(st.nextToken());
			nodes[i] = new Node(i, x, y, r);
			sort[i] = nodes[i];
		}
		Arrays.sort(sort);
		for (int i=1; i<=n; i++) {
			makeTree(sort[i], 0);
		}
		
		// find d
		boolean[] visited = new boolean[n+1];
		Queue<Integer> que = new ArrayDeque<>();
		que.add(0);
		visited[0] = true;
		int deepIdx = 0;
		while (!que.isEmpty()) {
			int curr = que.poll();
			for (Node next : nodes[curr].children) {
				visited[next.idx] = true;
				que.add(next.idx);
				deepIdx = next.idx;
			}
		}
		
		que.add(deepIdx);
		int[] disCnter = new int[n+1];
		disCnter[deepIdx] = 1;
		int output = 1;
		while (!que.isEmpty()) {
			int curr = que.poll();
			if (nodes[curr].pare != null && disCnter[nodes[curr].pare.idx] == 0) {
				disCnter[nodes[curr].pare.idx] = disCnter[curr]+1;
				que.add(nodes[curr].pare.idx);
				output = disCnter[nodes[curr].pare.idx];
			}
			for (Node next : nodes[curr].children) {
				if (disCnter[next.idx]!=0) {
					continue;
				}
				disCnter[next.idx] = disCnter[curr]+1;
				output = disCnter[next.idx];
				que.add(next.idx);
			}
		}
		System.out.println(output-1);
	}
	
	private static void makeTree(Node node, int pareIdx) {
		if (node.pare != null) return;
		Node curr = nodes[pareIdx];
		if (curr.children.isEmpty()) { // 리프 노드일 시
			curr.children.add(node);
			node.pare = curr;
			return;
		}
		
		for (Node ch : curr.children) {
			if (!checkDistinct(node, ch)) {
				makeTree(node, ch.idx);
			}
			if (node.pare != null) return;
		}
		// 자식 노드과 모두 관계가 없을 때
		if (node.pare == null) {
			curr.children.add(node);
			node.pare = curr;
			return;
		}
	}

	private static boolean checkDistinct(Node n1, Node n2) {
		long dx = n1.x-n2.x;
		long dy = n1.y-n2.y;
		long dsqure = dx*dx+dy*dy;
		long rsqure = (n1.r+n2.r)*(n1.r+n2.r);
		if (dsqure > rsqure) {
			return true;
		}
		return false;
	}
}