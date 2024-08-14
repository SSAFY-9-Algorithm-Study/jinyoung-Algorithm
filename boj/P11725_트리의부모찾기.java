package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P11725_트리의부모찾기 {

	static int N;
	static List<Integer>[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			tree[start].add(end);
			tree[end].add(start);
		}

		bfs(1);
		
	}

	private static void bfs(int startNode) {
		Queue<Integer> queue = new ArrayDeque<>();
		int[] visited = new int[N + 1];

		queue.offer(startNode);
		visited[startNode] = 1;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (int nextNode : tree[curr]) {
				if (visited[nextNode] == 0) {
					queue.offer(nextNode);
					visited[nextNode] = curr;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();

		for (int i = 2; i <= N; i++) {
			sb.append(visited[i]).append("\n");
		}
		System.out.println(sb);
	}

}
