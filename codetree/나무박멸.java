package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 나무 성장
 *    - 인접한 네 개의 칸 중 나무가 있는 칸의 수만큼 나무가 성장합니다.
 *    - 기존에 있었던 나무들은 인접한 4개의 칸 중 벽, 다른 나무, 제초제 모두 없는 칸에 번식을 진행합니다.
 *      각 칸의 나무 그루 수에서 총 번식이 가능한 칸의 개수만큼 나누어진 그루 수만큼 번식이 되며, 나눌 때 생기는 나머지는 버립니다.
 * 2. 제초제 뿌리기
 *    - 각 칸 중 제초제를 뿌렸을 때 나무가 가장 많이 박멸되는 칸에 제초제를 뿌립니다.
 *      나무가 없는 칸에 제초제를 뿌리면 박멸되는 나무가 전혀 없는 상태로 끝이 나지만, 
 *      나무가 있는 칸에 제초제를 뿌리게 되면 4개의 대각선 방향으로 k칸만큼 전파되게 됩니다.
 *    - 단, 전파되는 도중 벽이 있거나 나무가 아예 없는 칸이 있는 경우, 
 *      그 칸 까지는 제초제가 뿌려지며 그 이후의 칸으로는 제초제가 전파되지 않습니다.
 *    - 제초제가 뿌려진 칸에는 c년만큼 제초제가 남아있다가 c+1년째가 될 때 사라지게 됩니다. 
 *      제초제가 뿌려진 곳에 다시 제초제가 뿌려지는 경우에는 새로 뿌려진 해로부터 다시 c년동안 제초제가 유지됩니다.
 */

public class 나무박멸 {

	static int N, M, K, C;
	static int[][] map, copyMap, deadMap;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[] ddx = { -1, -1, 1, 1 };
	static int[] ddy = { -1, 1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		copyMap = new int[N][N];
		deadMap = new int[N][N];

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

		for (int i = 0; i < M; i++) {
			growTree();
			breedTree();
			reduceDeathCnt();
			answer += spreadDeath();
		}

		return answer;
	}

	private static void printMap() {

		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}

	private static void reduceDeathCnt() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (deadMap[i][j] > 0) {
					deadMap[i][j]--;
				}
			}
		}

	}

	private static int spreadDeath() {

		int maxCount = 0;
		int maxX = 0;
		int maxY = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;

				if (map[i][j] > 0) {
					cnt = countKillingTree(i, j);
				}

				if (cnt > maxCount) {
					maxCount = cnt;
					maxX = i;
					maxY = j;
				}
			}
		}
//		System.out.println(maxCount + " " + maxX + " " + maxY);
		killTree(maxX, maxY);
		return maxCount;
	}

	private static void killTree(int x, int y) {

		if (map[x][y] > 0) {
			map[x][y] = 0;
		}

		deadMap[x][y] = C;

		for (int i = 0; i < ddx.length; i++) {
			int nx = x;
			int ny = y;

			for (int j = 0; j < K; j++) {
				nx += ddx[i];
				ny += ddy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					break;
				}


				// 빈 칸이거나, 벽이 있다면 continue;
				if (map[nx][ny] <= 0) {
					deadMap[nx][ny] = C;
					break;
				} else {
					map[nx][ny] = 0;
					deadMap[nx][ny] = C;
				}
			}
		}

	}

	private static int countKillingTree(int x, int y) {

		int cnt = map[x][y];

		for (int i = 0; i < ddx.length; i++) {
			int nx = x;
			int ny = y;

			for (int j = 0; j < K; j++) {
				nx += ddx[i];
				ny += ddy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					break;
				}

				// 빈 칸이거나, 벽이 있다면 탐색 종료
				if (map[nx][ny] <= 0) {
					break;
				}

				cnt += map[nx][ny];
			}
		}

		return cnt;
	}

	private static void breedTree() {

		copyMap();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (copyMap[i][j] > 0) {
					int cnt = countBlankMap(i, j);
					if (cnt == 0)
						continue;
					breadBlankMap(i, j, copyMap[i][j] / cnt);
				}

			}
		}

	}

	private static void copyMap() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

	}

	private static void breadBlankMap(int x, int y, int cnt) {

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}

			if (copyMap[nx][ny] == 0 && deadMap[nx][ny] == 0) {
				map[nx][ny] += cnt;
			}
		}

	}

	private static int countBlankMap(int x, int y) {

		int cnt = 0;

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}

			if (copyMap[nx][ny] == 0 && deadMap[nx][ny] == 0) {
				cnt++;
			}
		}
		return cnt;
	}

	private static void growTree() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0) {
					map[i][j] += countTree(i, j);
				}
			}
		}

	}

	private static int countTree(int x, int y) {

		int cnt = 0;

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}

			if (map[nx][ny] > 0) {
				cnt++;
			}
		}
		return cnt;
	}
}
