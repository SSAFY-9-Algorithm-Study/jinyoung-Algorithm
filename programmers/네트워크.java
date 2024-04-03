package programmers;

import java.util.*;

public class 네트워크 {

	int n, answer;
	int[][] computers;
	Queue<Integer> queue;
	boolean[] visited;

	public int solution(int n, int[][] computers) {

		this.n = n;
		this.computers = computers;

		queue = new ArrayDeque<>();
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				continue;
			}

			bfs(i);
		}

		return answer;
	}

	void bfs(int index) {
		answer++;
		visited[index] = true;

		for (int i = 0; i < n; i++) {
			if (computers[index][i] == 0) {
				continue;
			}

			if (visited[i]) {
				continue;
			}
			queue.offer(i);
			visited[i] = true;
		}

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (int i = 0; i < computers[curr].length; i++) {
				int nextState = computers[curr][i];

				if (visited[i] || nextState == 0) {
					continue;
				}

				if (curr == i) {
					continue;
				}

				queue.offer(i);
				visited[i] = true;
			}
		}

	}

}
