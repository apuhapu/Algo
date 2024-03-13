import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static TreeSet<Problem> wholeTree = new TreeSet<>();
	static HashMap<Integer, TreeSet<Problem>> groupTree = new HashMap<>();
	static HashMap<Integer, Integer> infos = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int problemNumber = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            int group = Integer.parseInt(st.nextToken());
            add(problemNumber, level, group);
        }
        int m = Integer.parseInt(br.readLine());
        for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("add")) {
				int problemNumber = Integer.parseInt(st.nextToken());
	            int level = Integer.parseInt(st.nextToken());
	            int group = Integer.parseInt(st.nextToken());
	            add(problemNumber, level, group);
			} else if (command.equals("recommend")) {
				int group = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					sb.append(groupTree.get(group).last().id).append("\n");
				} else {
					sb.append(groupTree.get(group).first().id).append("\n");
				}
			} else if (command.equals("recommend2")) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					sb.append(wholeTree.last().id).append("\n");
				} else {
					sb.append(wholeTree.first().id).append("\n");
				}
			} else if (command.equals("recommend3")) {
				int x = Integer.parseInt(st.nextToken());
				int level = Integer.parseInt(st.nextToken());
				if (x == 1) {
					Problem problem = wholeTree.ceiling(new Problem(0,level,0));
					if (problem == null) {
						sb.append(-1).append("\n");
					} else {
						sb.append(problem.id).append("\n");
					}
				} else {
					Problem problem = wholeTree.floor(new Problem(0,level,0));
					if (problem == null) {
						sb.append(-1).append("\n");
					} else {
						sb.append(problem.id).append("\n");
					}
				}
			} else if (command.equals("solved")) {
				int problemNumber = Integer.parseInt(st.nextToken());
				int info = infos.get(problemNumber);
				int level = info/1000;
				int group = info%1000;
				Problem problem = new Problem(problemNumber, level, group);
				wholeTree.remove(problem);
				groupTree.get(group).remove(problem);
				infos.remove(problemNumber);
			}
		}
        System.out.println(sb);
	}
	
	private static void add(int problemNumber, int level, int group) {
		Problem problem = new Problem(problemNumber, level, group);
		wholeTree.add(problem);
		if (groupTree.containsKey(group)) {
			groupTree.get(group).add(problem);
		} else {
			TreeSet<Problem> temp = new TreeSet<>();
			temp.add(problem);
			groupTree.put(group, temp);
		}
		infos.put(problemNumber, level*1000+group);
	}

	static class Problem implements Comparable<Problem> {
		int id;
		int level;
		int group;
		public Problem(int id, int level, int group) {
			this.id = id;
			this.level = level;
			this.group = group;
		}
		@Override
		public int compareTo(Problem o) {
			if (this.level == o.level) {
				return this.id-o.id;
			}
			return this.level-o.level;
		}
	}
}