package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p18430 {
	static int N, M, max;
	static int[][] map;
	static int[][] dx = { { 0, 1 }, { -1, 0 }, { -1, 0 }, { 0, 1 } };
	static int[][] dy = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);
		System.out.println(max);
	}

	static void dfs(int cnt, int sum) {
		if (cnt == N * M) {
			max = Math.max(max, sum);
			return;
		}
		int x = cnt / M;
		int y = cnt % M;

		if (!visited[x][y]) {
			for (int i = 0; i < dx.length; i++) {
				boolean flag = true;
				for (int j = 0; j < 2; j++) {
					int nx = x + dx[i][j];
					int ny = y + dy[i][j];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
						flag = false;
						break;
					}
					if (visited[nx][ny]) {
						flag = false;
						break;
					}
				}
				if (flag) {
					int newSum = sum + map[x][y] * 2;
					visited[x][y] = true;
					for (int j = 0; j < 2; j++) {
						int nx = x + dx[i][j];
						int ny = y + dy[i][j];
						newSum += map[nx][ny];
						visited[nx][ny] = true;
					}
					dfs(cnt + 1, newSum);
					visited[x][y] = false;
					for (int j = 0; j < 2; j++) {
						int nx = x + dx[i][j];
						int ny = y + dy[i][j];
						visited[nx][ny] = false;
					}
				}
				
			}
		}
		dfs(cnt + 1, sum);

	}
}
