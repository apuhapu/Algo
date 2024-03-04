import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int n, currIdx = 1;
	static ArrayList<ArrayList<Integer>> hs = new ArrayList<>();
	static Node[] nodes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		nodes = new Node[n+1];
		StringTokenizer st;
		boolean[] isChild = new boolean[n+1];
		for (int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			int curr = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			if (left != -1) {
				isChild[left] = true;
			}
			if (right != -1) {
				isChild[right] = true;
			}
			nodes[curr] = new Node(left, right);
		}
		int root = 0;
		for (int i=1; i<n+1; i++) {
			if (!isChild[i]) {
				root = i;
				break;
			}
		}
		
		hs.add(new ArrayList<>()); // 0번 기본 추가
		sortCol(root,1);
		int findLevel = 1;
		int maxD = 1;
		for (int level=2;level<hs.size();level++) {
			ArrayList<Integer> sameLevelNodes = hs.get(level);
			int frontIdx = sameLevelNodes.get(0);
			int backIdx = sameLevelNodes.get(sameLevelNodes.size()-1);
			int currD = nodes[backIdx].col - nodes[frontIdx].col + 1;
			if (maxD<currD) {
				findLevel = level;
				maxD = currD;
			}
		}
		System.out.println(findLevel + " " + maxD);
	}
	
	private static void sortCol(int nodeIdx, int h) {
		Node curr = nodes[nodeIdx];
		if (hs.size()==h) {
			hs.add(new ArrayList<>());
		}
		hs.get(h).add(nodeIdx);
		if (curr.left!=-1) {
			sortCol(curr.left,h+1);
		}
		curr.col = currIdx++;
		if (curr.right!=-1) {
			sortCol(curr.right,h+1);
		}
	}

	static class Node {
		int left;
		int right;
		int col;
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
}