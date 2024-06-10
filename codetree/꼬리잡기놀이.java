package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 한 라운드는 다음과 같이 진행됩니다.
 * 1. 먼저 각 팀은 머리사람을 따라서 한 칸 이동합니다.
 * 2. 각 라운드마다 공이 정해진 선을 따라 던져집니다.
 * 3. 해당 선에 사람이 있으면 최초에 만나게 되는 사람이 공을 얻게 되어 점수를 얻게 됩니다.
 *    - 점수는 해당 사람이 머리사람을 시작으로 팀 내에서 k번째 사람이라면 k의 제곱만큼 점수를 얻게 됩니다.
 * 4. 공을 획득한 팀의 경우에는 머리사람과 꼬리사람이 바뀝니다. 즉 방향을 바꾸게 됩니다.
 */
public class 꼬리잡기놀이 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Header {
		int headerX, headerY;

		Header(int headerX, int headerY) {
			this.headerX = headerX;
			this.headerY = headerY;
		}

		@Override
		public String toString() {
			return headerX + " " + headerY;
		}
	}

	static int n, m, k;
	static int[][] map, roadMap;
	static boolean[][] road, visited, visitedRoad;
	static Header[] headers;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		road = new boolean[n][n];
		roadMap = new int[n][n];
		headers = new Header[m];
		visited = new boolean[n][n];

		int headerIdx = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 0) {
					road[i][j] = true;

					if (map[i][j] == 1) {
						headers[headerIdx++] = new Header(i, j);
					} else if (map[i][j] == 4) {
						map[i][j] = 0;
					}
				}
			}
		}

		checkRoad();
		System.out.println(playGame());
	}

	private static void checkRoad() {

		visitedRoad = new boolean[n][n];
		int idx = 1;

		for (int i = 0; i < headers.length; i++) {
			bfs(headers[i].headerX, headers[i].headerY, idx++);
		}

	}

	private static void bfs(int x, int y, int idx) {

		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];

		queue.offer(new Point(x, y));
		visited[x][y] = true;
		roadMap[x][y] = idx;

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
					continue;
				}

				if (visitedRoad[nx][ny] || !road[nx][ny]) {
					continue;
				}

				queue.offer(new Point(nx, ny));
				visitedRoad[nx][ny] = true;
				roadMap[nx][ny] = idx;
			}
		}

	}

	private static int playGame() {

		int answer = 0;

		for (int i = 0; i < k; i++) {
			moveHeader();
			int score = shootBall(i);
			answer += score * score;
		}

		return answer;
	}

	private static int shootBall(int round) {

		switch (round / n % 4) {
		case 0:
			return shootRight(round % n);
		case 1:
			return shootUp(round % n);
		case 2:
			return shootLeft(round % n);
		default:
			return shootDown(round % n);
		}
	}

	private static int shootDown(int idx) {

		int nx = 0;
		int ny = n - 1 - idx;

		while (nx >= 0 && ny >= 0 && nx < n && ny < n) {

			if (map[nx][ny] > 0) {
				return findNumber(nx, ny);
			}

			nx += 1;
		}

		return 0;
	}

	private static int shootLeft(int idx) {

		int nx = n - 1 - idx;
		int ny = n - 1;

		while (nx >= 0 && ny >= 0 && nx < n && ny < n) {

			if (map[nx][ny] > 0) {
				return findNumber(nx, ny);
			}

			ny -= 1;
		}

		return 0;
	}

	private static int shootUp(int idx) {

		int nx = n - 1;
		int ny = idx;

		while (nx >= 0 && ny >= 0 && nx < n && ny < n) {

			if (map[nx][ny] > 0) {
				return findNumber(nx, ny);
			}

			nx -= 1;
		}

		return 0;
	}

	private static int shootRight(int idx) {

		int nx = idx;
		int ny = 0;

		while (nx >= 0 && ny >= 0 && nx < n && ny < n) {

			if (map[nx][ny] > 0) {
				return findNumber(nx, ny);
			}

			ny += 1;
		}

		return 0;
	}

	private static int findNumber(int x, int y) {

		if (map[x][y] == 1) {
			changeDir(x, y);
			return 1;
		}

		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];

		queue.offer(new Point(x, y));
		visited[x][y] = true;

		int cnt = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int s = 0; s < size; s++) {
				Point curr = queue.poll();

				for (int i = 0; i < dx.length; i++) {
					int nx = curr.x + dx[i];
					int ny = curr.y + dy[i];

					if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
						continue;
					}

					if (visited[nx][ny] || map[nx][ny] == 0) {
						continue;
					}

					if (map[nx][ny] == 1) { // 머리 찾음

						if (map[curr.x][curr.y] == 3) {
							continue;
						}

						changeDir(nx, ny);
						return cnt + 1;
					} else if (map[nx][ny] == 3) { // 꼬리 찾음
						continue;
					} else {
						queue.offer(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}

			cnt++;
		}

		return 0;
	}

	private static void changeDir(int x, int y) {

		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];

		queue.offer(new Point(x, y));
		visited[x][y] = true;

		while (!queue.isEmpty()) {

			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
					continue;
				}

				if (visited[nx][ny] || map[nx][ny] == 0) {
					continue;
				}

				if (map[nx][ny] == 3) { // 꼬리 찾음
					// 머리 꼬리 위치 바꿔치기
					int idx = roadMap[nx][ny];
					int headerX = headers[idx - 1].headerX;
					int headerY = headers[idx - 1].headerY;

					map[headerX][headerY] = 3;

					headers[idx - 1].headerX = nx;
					headers[idx - 1].headerY = ny;
					map[nx][ny] = 1;

					return;
				} else {
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}

		}

	}

	private static void printMap() {

		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}

	private static void moveHeader() {

		// 각 그룹의 헤더를 기준으로 이동
		for (int i = 0; i < m; i++) {
			initVisited();

			int currX = headers[i].headerX;
			int currY = headers[i].headerY;

			for (int j = 0; j < dx.length; j++) {
				int nx = currX + dx[j];
				int ny = currY + dy[j];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
					continue;
				}

				if (road[nx][ny] == false) {
					continue;
				}

				// 이동할 칸을 찾음
				if (map[nx][ny] == 0 || map[nx][ny] == 3) {

					visited[currX][currY] = true;

					// 뒤에 따라오는 사람들 이동시키기
					move(currX, currY, false);

					// 머리 좌표 바꿔주기
					map[nx][ny] = 1;
					headers[i].headerX = nx;
					headers[i].headerY = ny;
					break;
				}

			}
		}

	}

	private static void move(int currX, int currY, boolean flag) {

		for (int i = 0; i < dx.length; i++) {
			int nx = currX + dx[i];
			int ny = currY + dy[i];

			if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
				continue;
			}

			if (road[nx][ny] == false) {
				continue;
			}

			// 다른 사람을 찾음
			if (map[nx][ny] == 2 && !visited[nx][ny]) {
				visited[nx][ny] = true;
				map[currX][currY] = 2;
				move(nx, ny, true);
			}

			// 꼬리를 찾음
			if (map[nx][ny] == 3 && !visited[nx][ny] && flag) {
				visited[nx][ny] = true;
				map[currX][currY] = 3;
				map[nx][ny] = 0;
				return;
			}

		}

	}

	private static void initVisited() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				visited[i][j] = false;
			}
		}

	}

}
