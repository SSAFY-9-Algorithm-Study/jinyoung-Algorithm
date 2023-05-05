package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p12896 {
	static int N, max, farNode;
	static ArrayList<ArrayList<Integer>> tree;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList());
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			tree.get(start).add(end);
			tree.get(end).add(start);
		}
		visited = new boolean[N + 1];
		max = Integer.MIN_VALUE;
		visited[1] = true;
		dfs(1, 0);

		max = Integer.MIN_VALUE;
		visited = new boolean[N + 1];
		visited[farNode] = true;
		dfs(farNode, 0);
		System.out.println((max + 1) / 2);
	}

	static void dfs(int start, int cnt) {
		if (cnt > max) {
			max = cnt;
			farNode = start;
		}
		for (int i = 0; i < tree.get(start).size(); i++) {
			int next = tree.get(start).get(i);
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			dfs(next, cnt + 1);

		}
	}
}
