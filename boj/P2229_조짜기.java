package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2229_조짜기 {
	
	static int N;
	static int[] scores;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		scores = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(dp());
	}
	
	static int dp() {
		int[] dp = new int[N + 1];
		
		int max = 0;
		for (int i = 1; i <= N; i++) {

			for (int j = i; j >= 1; j--) {
				max = Math.max(max, Math.abs(scores[i] - scores[j]) + dp[j - 1]);
			}
			dp[i] = max;
		}
		
//		System.out.println(Arrays.toString(dp));
		return dp[N];
	}
}
