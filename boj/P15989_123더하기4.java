package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P15989_123더하기4 {

	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		dp = new int[10001][4];
		dp[1][1] = 1;
		dp[2][1] = 1; dp[2][2] = 1;
		dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;

		for (int i = 0; i < T; i++) {
			int input = Integer.parseInt(br.readLine());
			System.out.println(dp(input));
		}
	}

	private static int dp(int input) {

		for (int i = 4; i <= input; i++) {
			dp[i][1] = dp[i - 1][1];
			dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
			dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
		}
		
		return dp[input][1] + dp[input][2] + dp[input][3];
	}
}
