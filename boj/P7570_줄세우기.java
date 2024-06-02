package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P7570_줄세우기 {

	static int N;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());

		int max = 0;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			dp[num] = dp[num - 1] + 1;
			max = Math.max(max, dp[num]);

		}

		System.out.println(N - max);
	}
}
