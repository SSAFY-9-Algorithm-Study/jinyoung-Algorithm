package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2293 {
	
	static int n, k;
	static int[] coins;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coins = new int[n];
		
		for (int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(dp());
	}
	
	static int dp() {
		int[] dp = new int[k + 1];

		for (int i = 0; i < n; i++) {
			
			for (int j = coins[i]; j <= k; j++) {
				
				if (j == coins[i]) {
					dp[j]++;
				}
				
				dp[j] += dp[j - coins[i]];
			}
//			System.out.println(Arrays.toString(dp));
		}
		
		return dp[k];
	}
}
