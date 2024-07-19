package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1697_숨바꼭질 {

	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[100_001];

		queue.offer(N);
		visited[N] = true;

		int cnt = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int s = 0; s < size; s++) {
				int curr = queue.poll();

				if (curr == K) {
					return cnt;
				}

				int next = curr - 1;
				if (next >= 0 && !visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}

				next = curr + 1;
				if (next <= 100_000 && !visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}

				next = curr * 2;
				if (next <= 100_000 && !visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}

			cnt++;
		}

		return 0;
	}
}
