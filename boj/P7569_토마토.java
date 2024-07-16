package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7569_토마토 {

	static class Point {
		int h, x, y;

		Point(int h, int x, int y) {
			this.h = h;
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, H;
	static int[][][] tomatoes;
	static Queue<Point> queue;
	static boolean[][][] visited;
	static int[] dx = { 0, 0, -1, 1, 0, 0 };
	static int[] dy = { -1, 1, 0, 0, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		tomatoes = new int[H][N][M];
		queue = new ArrayDeque<>();
		visited = new boolean[H][N][M];
		int grownCnt = 0;
		int wallCnt = 0;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());

				for (int k = 0; k < M; k++) {
					tomatoes[i][j][k] = Integer.parseInt(st.nextToken());

					if (tomatoes[i][j][k] == 1) {
						queue.offer(new Point(i, j, k));
						visited[i][j][k] = true;
						grownCnt++;
					} else if (tomatoes[i][j][k] == -1) {
						wallCnt++;
					}
				}
			}
		}

		if (grownCnt + wallCnt == H * N * M) {
			System.out.println(0);
			System.exit(0);
		}

		int cnt = bfs();

		if (checkTomatoes()) {
			System.out.println(cnt);
		} else {
			System.out.println(-1);
		}

	}

	private static boolean checkTomatoes() {

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (tomatoes[i][j][k] == 0) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private static int bfs() {

		int cnt = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean flag = false;
			
			for (int s = 0; s < size; s++) {
				Point curr = queue.poll();

				for (int i = 0; i < dx.length; i++) {
					int nx = curr.x + dx[i];
					int ny = curr.y + dy[i];
					int nh = curr.h + dh[i];

					if (nx < 0 || ny < 0 || nh < 0 || nx >= N || ny >= M || nh >= H) {
						continue;
					}

					if (visited[nh][nx][ny]) {
						continue;
					}

					if (tomatoes[nh][nx][ny] == 0) {
						tomatoes[nh][nx][ny] = 1;
						visited[nh][nx][ny] = true;
						queue.offer(new Point(nh, nx, ny));
						flag = true;
					}
				}
			}

			if (flag) {
				cnt++;
			}
		}

		return cnt;
	}

}
