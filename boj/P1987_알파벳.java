package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1987_알파벳 {

	static class Point {
		int x, y, cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int R, C, max;
	static char[][] board;
	static boolean[] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][C];
		visited = new boolean['Z' + 1];

		for (int i = 0; i < R; i++) {
			String input = br.readLine();

			for (int j = 0; j < C; j++) {
				board[i][j] = input.charAt(j);
			}
		}

		visited[board[0][0]] = true;
		dfs(0, 0, 1);
		System.out.println(max);
	}

	private static void dfs(int x, int y, int cnt) {

		max = Math.max(max, cnt);

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
				continue;
			}

			if (visited[board[nx][ny]]) {
				continue;
			}

			visited[board[nx][ny]] = true;
			dfs(nx, ny, cnt + 1);
			visited[board[nx][ny]] = false;
		}
	}

}
