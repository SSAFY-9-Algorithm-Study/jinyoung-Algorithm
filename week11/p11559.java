package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class p11559 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N = 12;
	static int M = 6;
	static String[][] field;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		field = new String[N][M];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				field[i][j] = line[j];
			}
		}
		int result = 0;
		boolean flag = true;
		while (true) {
			flag = true;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (field[i][j].equals(".")) {
						continue;
					}
					int puyoCnt = countPuyo(i, j, field[i][j]);
					if (puyoCnt >= 4) {
						flag = false;
						popPuyo(i, j, field[i][j]);
					}
				}
			}
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(field[i]));
//			}
			if (flag) {
				break;
			}
			// result++ 여기서 해야 함!!!
			result++;
			gravityPuyo();
//			System.out.println("=============");
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(field[i]));
//			}
//			System.out.println("================");
		}
		System.out.println(result);
	}

	static void gravityPuyo() {
		for (int i = 0; i < M; i++) {
			for (int j = N - 1; j > 0; j--) {

				if (field[j][i].equals(".")) {
					for (int k = j - 1; k >= 0; k--) {
						if (!field[k][i].equals(".")) {
							field[j][i] = field[k][i];
							field[k][i] = ".";
							break;
						}
					}
				}
			}
		}
	}

	static int countPuyo(int x, int y, String color) {
		Queue<Point> queue = new LinkedList<>();
		visited = new boolean[N][M];
		queue.offer(new Point(x, y));
		visited[x][y] = true;
		cnt = 1;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				if (visited[nx][ny]) {
					continue;
				}
				if (!field[nx][ny].equals(color)) {
					continue;
				}
				queue.offer(new Point(nx, ny));
				visited[nx][ny] = true;
				cnt++;
			}
		}
		return cnt;
	}

	static void popPuyo(int x, int y, String color) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j]) {
					field[i][j] = ".";
				}
			}
		}
	}
}
