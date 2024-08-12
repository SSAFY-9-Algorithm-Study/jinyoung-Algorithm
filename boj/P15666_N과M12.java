package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P15666_N과M12 {

	static int N, M;
	static int[] numbers, arr;
	static Set<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numbers = new int[N];
		arr = new int[M];
		set = new LinkedHashSet<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);

		dfs(0, 0);

		StringBuilder sb = new StringBuilder();
		for (String str : set) {
			sb.append(str).append("\n");
		}

		System.out.println(sb);
	}

	private static void dfs(int depth, int start) {

		if (depth == M) {

			String str = "";
			for (int i = 0; i < M; i++) {
				str += arr[i] + " ";
			}

			set.add(str);
			return;
		}

		for (int i = start; i < N; i++) {
			arr[depth] = numbers[i];
			dfs(depth + 1, i);
		}

	}
}
