package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1239_차트 {

	static int N, max;
	static int[] chart, arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		chart = new int[N];
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			chart[i] = Integer.parseInt(st.nextToken());
		}

		visited = new boolean[N];
		dfs(0);
		System.out.println(max);
	}

	private static void dfs(int depth) {

		if (depth == N) {
			findCntLine();
			return;
		}

		for (int i = 0; i < N; i++) {

			if (!visited[i]) {
				visited[i] = true;
				arr[depth] = chart[i];
				dfs(depth + 1);
				visited[i] = false;
			}
		}

	}

	private static void findCntLine() {

		int cnt = 0;
		int startSum = 0;
		for (int i = 0; i < arr.length - 1; i++) {

			if (startSum >= 50) {
				break;
			}

			startSum += arr[i];

			if (arr[i] == 50) {
				cnt++;

				continue;
			}

			int sum = arr[i];

			for (int j = i + 1; j < arr.length; j++) {
				sum += arr[j];

				if (sum == 50) {
					cnt++;

					continue;
				}
			}

		}

		max = Math.max(max, cnt);
	}
}
