package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P17404_RGB거리2 {

	static final int RGB_LENGTH = 3;
	static final int MAX_VALUE = 1000 * 1000;
	static int N;
	static int[][] rgbCosts;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		rgbCosts = new int[N][RGB_LENGTH];
		dp = new int[N][RGB_LENGTH];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			rgbCosts[i][0] = Integer.parseInt(st.nextToken());
			rgbCosts[i][1] = Integer.parseInt(st.nextToken());
			rgbCosts[i][2] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(dp());
	}

	private static int dp() {
		
		int answer = MAX_VALUE;
		
		for (int i = 0; i < RGB_LENGTH; i++) {
			
			// 초기값 설정 
			for (int j = 0; j < RGB_LENGTH; j++) {
				
				if (i == j) {
					dp[0][j] = rgbCosts[0][i];
				} else {
					dp[0][j] = MAX_VALUE;
				}
			}
			
			// dp배열 채우기 
			for (int j = 1; j < N; j++) {
				dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + rgbCosts[j][0];
				dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + rgbCosts[j][1];
				dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + rgbCosts[j][2];
			}
			
			// 배열 마지막값 비교 (1번 집의 색과 N번 집의 색이 다른 지 확인) 
			for (int j = 0; j < RGB_LENGTH; j++) {
			
				if (i != j) {
					answer = Math.min(answer, dp[N - 1][j]);
				}
			}
			
		}
		
		return answer;
	}
}
