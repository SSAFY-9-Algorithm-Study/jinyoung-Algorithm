package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P9466_텀프로젝트 {

	static int n, result;
	static int[] students;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			students = new int[n + 1];
			visited = new boolean[n + 1];
			result = n;

			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= n; i++) {
				students[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= n; i++) {

				if (!visited[i]) {
					visited[i] = true;

					Map<Integer, Integer> map = new HashMap<>();
					map.put(i, 1);

					dfs(i, 1, map);
				}

			}

			System.out.println(result);
		}
	}

	private static void dfs(int v, int depth, Map<Integer, Integer> map) {

		// 자기 자신을 선택한 경우
		if (v == students[v]) {
			result--;
			return;
		}

		// 싸이클 내에 있는 경우
		if (map.containsKey(students[v])) {
			result -= (depth + 1) - map.get(students[v]);
			return;
		}

		if (!visited[students[v]]) {
			visited[students[v]] = true;
			map.put(students[v], depth + 1);
			dfs(students[v], depth + 1, map);
		}

	}
}
