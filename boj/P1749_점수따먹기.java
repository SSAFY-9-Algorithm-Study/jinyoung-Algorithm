package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1749_점수따먹기 {

	static int N, M;
	static int[][] map, sumMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		sumMap = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solve());
	}

	private static int solve() {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sumMap[i][j] = sumMap[i - 1][j] + sumMap[i][j - 1] - sumMap[i - 1][j - 1] + map[i][j];
			}
//			System.out.println(Arrays.toString(sumMap[i]));
		}

		int max = Integer.MIN_VALUE;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				for (int k = 0; k < i; k++) {
					for (int l = 0; l < j; l++) {
						max = Math.max(max, sumMap[i][j] - sumMap[k][j] - sumMap[i][l] + sumMap[k][l]);
					}
				}

			}
		}

		return max;
	}
}