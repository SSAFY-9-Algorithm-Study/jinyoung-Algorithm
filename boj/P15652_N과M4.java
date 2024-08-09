package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15652_N과M4 {

	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];

		dfs(0, 1);
	}

	private static void dfs(int depth, int num) {

		if (depth == M) {

			for (int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}

			System.out.println();
			return;
		}

		for (int i = num; i <= N; i++) {
			arr[depth] = i;
			dfs(depth + 1, i);
		}

	}
}
