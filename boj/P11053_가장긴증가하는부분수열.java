package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11053_가장긴증가하는부분수열 {

	static int N;
	static int[] numbers, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		numbers = new int[N];
		dp = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}

		System.out.println(dp());
	}

	private static int dp() {

		int max = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {

				if (numbers[j] < numbers[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		return max;
	}
}
