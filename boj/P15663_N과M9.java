package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P15663_N과M9 {

	static int N, M;
	static int[] numbers, arr;
	static Set<String> set;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numbers = new int[N];
		arr = new int[M];
		set = new LinkedHashSet<>();
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);
		dfs(0);

		StringBuilder sb = new StringBuilder();
		for (String str : set) {
			sb.append(str).append("\n");
		}

		System.out.println(sb);
	}

	private static void dfs(int depth) {

		if (depth == M) {

			String result = "";
			for (int i = 0; i < M; i++) {
				result += arr[i] + " ";
			}

			set.add(result);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				arr[depth] = numbers[i];
				visited[i] = true;
				dfs(depth + 1);
				visited[i] = false;
			}
		}

	}
}
