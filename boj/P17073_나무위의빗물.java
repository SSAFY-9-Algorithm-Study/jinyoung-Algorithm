package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P17073_나무위의빗물 {

	static int N, W;
	static double num;
	static List<List<Integer>> tree;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		tree = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());

			tree.get(U).add(V);
			tree.get(V).add(U);
		}

		visited = new boolean[N + 1];

		dfs(1, 0);
		
		System.out.println(W / num);
	}

	private static void dfs(int n, int parentN) {

		if (visited[n]) {
			return;
		}

		visited[n] = true;

		List<Integer> node = tree.get(n);

		int tempNode = node.get(0);
		if (node.size() == 1 && tempNode == parentN) {
			num++;
			return;
		}

		for (int i = 0; i < node.size(); i++) {
			dfs(node.get(i), n);
		}
	}
}
