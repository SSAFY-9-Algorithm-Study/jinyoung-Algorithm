package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17070_파이프옮기기1 {

	static int N, result;
	static int[][] map;
	static boolean[][] wallMap, visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		wallMap = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) {
					wallMap[i][j] = true;
					map[i][j] = 0;
				}
			}
		}

		// 가로 : 1, 대각선 : 2, 세로 : 3

		map[0][0] = 1;
		map[0][1] = 1;

		if (checkHorizontal(0, 1)) {
			dfs(0, 2, 1);
		}

		if (checkDiagonal(0, 1)) {
			dfs(1, 2, 2);
		}

		System.out.println(result);
	}

	private static void dfs(int x, int y, int shape) {

		map[x][y] = shape;

		if (x == N - 1 && y == N - 1) {
			result++;
			return;
		}

		if (checkHorizontal(x, y)) {
			map[x][y + 1] = 1;
			dfs(x, y + 1, 1);
			map[x][y + 1] = 0;
		}

		if (checkVertical(x, y)) {
			map[x + 1][y] = 3;
			dfs(x + 1, y, 3);
			map[x + 1][y] = 0;
		}

		if (checkDiagonal(x, y)) {
			map[x + 1][y + 1] = 2;
			dfs(x + 1, y + 1, 2);
			map[x + 1][y + 1] = 0;
		}

		map[x][y] = 0;
	}

	private static boolean checkDiagonal(int x, int y) {

		if (y + 1 >= N || x + 1 >= N) {
			return false;
		}

		if (wallMap[x][y + 1] || wallMap[x + 1][y] || wallMap[x + 1][y + 1]) {
			return false;
		}

		return true;
	}

	private static boolean checkVertical(int x, int y) {

		if (map[x][y] == 1) {
			return false;
		}

		if (x + 1 >= N) {
			return false;
		}

		if (wallMap[x + 1][y]) {
			return false;
		}

		return true;
	}

	private static boolean checkHorizontal(int x, int y) {

		if (map[x][y] == 3) {
			return false;
		}

		if (y + 1 >= N) {
			return false;
		}

		if (wallMap[x][y + 1]) {
			return false;
		}

		return true;
	}
}
