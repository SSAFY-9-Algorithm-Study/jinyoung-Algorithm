package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2169_로봇조종하기 {

	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solve());
	}

	private static int solve() {
		int[][] dp = new int[N][M];
		dp[0][0] = map[0][0];

		for (int i = 1; i < M; i++) {
			dp[0][i] = dp[0][i - 1] + map[0][i];
		}
		
		for (int i = 1; i < N; i++) {
			int[] leftArr = new int[M];
			int[] rightArr = new int[M];
			
			// 왼 -> 오 / 상 -> 하 비교 
			leftArr[0] = dp[i - 1][0] + map[i][0];
			for (int j = 1; j < M; j++) {
				leftArr[j] = Math.max(leftArr[j - 1], dp[i - 1][j]) + map[i][j];
			}
			
			// 오 -> 왼 / 상 -> 하 비교 
			rightArr[M - 1] = dp[i - 1][M - 1] + map[i][M - 1];
			for (int j = M - 2; j >= 0; j--) {
				rightArr[j] = Math.max(rightArr[j + 1], dp[i - 1][j]) + map[i][j];
			}
			
			// 위에서 비교한 배열 중 큰 값을 dp 배열에 저장 
			for (int j = 0; j < M; j++) {
				dp[i][j] = Math.max(leftArr[j], rightArr[j]);
			}
		}

		return dp[N - 1][M - 1];
	}
}