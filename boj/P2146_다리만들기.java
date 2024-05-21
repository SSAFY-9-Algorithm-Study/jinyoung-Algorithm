package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2146_다리만들기 {

	static class Point {
		int x, y, islandNumber, cnt;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Point(int x, int y, int islandNumber, int cnt) {
			this.x = x;
			this.y = y;
			this.islandNumber = islandNumber;
			this.cnt = cnt;
		}

	}

	static int N;
	static int[][] map, islandNumberMap;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static Queue<Point> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		islandNumberMap = new int[N][N];

		pq = new PriorityQueue<>((o1, o2) -> {
			return o1.cnt - o2.cnt;
		});

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][N];
		int islandNumber = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (visited[i][j]) {
					continue;
				}

				if (map[i][j] == 0) {
					continue;
				}

				divideIsland(i, j, islandNumber++);
			}
		}

		System.out.println(connectIsland());
	}

	private static void divideIsland(int startX, int startY, int islandNumber) {

		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(startX, startY));
		visited[startX][startY] = true;
		islandNumberMap[startX][startY] = islandNumber;
		pq.offer(new Point(startX, startY, islandNumber, 0));

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				if (map[nx][ny] == 0 || visited[nx][ny]) {
					continue;
				}

				visited[nx][ny] = true;
				queue.offer(new Point(nx, ny));
				islandNumberMap[nx][ny] = islandNumber;
				pq.offer(new Point(nx, ny, islandNumber, 0));
			}
		}

	}

	private static int connectIsland() {
		int[][] visitedMap = new int[N][N];

		while (!pq.isEmpty()) {
			Point curr = pq.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				// 같은 번호의 섬으로 갔을 경우 continue
				if (curr.islandNumber == islandNumberMap[nx][ny]) {
					continue;
				}

				// 다른 번호의 섬에 도착했을 경우 or 다른 번호의 섬이 방문한 바다에 도착한 경우 
				if (curr.islandNumber != islandNumberMap[nx][ny] && islandNumberMap[nx][ny] != 0) {
					return curr.cnt + visitedMap[nx][ny];
				}

				// 다른 섬에서 도착한 바다가 아닌 경우 
				if (map[nx][ny] == 0) {
					pq.offer(new Point(nx, ny, curr.islandNumber, curr.cnt + 1));
					visitedMap[nx][ny] = curr.cnt + 1;
					islandNumberMap[nx][ny] = curr.islandNumber;
				}

			}
		}
		return 0;
	}
}