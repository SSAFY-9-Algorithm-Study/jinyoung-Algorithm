package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P9251 {
	
	static String word1, word2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word1 = br.readLine();
		word2 = br.readLine();
		
		System.out.println(findLCS());
	}
	
	static int findLCS() {
		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
		
		for (int i = 1; i <= word1.length(); i++) {
			
			for (int j = 1; j <= word2.length(); j++) {
				
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}

		}
		
		return dp[word1.length()][word2.length()];
	}
}
