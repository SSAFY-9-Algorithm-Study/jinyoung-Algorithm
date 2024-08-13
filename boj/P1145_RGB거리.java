package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1145_RGB거리 {

	static int N;
	static int[][] costs, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		costs = new int[N][3];
		dp = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			costs[i][0] = Integer.parseInt(st.nextToken());
			costs[i][1] = Integer.parseInt(st.nextToken());
			costs[i][2] = Integer.parseInt(st.nextToken());
		}

		System.out.println(dp());
	}

	private static int dp() {

		for (int i = 0; i < 3; i++) {
			dp[0][i] = costs[0][i];
		}

		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
		}

		int result = dp[N - 1][0];

		for (int i = 1; i < 3; i++) {
			result = Math.min(result, dp[N - 1][i]);
		}

		return result;
	}
}
