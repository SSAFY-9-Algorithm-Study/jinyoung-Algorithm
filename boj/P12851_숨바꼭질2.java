package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P12851_숨바꼭질2 {

	static class Visit {
		int depth, cnt;

		public Visit(int depth, int cnt) {
			this.depth = depth;
			this.cnt = cnt;
		}
	}

	static int N, K, result, resultSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs();
		System.out.println(result);
		System.out.println(resultSum);
	}

	private static void bfs() {
		Queue<Integer> pq = new LinkedList<>();
		Visit[] visited = new Visit[100_001]; // depth와 방법 수를 저장

		int depth = 0;

		pq.offer(N);
		visited[N] = new Visit(depth, 1);

		while (!pq.isEmpty()) {

			int size = pq.size();
			boolean flag = false;
			for (int i = 0; i < size; i++) {
				int curr = pq.poll();

				if (curr == K) {
					result = depth;
					resultSum = visited[curr].cnt;
					return;
				}

				if (curr - 1 >= 0 && (visited[curr - 1] == null || visited[curr - 1].depth == depth)) {

					if (visited[curr - 1] == null) {
						visited[curr - 1] = new Visit(depth, 1);
					} else {
						visited[curr - 1].cnt++;
					}

					pq.offer(curr - 1);
				}

				if (curr + 1 <= 100_000 && (visited[curr + 1] == null || visited[curr + 1].depth == depth)) {
					if (visited[curr + 1] == null) {
						visited[curr + 1] = new Visit(depth, 1);
					} else {
						visited[curr + 1].cnt++;
					}

					pq.offer(curr + 1);
				}

				if (curr * 2 <= 100_000 && (visited[curr * 2] == null || visited[curr * 2].depth == depth)) {
					if (visited[curr * 2] == null) {
						visited[curr * 2] = new Visit(depth, 1);
					} else {
						visited[curr * 2].cnt++;
					}

					pq.offer(curr * 2);
				}

			}

			depth++;
		}

	}
}
