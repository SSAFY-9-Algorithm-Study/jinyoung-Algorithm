package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14722 {

	static int N;
	static int[][] map, dp;
	static int[] dx = { 1, 0 };
	static int[] dy = { 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		if (map[1][1] == 0) {
			dp[1][1] = 1;
		} else {
			dp[1][1] = 0;
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 행을 기준으로 비교
				// 우유 먹을 수 있는 경우
				if (map[i][j] == (dp[i][j - 1]) % 3) {
					dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + 1);
				} else { // 우유 먹을 수 없는 경우
					dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
				}
				
				// 열을 기준으로 비교
				// 우유 먹을 수 있는 경우
				if (map[i][j] == (dp[i - 1][j]) % 3) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
				} else { // 우유 먹을 수 없는 경우
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
				}

			}
		}

		System.out.println(dp[N][N]);
	}

}
