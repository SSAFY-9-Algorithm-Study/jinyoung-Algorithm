package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 숲 탐색하기 
 *    - 정령들은 숲의 북쪽을 통해서만 숲에 들어올 수 있습니다.
 *    - 총 K명의 정령은 각자 골렘을 타고 숲을 탐색합니다. 
 *    - 정령은 어떤 방향에서든 골렘에 탑승할 수 있지만 골렘에서 내릴 때에는 정해진 출구를 통해서만 내릴 수 있습니다.
 *    - i번째로 숲을 탐색하는 골렘은 숲의 가장 북쪽에서 시작해 골렘의 중앙이 ci열이 되도록 하는 위치에서 내려오기 시작합니다. 
 *    	초기 골렘의 출구는 di의 방향에 위치해 있습니다.
 * 2. 탐색하는 우선순위 (더 이상 움직이지 못할 때까지 해당 과정을 반복)
 *    - 1. 남쪽으로 한 칸 내려갑니다.
 *    - 2. 1의 방법으로 이동할 수 없으면 서쪽 방향으로 회전하면서 내려갑니다. (출구가 반시계 방향으로 이동)
 *    - 3. 1과 2의 방법으로 이동할 수 없으면 동쪽 방향으로 회전하면서 내려갑니다. (출구가 시계 방향으로 이동) 
 * 3. 도착
 *    - 골렘이 이동할 수 있는 가장 남쪽에 도달해 더이상 이동할 수 없으면 정령은 골렘 내에서 상하좌우 인접한 칸으로 이동이 가능합니다. 
 *      단, 만약 현재 위치하고 있는 골렘의 출구가 다른 골렘과 인접하고 있다면 해당 출구를 통해 다른 골렘으로 이동할 수 있습니다.
 *    - 만약 골렘이 최대한 남쪽으로 이동했지만 골렘의 몸 일부가 여전히 숲을 벗어난 상태라면, 
 *      해당 골렘을 포함해 숲에 위치한 모든 골렘들은 숲을 빠져나간 뒤 다음 골렘부터 새롭게 숲의 탐색을 시작합니다. 
 *      단, 이 경우에는 정령이 도달하는 최종 위치를 답에 포함시키지 않습니다.
 */
public class 마법의숲탐색 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Golem {
		int c, r, d;

		public Golem(int c, int d) {
			this.c = c;
			this.d = d;
			this.r = -2;
		}
	}

	static int R, C, K;
	static int[][] map;
	static Golem[] golems;
	static int[] golemX = { -1, 0, 1, 0 }; // 북, 동, 남, 서
	static int[] golemY = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		golems = new Golem[K + 1];

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());

			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			golems[i] = new Golem(c, d);
		}

		System.out.println(playGame());
	}

	private static int playGame() {

		int answer = 0;

		for (int i = 1; i <= K; i++) {
			
//			System.out.println("round" + i);

			if (!move(i)) {
				// 맵 초기화
				initMap();
			} else {
				// 골렘의 최종 위치 저장
				saveGolemPoint(i);

				// 골렘 탈출
				int result = escapeGolem(i) + 1;
				answer += result;
//				System.out.println(result);
			}
			
		}

		return answer;
	}

	private static void printMap() {
		
		for (int i = 0; i < R; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
	}

	private static int escapeGolem(int idx) {

		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];

		queue.offer(new Point(golems[idx].r, golems[idx].c));
		visited[golems[idx].r][golems[idx].c] = true;

		int maxX = golems[idx].r;

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			int currGolem = map[curr.x][curr.y];

			for (int i = 0; i < golemX.length; i++) {
				int nx = curr.x + golemX[i];
				int ny = curr.y + golemY[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}

				if (visited[nx][ny] || map[nx][ny] == 0) {
					continue;
				}

				// 현재 방향이 출구 방향이고, 다음 위치가 다른 골렘인 경우 이동 가능!
				if (i == golems[currGolem].d && currGolem != map[nx][ny]) {
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
					maxX = Math.max(maxX, nx);
				} else if (currGolem == map[nx][ny]) { // 같은 골렘 내에서 이동 가능
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
					maxX = Math.max(maxX, nx);
				}

			}
		}

		return maxX;
	}

	private static void saveGolemPoint(int idx) {

		int currX = golems[idx].r;
		int currY = golems[idx].c;
		map[currX][currY] = idx;

		for (int i = 0; i < golemX.length; i++) {
			int nx = currX + golemX[i];
			int ny = currY + golemY[i];
			map[nx][ny] = idx;
		}

//		printMap();
	}

	private static void initMap() {

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = 0;
			}
		}

	}

	private static boolean move(int idx) {

		while (true) {

			if (moveDown(idx)) {
				// 아래로 한 칸 이동 성공
				golems[idx].r += golemX[2];
				golems[idx].c += golemY[2];
				continue;
			}

			if (moveLeft(idx)) {
				// 왼쪽으로 한 칸 이동 성공
				golems[idx].r += golemX[3];
				golems[idx].c += golemY[3];

				if (moveDown(idx)) {
					// 아래로 한 칸 이동 성공
					golems[idx].r += golemX[2];
					golems[idx].c += golemY[2];

					// 출구 위치 반시계 방향으로 이동
					golems[idx].d = (golems[idx].d + 4 - 1) % 4;
					continue;
				}
				// 아래로 이동 불가한 경우 되돌려놓기
				golems[idx].r -= golemX[3];
				golems[idx].c -= golemY[3];
			}

			if (moveRight(idx)) {
				// 오른쪽으로 한 칸 이동 성공
				golems[idx].r += golemX[1];
				golems[idx].c += golemY[1];

				if (moveDown(idx)) {
					// 아래로 한 칸 이동 성공
					golems[idx].r += golemX[2];
					golems[idx].c += golemY[2];

					// 출구 위치 시계 방향으로 이동
					golems[idx].d = (golems[idx].d + 1) % 4;
					continue;
				}

				// 아래로 이동 불가한 경우 되돌려놓기
				golems[idx].r -= golemX[1];
				golems[idx].c -= golemY[1];
			}

			// 이동이 불가한 상태
			// 골렘의 몸 일부가 숲을 벗어난 상태라면? return false -> 맵 초기화
			if (golems[idx].r < 1) {
				return false;
			} else {
				return true;
			}

		}

	}

	private static boolean moveRight(int idx) {

		int nx = golems[idx].r + golemX[1];
		int ny = golems[idx].c + golemY[1];

		for (int i = 0; i < golemX.length; i++) {
			int currX = nx + golemX[i];
			int currY = ny + golemY[i];

			if (currX < 0) {
				continue;
			}

			// 이동이 불가능한 경우
			if (currX >= R || currY < 0 || currY >= C || map[currX][currY] != 0) {
				return false;
			}
		}

		return true;
	}

	private static boolean moveLeft(int idx) {

		int nx = golems[idx].r + golemX[3];
		int ny = golems[idx].c + golemY[3];

		for (int i = 0; i < golemX.length; i++) {
			int currX = nx + golemX[i];
			int currY = ny + golemY[i];

			if (currX < 0) {
				continue;
			}

			// 이동이 불가능한 경우
			if (currX >= R || currY < 0 || currY >= C || map[currX][currY] != 0) {
				return false;
			}
		}

		return true;
	}

	private static boolean moveDown(int idx) {

		int nx = golems[idx].r + golemX[2];
		int ny = golems[idx].c + golemY[2];

		for (int i = 0; i < golemX.length; i++) {
			int currX = nx + golemX[i];
			int currY = ny + golemY[i];

			if (currX < 0) {
				continue;
			}

			// 이동이 불가능한 경우
			if (currX >= R || currY < 0 || currY >= C || map[currX][currY] != 0) {
				return false;
			}
		}

		return true;
	}

}