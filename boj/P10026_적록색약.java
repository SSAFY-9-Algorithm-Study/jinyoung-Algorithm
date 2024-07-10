package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class P10026_적록색약 {

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static final int R_NUMBER = 1;
	static final int G_NUMBER = 2;
	static final int B_NUMBER = 3;
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 0; j < N; j++) {
				int curr = input.charAt(j);

				if (curr == 'R') {
					map[i][j] = R_NUMBER;
				} else if (curr == 'G') {
					map[i][j] = G_NUMBER;
				} else {
					map[i][j] = B_NUMBER;
				}
			}
		}
		
		int result1 = solve(); // 적록색약 X
		int result2 = solveRedEqualsGreen(); // 적록색약 O
		
		System.out.println(result1 + " " + result2);
	}

	private static int solve() {

		visited = new boolean[N][N];
		int sum = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == false) {
					bfs(i, j);
					sum++;
				}
			}
		}
		
		return sum;
	}
	
	private static int solveRedEqualsGreen() {
		
		visited = new boolean[N][N];
		int sum = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == false) {
					bfsRedEqualsGreen(i, j);
					sum++;
				}
			}
		}
		
		return sum;

	}

	private static void bfs(int x, int y) {

		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;

		int currNumber = map[x][y];

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}

				if (visited[nx][ny]) {
					continue;
				}

				if (map[nx][ny] == currNumber) {
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}

	}
	
	private static void bfsRedEqualsGreen(int x, int y) {
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;

		int currNumber = map[x][y];

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}

				if (visited[nx][ny]) {
					continue;
				}

				if (map[nx][ny] == currNumber) {
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
					continue;
				}
				
				if (currNumber == R_NUMBER && map[nx][ny] == G_NUMBER) {
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
					continue;
				}
				
				if (currNumber == G_NUMBER && map[nx][ny] == R_NUMBER) {
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
					continue;
				}
			}
		}

		
	}

}
