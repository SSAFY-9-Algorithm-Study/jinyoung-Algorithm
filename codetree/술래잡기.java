package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 도망자의 이동
 *    - 도망자가 움직일 때 현재 술래와의 거리가 3 이하인 도망자만 움직입니다.
 *      도망자의 위치가 (x1, y1), 술래의 위치가 (x2, y2)라 했을 때 두 사람간의 거리는 |x1 - x2| + |y1 - y2|로 정의
 *      1) 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나지 않는 경우
 *         ㄱ. 움직이려는 칸에 술래가 있는 경우라면 움직이지 않습니다.
 *         ㄴ. 움직이려는 칸에 술래가 있지 않다면 해당 칸으로 이동합니다.
 *      2) 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나는 경우
 *         ㄱ. 먼저 방향을 반대로 틀어줍니다. 
 *         ㄴ. 이후 바라보고 있는 방향으로 1칸 움직인다 했을 때 해당 위치에 술래가 없다면 1칸 앞으로 이동합니다.
 * 2. 술래의 이동
 *    - 술래는 중앙에서부터 달팽이 모양으로 움직입니다.
 *    - 끝에 도달했을 경우, 다시 거꾸로 중심으로 이동합니다. 
 *    - 술래는 1번의 턴 동안, 해당하는 방향으로 한 칸 이동합니다.
 *      이동 후의 위치가 만약 이동방향이 틀어지는 지점이라면, 방향을 바로 틀어줍니다. 
 *      만약 이동을 통해 양끝에 해당하는 위치인 (1행, 1열) 혹은 정중앙에 도달하게 된다면 이 경우 역시 방향을 바로 틀어줘야 함에 유의
 * 3. 술래가 도망자를 잡기
 *    - 이동 직후 술래는 턴을 넘기기 전에 시야 내에 있는 도망자를 잡게 됩니다.
 *    - 술래의 시야는 현재 바라보고 있는 방향을 기준으로 현재 칸을 포함하여 총 3칸입니다.
 *    - 하지만 만약 나무가 놓여 있는 칸이라면, 해당 칸에 있는 도망자는 나무에 가려져 보이지 않게 됩니다.
 *    - 현재 턴을 t번째 턴이라고 했을 때 <t x 현재 턴에서 잡힌 도망자의 수> 만큼의 점수를 얻게 됩니다. 
 */

public class 술래잡기 {
	static class Runner {
		boolean dirVertical, isDead;
		int x, y, dir;

		public Runner(int x, int y, boolean dirVertical, int dir) {
			this.x = x;
			this.y = y;
			this.dirVertical = dirVertical;
			this.dir = dir;
			this.isDead = false;
		}
	}

	static int N, M, H, K;
	static int[] type1DX = { 0, 0 }; // 좌우로 움직이는 경우, dx로 사용한다.
	static int[] type2DX = { 1, -1 }; // 상하로 움직이는 경우, dx로 사용한다.
	static List<Integer>[][] runnerMap;
	static Runner[] runners;
	static boolean[][] treeMap;
	static int policeX, policeY;
	static boolean policeDir;
	static int[][] policeForwardMap, policeReverseMap;
	static int[] dx = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] dy = { 0, 1, 0, -1 };
	static final int DISTANCE_VALUE = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		runners = new Runner[M];
		runnerMap = new ArrayList[N][N];
		treeMap = new boolean[N][N];
		policeX = N / 2;
		policeY = N / 2;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				runnerMap[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int dirType = Integer.parseInt(st.nextToken());

			if (dirType == 1) { // 좌우로 움직임
				runners[i] = new Runner(x, y, false, 0);
			} else { // 상하로 움직임
				runners[i] = new Runner(x, y, true, 0);
			}
		}

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			treeMap[x][y] = true;
		}

		initRunnerMap();
		System.out.println(playGame());
	}

	private static void initRunnerMap() {

		for (int i = 0; i < M; i++) {
			int x = runners[i].x;
			int y = runners[i].y;

			runnerMap[x][y].add(i);
		}

	}

	private static int playGame() {

		initMovePolice();

		int answer = 0;

		for (int i = 0; i < K; i++) {
			moveRunner();
			movePolice(i);
			int count = catchRunner();
			answer += (i + 1) * count;
		}

		return answer;
	}

	private static void printRunnerMap() {

		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(runnerMap[i]));
		}

	}

	private static int catchRunner() {

		int count = 0;

		int dir = 0;
		if (policeDir == false) { // 정방향
			dir = policeForwardMap[policeX][policeY];
		} else {
			dir = policeReverseMap[policeX][policeY];
		}

		int nx = policeX;
		int ny = policeY;

		if ((nx == 0 && ny == 0) || (nx == N / 2 && ny == N / 2)) {
			policeDir = !policeDir;
		}

		for (int i = 0; i < DISTANCE_VALUE; i++) {

//			System.out.println(nx + " " + ny + " " + dir);

			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				nx += dx[dir];
				ny += dy[dir];
				continue;
			}

			if (treeMap[nx][ny]) {
				nx += dx[dir];
				ny += dy[dir];
				continue;
			}

			if (runnerMap[nx][ny].size() == 0) {
				nx += dx[dir];
				ny += dy[dir];
				continue;
			}

			for (int j = 0; j < runnerMap[nx][ny].size(); j++) {
				int curr = runnerMap[nx][ny].get(j);
				runners[curr].isDead = true;
				count++;
//				System.out.println("catch");
			}

			runnerMap[nx][ny].clear();

			nx += dx[dir];
			ny += dy[dir];

		}

		return count;
	}

	private static void movePolice(int round) {

		int dir = 0;
		if (policeDir == false) { // 정방향
			dir = policeForwardMap[policeX][policeY];
		} else {
			dir = policeReverseMap[policeX][policeY];
		}
		policeX += dx[dir];
		policeY += dy[dir];

	}

	private static void initMovePolice() {

		int policeDir = 0;
		int policeMoving = 0;
		int policeMovingValue = 1;
		boolean policeMovingChange = false;

		policeForwardMap = new int[N][N];
		policeReverseMap = new int[N][N];

		int x = N / 2;
		int y = N / 2;

		for (int i = 0; i < N * N - 1; i++) {

			x += dx[policeDir];
			y += dy[policeDir];

			policeForwardMap[x][y] = policeDir;
			policeReverseMap[x][y] = (policeDir + 2) % 4;

			policeMoving++;
			if (policeMoving == policeMovingValue) {
				// 이동 후 방향을 틀어주는 지점이라면, 바로 방향을 바꿔줌
				policeDir = (policeDir + 1) % 4;
				policeForwardMap[x][y] = policeDir;
				policeReverseMap[x][y] = (policeDir + 1) % 4;

				policeMoving = 0; // 이동 방향 수 초기화

				if (policeMovingChange == true) { // 2번 방향 바꾸면, 방향 바꾸는 값을 증가시켜주기
					policeMovingChange = false;
					policeMovingValue++;
				} else {
					policeMovingChange = true;
				}

			}

		}

		policeForwardMap[0][0] = 2;

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(policeForwardMap[i]));
//		}
//		
//		System.out.println();
//		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(policeReverseMap[i]));
//		}

	}

	private static void moveRunner() {

		for (int i = 0; i < M; i++) {

			if (runners[i].isDead) { // 이미 잡힌 도망자라면, continue;
				continue;
			}

			int distance = Math.abs(runners[i].x - policeX) + Math.abs(runners[i].y - policeY);
			if (distance > DISTANCE_VALUE) {
				continue;
			}

			// 이동 시 격자를 벗어나는 경우, 방향 바꿔주기
			// 격자를 벗어나지 않는다면, 이동
			if (!checkRunnerDir(i)) {
				runners[i].dir = (runners[i].dir + 1) % 2;
				checkRunnerDir(i);
			}

		}

	}

	private static boolean checkRunnerDir(int idx) {

		int nx = runners[idx].x;
		int ny = runners[idx].y;

		if (runners[idx].dirVertical) {
			nx += type2DX[runners[idx].dir];
			ny += type1DX[runners[idx].dir];
		} else {
			nx += type1DX[runners[idx].dir];
			ny += type2DX[runners[idx].dir];
		}

		if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
			return false;
		}

		// 이동하려는 위치에 술래가 없다면 이동
		if (nx != policeX || ny != policeY) {

			runnerMap[runners[idx].x][runners[idx].y].remove(Integer.valueOf(idx));

			runners[idx].x = nx;
			runners[idx].y = ny;

			runnerMap[runners[idx].x][runners[idx].y].add(idx);
		}

		return true;
	}
}
