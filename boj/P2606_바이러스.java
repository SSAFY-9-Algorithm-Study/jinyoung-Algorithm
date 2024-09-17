package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P2606_바이러스 {

	static int N;
	static List<Integer>[] nodes;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		nodes = new ArrayList[N + 1];
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		}

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			nodes[start].add(end);
			nodes[end].add(start);
		}

		dfs(1);

		int result = 0;
		for (int i = 2; i <= N; i++) {
			if (visited[i]) {
				result++;
			}
		}

		System.out.println(result);
	}

	private static void dfs(int node) {
		visited[node] = true;

		for (int i = 0; i < nodes[node].size(); i++) {
			int next = nodes[node].get(i);
			if (!visited[next]) {
				dfs(next);
			}
		}

	}
}
