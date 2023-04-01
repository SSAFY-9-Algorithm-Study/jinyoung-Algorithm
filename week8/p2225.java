package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2225 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);

		long[][] dp = new long[K + 1][N + 1];

		// k가 2일 때는 전부 1로 채우기
		for (int i = 0; i <= N; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= K; i++) {
			// 맨 뒤는 무조건 1
			dp[i][N] = 1;
			// 뒤에서부터 이전 dp값의 각 자리의 합계를 dp에 넣어줌
			for (int j = N - 1; j >= 0; j--) {
				dp[i][j] = (dp[i][j + 1] + dp[i - 1][j]) % 1_000_000_000;
			}
		}

		if (K == 1) {
			System.out.println(1);
		} else {
			long sum = 0;
			for (int i = 0; i <= N; i++) {
				sum += dp[K - 1][i] % 1_000_000_000;
			}
			System.out.println(sum % 1_000_000_000);
		}
	}
}