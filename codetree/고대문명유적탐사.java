package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 회전하기
 *    - 90도, 180도, 270도 중 하나의 각도만큼 회전시킬 수 있습니다.
 *    - 가능한 회전의 방법 중 1. 유물 1차 획득 가치를 최대화하고, 
 *    	그러한 방법이 여러가지인 경우 2. 회전한 각도가 가장 작은 방법을 선택합니다. 
 *    	그러한 경우도 여러가지인 경우 3. 회전 중심 좌표의 열이 가장 작은 구간을, 열이 같다면 행이 가장 작은 구간을 선택합니다.
 * 2. 유물 획득
 *    - 상하좌우 인접한 조각이 3개 이상인 경우, 유물이 되어 사라진다. 
 *    - 조각이 사라진 위치에는 유적의 벽면에 적혀있는 순서대로 새로운 조각이 생겨납니다. 
 *    	새로운 조각은 1. 열 번호가 작은 순으로 조각이 생겨나고, 열 번호가 같다면 2. 행 번호가 큰 순으로 조각이 생겨납니다.
 * 3. 유물 연쇄 획득
 *    - 새로운 유물 조각이 생겨난 이후에도 조각들이 3개 이상 연결된 경우, 사라진 위치에는 또다시 새로운 조각이 생겨나며 
 *      이는 더 이상 조각이 3개 이상 연결되지 않아 유물이 될 수 없을 때까지 반복됩니다.
 * 4. 탐사 반복
 *    - 이 문제에서는 탐사 진행 ~ 유물 연쇄 획득의 과정까지를 1턴으로 생각하며, 총 K 번의 턴에 걸쳐 진행됩니다.
 *    - 단, 아직 K번의 턴을 진행하지 못했지만, 탐사 진행 과정에서 어떠한 방법을 사용하더라도 유물을 획득할 수 없었다면
 *      모든 탐사는 그 즉시 종료됩니다.
 */
public class 고대문명유적탐사 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static final int MAP_LENGTH = 5;
	static final int SELECTED_LENGTH = 3;
	static int K, M;
	static int[][] map, newMap;
	static Queue<Integer> queue;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[MAP_LENGTH][MAP_LENGTH];
		newMap = new int[MAP_LENGTH][MAP_LENGTH];
		queue = new ArrayDeque<>();

		for (int i = 0; i < MAP_LENGTH; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < MAP_LENGTH; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			queue.offer(Integer.parseInt(st.nextToken()));
		}

		playGame();
	}

	private static void playGame() {

		for (int i = 0; i < K; i++) {

			int score = selectPoint();

			if (score == 0) {
				break;
			}

			// 빈 칸 다시 채우기
			fillMap();

			int extraScore = checkMap();
			while (extraScore > 0) {
				score += extraScore;
				fillMap();
				extraScore = checkMap();
			}
			System.out.print(score + " ");

		}

	}

	private static void fillMap() {

		for (int i = 0; i < MAP_LENGTH; i++) {
			for (int j = MAP_LENGTH - 1; j >= 0; j--) {
				if (map[j][i] == 0) {
					map[j][i] = queue.poll();
				}
			}
		}

	}

	private static int selectPoint() {

		int maxScore = 0;
		int maxX = 0;
		int maxY = 0;
		int maxDegree = 0;

		for (int k = 0; k <= 2; k++) { // 회전 각도
			for (int i = 0; i <= MAP_LENGTH - SELECTED_LENGTH; i++) {
				for (int j = 0; j <= MAP_LENGTH - SELECTED_LENGTH; j++) {

					int score = turnMap(j, i, k);

					if (maxScore < score) {
						maxScore = score;
						maxX = j;
						maxY = i;
						maxDegree = k;
					}
				}
			}
		}

		if (maxScore == 0) {
			return 0;
		}

		getScore(maxX, maxY, maxDegree);

		return maxScore;
	}

	private static void getScore(int maxX, int maxY, int maxDegree) {

		int[][] tempMap = new int[MAP_LENGTH][MAP_LENGTH];

		for (int d = 0; d <= maxDegree; d++) {

			for (int i = 0; i < SELECTED_LENGTH; i++) {
				for (int j = 0; j < SELECTED_LENGTH; j++) {
					tempMap[i + maxX][j + maxY] = map[SELECTED_LENGTH - j + maxX - 1][i + maxY];
				}
			}

			for (int i = maxX; i < maxX + SELECTED_LENGTH; i++) {
				for (int j = maxY; j < maxY + SELECTED_LENGTH; j++) {
					map[i][j] = tempMap[i][j];
				}
			}

		}

		checkMap();

	}

	private static int checkMap() {

		visited = new boolean[MAP_LENGTH][MAP_LENGTH];
		int sum = 0;

		for (int i = 0; i < MAP_LENGTH; i++) {
			for (int j = 0; j < MAP_LENGTH; j++) {

				if (!visited[i][j]) {
					int count = bfs(i, j, map);

					if (count >= 3) {
						sum += count;
						removeMap(i, j);
					}
				}
			}
		}

		return sum;
	}

	private static void removeMap(int x, int y) {

		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		int number = map[x][y];
		map[x][y] = 0;

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= MAP_LENGTH || ny < 0 || ny >= MAP_LENGTH) {
					continue;
				}

				if (map[nx][ny] == number) {
					queue.offer(new Point(nx, ny));
					map[nx][ny] = 0;
				}
			}
		}

	}

	private static int turnMap(int x, int y, int degree) {

		initNewMap();
		int[][] tempMap = new int[MAP_LENGTH][MAP_LENGTH];

		for (int d = 0; d <= degree; d++) {

			for (int i = 0; i < SELECTED_LENGTH; i++) {
				for (int j = 0; j < SELECTED_LENGTH; j++) {
					tempMap[i + x][j + y] = newMap[SELECTED_LENGTH - j + x - 1][i + y];

				}
			}

			for (int i = x; i < x + SELECTED_LENGTH; i++) {
				for (int j = y; j < y + SELECTED_LENGTH; j++) {
					newMap[i][j] = tempMap[i][j];
				}
			}

		}

		int score = calcScore();

		return score;
	}

	private static int calcScore() {

		visited = new boolean[MAP_LENGTH][MAP_LENGTH];
		int sum = 0;

		for (int i = 0; i < MAP_LENGTH; i++) {
			for (int j = 0; j < MAP_LENGTH; j++) {

				if (!visited[i][j]) {
					int count = bfs(i, j, newMap);

					if (count >= 3) {
						sum += count;
					}
				}
			}
		}

		return sum;
	}

	private static int bfs(int x, int y, int[][] tempMap) {

		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;
		int size = 1;
		int number = tempMap[x][y];

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= MAP_LENGTH || ny < 0 || ny >= MAP_LENGTH) {
					continue;
				}

				if (visited[nx][ny]) {
					continue;
				}

				if (tempMap[nx][ny] == number) {
					size++;
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}

		return size;
	}

	private static void initNewMap() {

		for (int i = 0; i < MAP_LENGTH; i++) {
			for (int j = 0; j < MAP_LENGTH; j++) {
				newMap[i][j] = map[i][j];
			}
		}

	}
}
