package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1018_체스판다시칠하기 {

	static final int MAP_LENGTH = 8;
	static int N, M;
	static boolean[][] initMap, map, resultMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		initMap = new boolean[N][M];
		map = new boolean[N][M];
		resultMap = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 0; j < M; j++) {
				if (input.charAt(j) == 'W') {
					initMap[i][j] = true;
				}
			}
		}

		System.out.println(countResult());
	}

	private static int countResult() {

		int min = N * M;

		for (int i = 0; i <= N - MAP_LENGTH; i++) {
			for (int j = 0; j <= M - MAP_LENGTH; j++) {

				// 맨 왼쪽 위 칸이 true인 경우
				min = Math.min(min, solve(i, j, true));
				// 맨 왼쪽 위 칸이 false인 경우
				min = Math.min(min, solve(i, j, false));
			}
		}

		return min;
	}

	private static int solve(int startX, int startY, boolean initFlag) {

		initMap();

		boolean flag = map[startX][startY];

		if (flag != initFlag) {
			map[startX][startY] = initFlag;
			resultMap[startX][startY] = true;
			flag = initFlag;
		}

		for (int i = 0; i < MAP_LENGTH; i++) {

			if (i != 0 && flag == map[i + startX][startY]) {
				map[i + startX][startY] = !map[i + startX][startY];

				resultMap[i + startX][startY] = true;
			}

			flag = map[i + startX][startY];

			for (int j = 1; j < MAP_LENGTH; j++) {

				if (map[i + startX][j + startY - 1] == map[i + startX][j + startY]) {
					map[i + startX][j + startY] = !map[i + startX][j + startY];

					resultMap[i + startX][j + startY] = true;
				}
			}
		}

		// 갯수 세기
		int sum = 0;
		for (int i = 0; i < MAP_LENGTH; i++) {
			for (int j = 0; j < MAP_LENGTH; j++) {
				if (resultMap[i + startX][j + startY]) {
					sum++;
				}
			}
		}

		return sum;
	}

	private static void initMap() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				resultMap[i][j] = false;
				map[i][j] = initMap[i][j];
			}
		}

	}
}
