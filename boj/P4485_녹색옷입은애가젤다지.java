package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4485_녹색옷입은애가젤다지 {

	static class Point {
		int x, y, cost;

		Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	static int N;
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int idx = 1;
		while (N != 0) {

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println("Problem " + idx + ": " + bfs());
			N = Integer.parseInt(br.readLine());
			idx++;
		}

	}

	private static int bfs() {

		Queue<Point> pq = new PriorityQueue<>((o1, o2) -> {
			return o1.cost - o2.cost;
		});

		int[][] costMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(costMap[i], Integer.MAX_VALUE);
		}

		pq.offer(new Point(0, 0, map[0][0]));
		costMap[0][0] = map[0][0];

		while (!pq.isEmpty()) {
			Point curr = pq.poll();

			if (curr.x == N - 1 && curr.y == N - 1) {
				return curr.cost;
			}

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				if (curr.cost + map[nx][ny] < costMap[nx][ny]) {
					costMap[nx][ny] = curr.cost + map[nx][ny];
					pq.offer(new Point(nx, ny, curr.cost + map[nx][ny]));
				}
			}
		}

		return 0;
	}
}
