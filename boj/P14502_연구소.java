package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class P14502_연구소 {

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N, M, max;
	static int[][] map, newMap;
	static boolean[][] visitedWall, visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static final int wallCount = 3;

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

		visitedWall = new boolean[N][M];

		dfs(0, 0);
		System.out.println(max);
	}

	private static void dfs(int depth, int start) {

		if (depth == wallCount) {
			
			spreadVirus();
			int safetyZone = checkSafetyZone();
			
			max = Math.max(max, safetyZone);

			return;
		}

		for (int i = start; i < N * M; i++) {

			int r = i / M;
			int c = i % M;

			if (map[r][c] == 0) {
				map[r][c] = 1;
				dfs(depth + 1, i + 1);
				map[r][c] = 0;
			}

		}

	}

	private static int checkSafetyZone() {

		int sum = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (newMap[i][j] == 0) {
					sum++;
				}
			}
		}

		return sum;
	}

	private static void spreadVirus() {

		newMap = new int[N][M];
		visited = new boolean[N][M];


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (!visited[i][j] && newMap[i][j] == 2) {
					bfs(i, j);
				}

			}
		}

	}

	private static void bfs(int x, int y) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}

				if (visited[nx][ny] || newMap[nx][ny] == 1) {
					continue;
				}

				newMap[nx][ny] = 2;
				visited[nx][ny] = true;
				queue.offer(new Point(nx, ny));
			}
		}
		
	}
}
