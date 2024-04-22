package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 1. 공격자 선정 (공격력이 가장 낮은 포탑, 선정 후  N+M만큼의 공격력이 증가한다.)
 *   - 공격력이 같다면, 
 *  	1) 가장 최근에 공격한 포탑
 *   	2) 포탑 위치의 행과 열의 합이 가장 큰 포탑
 *   	3) 포탑 위치의 열 값이 가장 큰 포탑이 가장 약한 포탑이다. 
 * 2. 공격자의 공격 (자신을 제외한 가장 공격력이 높은 포탑을 공격한다.)
 *    - 공격력이 같다면,
 *    	 1) 공격한지 가장 오래된 포탑
 *    	 2) 포탑 위치의 행과 열의 합이 가장 작은 포탑
 *    	 3) 포탑 위치의 열 값이 가장 작은 포탑이 가장 강한 포탑이다. 
 * 3. 레이저 공격 (레이저 공격을 하지 못한 경우, 포탄 공격을 한다.)
 * 	 - 부서진 포탑이 있는 위치는 지날 수 없다.
 * 	 - 공격자의 위치에서 공격 대상 포탑까지의 최단 경로로 공격한다.
 *   - 최단 경로가 2개 이상이라면, 우/하/좌/상의 우선순위대로 먼저 움직인 경로가 선택된다. 
 *   - 공격 대상에는 공격자의 공격력만큼 피해를 입고, 다른 레이저 경로의 포탑들은 공격력/2만큼 피해를 입는다. 
 * 4. 포탄 공격
 *   - 공격 대상에는 공격자의 공격력만큼 피해를 입고, 주위 8방향 포탑들은 공격력/2만큼 피해를 입는다. 
 *   - 만약 가장자리에 포탄이 떨어졌다면, 포탄의 추가 피해가 반대편 격자에 미친다. 
 * 5. 포탑 부서짐 (공격을 받아 공격력이 0 이하가 된 포탑은 부서진다.)
 * 6. 포탑 정비
 *   - 부서지지 않은 포탑 중 공격받지 않은 포탑은 공격력이 1씩 올라간다. 
 *   
 */

public class 포탑부수기 {

	static class Tower {
		int r, c, power, attackTime;
		boolean isBroken, isAttack;

		public Tower(int r, int c, int power, int attackTime) {
			this.r = r;
			this.c = c;
			this.power = power;
			this.attackTime = attackTime;
			this.isBroken = false;
			this.isAttack = false;
		}

		@Override
		public String toString() {
			return this.power + "";
		}
	}

	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
			public String toString() {
				// TODO Auto-generated method stub
				return ""+ r + " " + c;
			}
	}

	static int N, M, K;
	static Tower[][] map;
	static int[] dr = { 0, 1, 0, -1, 1, 1, -1, -1 }; // 우, 하, 좌, 상, 대각선
	static int[] dc = { 1, 0, -1, 0, 1, -1, -1, 1 };

	public static void main(String[] args) throws IOException {
		init();
		playGame();
//		printMap();
		System.out.println(findMaxPower());
	}

	private static int findMaxPower() {
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (map[i][j] == null || map[i][j].isBroken) {
					continue;
				}

				max = Math.max(max, map[i][j].power);
			}
		}
		
		return max;
	}

	private static void printMap() {

		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}

	}

	private static void playGame() {

		for (int i = 1; i <= K; i++) {
//			System.out.println("Turn " + i);
			Tower weakTower = selectWeakTower();
			Tower strongTower = selectStrongTower(weakTower.r, weakTower.c);
			map[weakTower.r][weakTower.c].attackTime = i;
			
//			System.out.println(weakTower.r + " " + weakTower.c + " & " + strongTower.r + " " + strongTower.c);

			if (!tryLazerAttack(weakTower.r, weakTower.c, strongTower.r, strongTower.c)) {
				bombAttack(weakTower.r, weakTower.c, strongTower.r, strongTower.c);
			}

//			printMap();
			// 부서지지 않은 포탑이 1개가 된다면 중지한다.
			if (!checkTower()) {
				break;
			}
			// 공격과 무관한 포탑은 공격력 1 증가시키고, 그렇지 않은 포탑은 상태를 false로 다시 변경해주기
			powerUp();
//			printMap();
//			System.out.println();
		}

	}

	private static void powerUp() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (map[i][j] == null || map[i][j].isBroken) {
					continue;
				}

				if (!map[i][j].isAttack) {
					map[i][j].power += 1;
				} else {
					map[i][j].isAttack = false;
				}
			}
		}

	}

	private static boolean checkTower() {

		int aliveTower = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (map[i][j] == null || map[i][j].isBroken) {
					continue;
				}

				if (map[i][j].power <= 0) {
					map[i][j].isBroken = true;
				} else {
					aliveTower++;
				}
			}
		}

		if (aliveTower <= 1) {
			return false;
		}

		return true;
	}

	private static void bombAttack(int startR, int startC, int endR, int endC) {

		// 공격자 
		map[startR][startC].isAttack = true;
		
		// 공격 대상
		map[endR][endC].isAttack = true;
		map[endR][endC].power -= map[startR][startC].power;

		// 공격 대상 주변 8방향
		for (int i = 0; i < dr.length; i++) {
			int nr = endR + dr[i];
			int nc = endC + dc[i];

			nr = (nr + N) % N;
			nc = (nc + M) % M;

			if (map[nr][nc] == null || map[nr][nc].isBroken) {
				continue;
			}
			
			if (nr == startR && nc == startC) {
				continue;
			}

			map[nr][nc].isAttack = true;
			map[nr][nc].power -= (map[startR][startC].power / 2);
		}

	}

	private static boolean tryLazerAttack(int startR, int startC, int endR, int endC) {

		Queue<Point> queue = new LinkedList();
		boolean[][] visited = new boolean[N][M];
		Point[][] visitedMap = new Point[N][M];

		queue.offer(new Point(startR, startC));
		visited[startR][startC] = true;

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			if (curr.r == endR && curr.c == endC) {
				
//				for (Point[] i : visitedMap) {
//					System.out.println(Arrays.toString(i));
//				}
				lazerAttack(startR, startC, endR, endC, visitedMap);
				return true;
			}

			for (int i = 0; i < dr.length / 2; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				
				nr = (nr + N) % N;
				nc = (nc + M) % M;


				if (map[nr][nc] == null || map[nr][nc].isBroken || visited[nr][nc]) {
					continue;
				}
				
				visitedMap[nr][nc] = new Point(curr.r, curr.c);
				queue.offer(new Point(nr, nc));
				visited[nr][nc] = true;
			}
		}
		return false;
	}

	private static void lazerAttack(int startR, int startC, int endR, int endC, Point[][] visitedMap) {

		// 공격자 
		map[startR][startC].isAttack = true;
		
		// 공격 대상
		map[endR][endC].isAttack = true;
		map[endR][endC].power -= map[startR][startC].power;
		
		Point point = visitedMap[endR][endC];
		
		while (point.r != startR || point.c != startC) {
			map[point.r][point.c].power -= (map[startR][startC].power / 2);
			map[point.r][point.c].isAttack = true;
			point = visitedMap[point.r][point.c];
		}

	}

	private static Tower selectStrongTower(int weakTowerR, int weakTowerC) {

		Tower tower = new Tower(N - 1, M - 1, 0, Integer.MAX_VALUE);

		for (int i = 0; i < M; i++) { // 열
			for (int j = 0; j < N; j++) { // 행

				if (map[j][i] == null || map[j][i].isBroken) {
					continue;
				}
				
				if (j == weakTowerR && i == weakTowerC) {
					continue;
				}

				if (tower.power < map[j][i].power) {
					tower = map[j][i];
					continue;
				}

				if (tower.power == map[j][i].power) {

					if (tower.attackTime > map[j][i].attackTime) {
						tower = map[j][i];
						continue;
					}

					if (tower.attackTime == map[j][i].attackTime) {

						if (tower.r + tower.c > map[j][i].r + map[j][i].c) {
							tower = map[j][i];
						}
					}
				}
			}
		}

		return tower;
	}

	private static Tower selectWeakTower() {

		Tower tower = new Tower(0, 0, Integer.MAX_VALUE, 0);

		for (int i = M - 1; i >= 0; i--) { // 열
			for (int j = N - 1; j >= 0; j--) { // 행

				if (map[j][i] == null || map[j][i].isBroken) {
					continue;
				}

				if (tower.power > map[j][i].power) {
					tower = map[j][i];
					continue;
				}

				if (tower.power == map[j][i].power) {
					if (tower.attackTime < map[j][i].attackTime) {
						tower = map[j][i];
					}
				}
			}
		}

		// 가장 약한 타워의 공격력 증가
		map[tower.r][tower.c].power += N + M;
		return tower;
	}

	private static void init() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Tower[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				int power = Integer.parseInt(st.nextToken());
				// map의 칸이 null이라면 이미 부서진 포탑.
				if (power > 0) {
					map[i][j] = new Tower(i, j, power, 0);
				}
			}
		}
	}
}
