package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1106_호텔 {

	static final int MAX_C = 1000;
	static int C, N, answer;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		dp = new int[MAX_C + 101];

		for (int i = 0; i <= dp.length - 1; i++) {
			dp[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			updateArr(cost, num);
		}

		findMinMoney();

		answer = Integer.MAX_VALUE;
		for (int i = C; i < dp.length; i++) {
			answer = Math.min(answer, dp[i]);
		}

		System.out.println(answer);
	}

	private static void findMinMoney() {

		for (int i = 1; i <= dp.length - 1; i++) {
			for (int j = i; j <= dp.length - 1 - i; j++) {

				if (dp[i] == Integer.MAX_VALUE || dp[j] == Integer.MAX_VALUE) {
					continue;
				}

				dp[i + j] = Math.min(dp[i + j], dp[i] + dp[j]);
			}
		}

	}

	private static void updateArr(int cost, int num) {

		for (int i = 1; i <= dp.length - 1; i++) {
			int currNum = i * num;

			if (currNum > dp.length - 1) {
				break;
			}

			dp[currNum] = Math.min(dp[currNum], i * cost);
		}

	}
}
