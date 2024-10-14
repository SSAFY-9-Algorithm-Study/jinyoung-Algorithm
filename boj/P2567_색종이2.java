package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2567_색종이2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[100][100];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pointX = Integer.parseInt(st.nextToken());
			int pointY = Integer.parseInt(st.nextToken());

			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 10; y++) {
					map[pointX + x][pointY + y] = true;
				}
			}
		}

		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };
		int sum = 0;

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j]) {
					for (int k = 0; k < dx.length; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						if (nx < 0 || ny < 0 || nx >= 100 || ny >= 100) {
							sum++;
							continue;
						}

						if (map[nx][ny] == false) {
							sum++;
						}
					}
				}
			}
		}

		System.out.println(sum);
	}
}
