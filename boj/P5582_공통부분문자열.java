package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P5582_공통부분문자열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word1 = br.readLine();
		String word2 = br.readLine();

		int[][] dp = new int[word1.length() + 1][word2.length() + 1];

		int result = 0;
		for (int i = 1; i <= word1.length(); i++) {
			for (int j = 1; j <= word2.length(); j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					result = Math.max(result, dp[i][j]);
				}
			}
		}

		System.out.println(result);
	}
}
