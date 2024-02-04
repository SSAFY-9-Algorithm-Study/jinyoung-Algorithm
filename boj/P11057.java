package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P11057 {
	
	static int N;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N][11];
		
		System.out.println(calculateAscNumber());
	}
	
	static int calculateAscNumber() {
		
		for (int i = 1; i <= 10; i++) {
			dp[0][i] = i;
		}
		
		for (int i = 1; i < N; i++) {
			
			for (int j = 1; j <= 10; j++) {
				dp[i][j] = (dp[i][j - 1] % 10007) + (dp[i - 1][j] % 10007);
			}
		}
		
		return dp[N - 1][10] % 10007;
	}
}
