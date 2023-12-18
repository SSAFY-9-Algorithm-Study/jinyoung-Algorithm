package week20;

import java.util.ArrayList;

class Solution {

	public static void main(String[] args) {
		int[] info = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
				{ 4, 6 }, { 8, 9 } };
		Solution solution = new Solution();
		solution.solution(info, edges);
	}

	static ArrayList<ArrayList<Integer>> list;
	static int[] newInfo;
	static int result;

	public int solution(int[] info, int[][] edges) {
		int answer = 0;
		newInfo = info;
		list = new ArrayList<>();
		for (int i = 0; i <= info.length; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < info.length - 1; i++) {
			int start = edges[i][0];
			int end = edges[i][1];
			list.get(start).add(end);
			list.get(end).add(start);
		}

		dfs(0, 0, 0, new boolean[edges.length + 1]);
		return result;
	}

	static void dfs(int now, int sheep, int wolf, boolean[] visited) {
		if (newInfo[now] == 0) {
			sheep++;
		} else {
			wolf++;
		}

		if (wolf >= sheep) {
			return;
		}

		result = Math.max(sheep, result);

		boolean[] newVisited = visited.clone();
		newVisited[now] = true;
		
		for (int i = 0; i < newInfo.length; i++) {
			if (newVisited[i] == true) {
				for (int j = 0; j < list.get(i).size(); j++) {
					int next = list.get(i).get(j);
					if (!newVisited[next]) {
						dfs(next, sheep, wolf, newVisited);
					}
				}
			}
		}
	}
}
