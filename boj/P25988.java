package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P25988 {
	
	static long[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		 
		dp = new long[1_000_001];
	    dp[1] = 1;
	    dp[2] = 2;
	    dp[3] = 4;
		
	    int[] input = new int[T];
 	    int max = 0;
 	    
		for (int i = 0; i < T; i++) {
			input[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, input[i]);
		}
		
		dp(max);
		
		for (int i : input) {
			System.out.println(dp[i] % 1_000_000_009);
		}
	}
	
	static void dp(int n) {
		
		for (int i = 4; i <= n; i++) {
			
			for (int j = 1; j <= 3; j++) {
				dp[i] += (dp[i - j] % 1_000_000_009);
			}
		}

	}
}
