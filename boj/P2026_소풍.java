package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P2026_소풍 {

	static int K, N, F;
	static boolean[][] relationships;
	static int[] cntFriends;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());

		relationships = new boolean[N + 1][N + 1];
		cntFriends = new int[N + 1];

		for (int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			relationships[start][end] = true;
			relationships[end][start] = true;
			cntFriends[start]++;
			cntFriends[end]++;
		}

		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {

			if (cntFriends[i] < K - 1) {
				continue;
			}

			visited[i] = true;
			dfs(i, 1);
			visited[i] = false;
		}

		System.out.println(-1);
	}

	private static void dfs(int idx, int depth) {

		if (cntFriends[idx] < K - 1) {
			return;
		}

		if (depth == K) {
			printResult();
			System.exit(0);
		}

		for (int i = idx + 1; i <= N; i++) {

			if (relationships[idx][i] && isFriends(i)) {
				visited[i] = true;
				dfs(i, depth + 1);
				visited[i] = false;
			}
		}

	}

	private static void printResult() {

		for (int i = 1; i <= N; i++) {
			if (visited[i]) {
				System.out.println(i);
			}
		}

	}

	private static boolean isFriends(int idx) {

		for (int i = 1; i <= N; i++) {
			if (visited[i] && !relationships[idx][i]) {
				return false;
			}
		}

		return true;
	}

}
