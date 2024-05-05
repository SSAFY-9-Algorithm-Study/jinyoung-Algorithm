package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15286_퇴사2 {
	
	static int N;
	static int[] arrT, arrP, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arrT = new int[N + 1];
		arrP = new int[N + 1];
		dp = new int[N + 2];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arrT[i] = Integer.parseInt(st.nextToken());
			arrP[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(dp());
		
	}
	private static int dp() {
		
		for (int i = 1; i <= N; i++) {
			
			if (i + arrT[i] <= N + 1) {
				dp[i + arrT[i]] = Math.max(dp[i + arrT[i]], dp[i] + arrP[i]);
			}
			
			dp[i + 1] = Math.max(dp[i + 1], dp[i]);
		}
		
//		System.out.println(Arrays.toString(dp));
		
		return dp[N + 1];
	}
}
