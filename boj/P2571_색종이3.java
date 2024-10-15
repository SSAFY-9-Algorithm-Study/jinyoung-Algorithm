package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2571_색종이3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[101][101];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pointX = Integer.parseInt(st.nextToken());
			int pointY = Integer.parseInt(st.nextToken());

			for (int x = 1; x <= 10; x++) {
				for (int y = 1; y <= 10; y++) {
					map[pointX + x][pointY + y] = true;
				}
			}
		}

		// 행 누적합 구하기
		int[][] prefixSum = new int[101][101];

		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				if (map[i][j]) {
					prefixSum[i][j] = prefixSum[i][j - 1] + 1;
				}
			}
		}

		int max = 0;

		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				int w = 100;

				// 현재 행에 대해 아랫쪽으로 내려가면서 사각형 구하기
				for (int k = i; k <= 100; k++) {
					if (prefixSum[k][j] == 0)
						break;

					w = Math.min(w, prefixSum[k][j]); // 사각형 가로길이는 현재 사각형 높이까지 검사한 수 중 min
					max = Math.max(max, w * (k - i + 1));
				}
			}
		}

		System.out.println(max);
	}
}
