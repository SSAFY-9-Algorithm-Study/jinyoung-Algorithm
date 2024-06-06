package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1113_수영장만들기 {

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static int[][] pool;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pool = new int[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 0; j < M; j++) {
				pool[i][j] = input.charAt(j) - '0';
			}
		}
		
		System.out.println(makePool());

	}

	private static int makePool() {

		int answer = 0;
		
		for (int p = 2; p <= 9; p++) {
			visited = new boolean[N][M];

			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {

					if (pool[i][j] < p && !visited[i][j]) {
						answer += bfs(i, j, p);
					}
				}
			}
		}
		
		return answer;
	}

	private static int bfs(int x, int y, int p) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;
		pool[x][y]++;

		int size = 1;
		boolean flag = true;
		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					flag = false;
					continue;
				}
				
				if (visited[nx][ny] || pool[nx][ny] >= p) {
					continue;
				}
				
				size++;
				queue.offer(new Point(nx, ny));
				visited[nx][ny] = true;
				pool[nx][ny]++;
			}
		}
		
		if (!flag) {
			size = 0;
		}

		return size;
	}
}
