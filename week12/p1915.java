package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1915 {
	static int N, M, max;
	static int[][] arr;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				arr[i][j] = line.charAt(j - 1) - '0';
			}
		}

		dp = new int[N + 1][M + 1];
		
		setSquare();

		System.out.println(max * max);

	}

	static void setSquare() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (arr[i][j] == 0) {
					dp[i][j] = 0;
					continue;
				}
				int[] around = new int[] { dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]};
				int min = Integer.MAX_VALUE;
				for (int a : around) {
					min = Math.min(min, a);
				}
				dp[i][j] = min + 1;
				max = Math.max(max, dp[i][j]);
			}
		}
	}
}
