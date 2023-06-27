package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p3109 {
	static int R, C, result;
	static char[][] map;
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			if (dfs(i, 0)) {
				result += 1;
			}
		}
		System.out.println(result);
	}

	static boolean dfs(int x, int y) {
		map[x][y] = 'x';
		if (y == C - 1) {
			return true;
		}
		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
				continue;
			}
			if (map[nx][ny] == 'x') {
				continue;
			}

			if (dfs(nx, ny)) {
				return true;
			}
		}
		return false;
	}
}
