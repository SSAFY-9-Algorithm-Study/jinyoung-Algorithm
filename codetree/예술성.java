package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 그룹 만들기
 *    - 동일한 숫자가 상하좌우로 인접해있는 경우 동일한 그룹이라 본다면, 총 4개의 그룹이 만들어지게 됩니다.
 * 2. 예술 점수 구하기
 *    - 그룹 a와 그룹 b의 조화로움은 
 *    (그룹 a에 속한 칸의 수 + 그룹 b에 속한 칸의 수 ) x 그룹 a를 이루고 있는 숫자 값 
 *    x 그룹 b를 이루고 있는 숫자 값 x 그룹 a와 그룹 b가 서로 맞닿아 있는 변의 수로 정의됩니다.
 *    - 각 그룹의 조화로움 값을 전부 더하면 초기 예술 점수는 48 + 192 + 152 + 156 = 548이 됩니다.
 * 3. 회전하기
 *    - 회전은 정중을 기준으로 두 선을 그어 만들어지는 십자 모양과 그 외 부분으로 나뉘어 진행됩니다.
 *    - 십자 모양의 경우 통째로 반시계 방향으로 90' 회전합니다.
 *    - 십자 모양을 제외한 4개의 정사각형은 각각 개별적으로 시계 방향으로 90'씩 회전이 진행됩니다.
 * 4. 결과 출력
 *    - 초기 예술 점수, 1회전 이후 예술 점수, 2회전 이후 예술 점수, 그리고 3회전 이후 예술 점수의 합을 구하시오. 
 */

public class 예술성 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static final int LAST_TIME = 3;
	static int N;
	static int[][] map, groupMap, nearLines;
	static List<Integer> groupCounts, groupNumbers;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		groupMap = new int[N][N];
		groupCounts = new ArrayList<>();
		groupNumbers = new ArrayList<>();
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solve());
	}

	private static int solve() {

		int answer = 0;
		
		for (int i = 0; i <= LAST_TIME; i++) {
			countGroupSpace();
			answer += calcArtScore();
			turnMap();
		}

		return answer;
	}

	private static void printMap() {
		
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
	}

	private static void turnMap() {
		
		int centerNum = N / 2;
		
		turnLeft(centerNum);
		
		turnRight(0, 0, centerNum);
		turnRight(0, centerNum + 1, centerNum);
		turnRight(centerNum + 1, 0, centerNum);
		turnRight(centerNum + 1, centerNum + 1, centerNum);
		
	}

	private static void turnRight(int startX, int startY, int length) {
		
		int[][] newMap = new int[N][N];
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				newMap[i + startX][j + startY] = map[length - 1 - j + startX][i + startY];
			}
		}
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				map[i + startX][j + startY] = newMap[i + startX][j + startY];
			}
		}
		
	}

	private static void turnLeft(int centerNum) {
		
		int[] flagArr = new int[N];
		
		for (int i = 0; i < N; i++) {
			flagArr[i] = map[i][centerNum];
		}
		
		for (int i = 0; i < N; i++) {
			map[i][centerNum] = map[centerNum][N - 1 - i];
		}
		
		for (int i = 0; i < N; i++) {
			map[centerNum][i] = flagArr[i];
		}
		
	}

	private static int calcArtScore() {

		// 맞닿아 있는 변의 수 구하기 
		boolean[] visitedGroup = new boolean[groupCounts.size()];
		nearLines = new int[groupCounts.size()][groupCounts.size()];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visitedGroup[groupMap[i][j]]) {
					checkNearGroups(i, j, groupMap[i][j], visitedGroup);
					visitedGroup[groupMap[i][j]] = true;
				}
			}
		}
		
		int sumArtScore = 0;
		for (int i = 0; i < groupCounts.size() - 1; i++) {
			for (int j = i + 1; j < groupCounts.size(); j++) {
				// 맞닿은 변의 수가 0이라면, 계산하지 않음 
				if (nearLines[i][j] == 0) {
					continue;
				}
				
				int artScore = (groupCounts.get(i) + groupCounts.get(j)) 
						* groupNumbers.get(i) * groupNumbers.get(j) * nearLines[i][j];
				sumArtScore += artScore;
			}
		}
//		System.out.println(sumArtScore);
		return sumArtScore;
	}

	private static void checkNearGroups(int x, int y, int groupIdx, boolean[] visitedGroup) {

		Queue<Point> queue = new ArrayDeque<>();
		int size = 0;
		loop: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (groupMap[i][j] == groupIdx) {
					queue.offer(new Point(i, j));
					size++;
				}

				if (size == groupCounts.get(groupIdx)) {
					break loop;
				}
			}
		}

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				int number = groupMap[nx][ny];
				if (visitedGroup[number]) {
					continue;
				}
				
				nearLines[groupIdx][number]++;
			}
		}

	}

	private static void countGroupSpace() {

		initVisited();
		groupCounts.clear();
		groupNumbers.clear();

		int groupIdx = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					int cnt = bfs(i, j, groupIdx++);
					groupCounts.add(cnt);
					groupNumbers.add(map[i][j]);
				}
			}
		}

	}

	private static int bfs(int x, int y, int groupIdx) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;
		groupMap[x][y] = groupIdx;

		int size = 1;
		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				if (visited[nx][ny] || map[x][y] != map[nx][ny]) {
					continue;
				}

				queue.offer(new Point(nx, ny));
				visited[nx][ny] = true;
				groupMap[nx][ny] = groupIdx;
				size++;
			}
		}

		return size;
	}

	private static void initVisited() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}
	}
}
