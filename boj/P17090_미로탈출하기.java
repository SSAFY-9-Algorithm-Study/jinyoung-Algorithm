package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17090_미로탈출하기 {

	static int N, M;
	static char[][] map;
	static int[][] isEscape;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		isEscape = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (dfs(i, j)) {
					result++;
				}

			}
		}

		System.out.println(result);

	}

	private static boolean dfs(int i, int j) {

		if (i < 0 || i >= N || j < 0 || j >= M) {
			return true;
		}

		if (isEscape[i][j] == 1) {
			return true;
		}

		if (isEscape[i][j] == 2) {
			return false;
		}

		if (visited[i][j]) {
			return false;
		}

		visited[i][j] = true;

		boolean flag = false;

		switch (map[i][j]) {

		case 'U':
			flag = dfs(i - 1, j);
			break;
		case 'R':
			flag = dfs(i, j + 1);
			break;
		case 'D':
			flag = dfs(i + 1, j);
			break;
		case 'L':
			flag = dfs(i, j - 1);
			break;

		default:
			break;
		}

		if (flag) {
			isEscape[i][j] = 1;
		} else {
			isEscape[i][j] = 2;
		}

		return flag;
	}
}
