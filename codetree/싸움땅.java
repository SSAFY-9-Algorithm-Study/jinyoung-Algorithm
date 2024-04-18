package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 플레이어는 순차적으로 본인이 향하고 있는 방향대로 한 칸만큼 이동한다.
 *    - 격자를 벗어나는 경우, 반대 방향으로 이동한다. 
 * 2-1. 이동한 방향에 플레이어가 없을 경우
 *    - 총이 있다면, 플레이어는 총을 획득한다.
 *    - 이미 총을 가지고 있다면, 놓여있는 총과 플레이어의 총 중에 공격력이 더 쎈 총을 획득하고, 나머지는 해당 격자에 버린다.
 * 2-2. 이동한 방향에 플레이어가 있을 경우 (싸움) 
 *    - 해당 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합을 비교하여 더 큰 플레이어가 이긴다. 
 *    - 수치가 같다면, 초기 능력치가 높은 플레이어가 승리한다. 
 *    - 이긴 플레이어는 (각 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합)의 차이만큼을 포인트로 획득한다. 
 * 3. 싸움에서 진 플레이어 
 *    - 본인이 가지고 있는 총을 해당 격자에 내려놓고, 원래 가고 있던 방향대로 한 칸 이동한다. 
 *    -  만약 이동하려는 칸에 다른 플레이어가 있거나 격자 범위 밖인 경우에는 오른쪽으로 90도씩 회전하여 빈 칸으로 이동한다. 
 *    - 이동한 칸에 총이 있다면, 가장 공격력이 높은 총을 획득하고 나머지 총들은 해당 격자에 내려 놓습니다.
 * 4. 싸움에서 이긴 플레이어 
 *    - 승리한 칸에 떨어져 있는 총들과 원래 들고 있던 총 중 가장 공격력이 높은 총을 획득하고, 나머지 총들은 해당 격자에 내려 놓습니다.
 * 
 */

public class 싸움땅 {

	static class Player {
		int x, y, dir;
		int initPower, gunPower;
		int point;

		public Player(int x, int y, int dir, int initPower) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.initPower = initPower;
		}
	}

	static int n, m, k;
	static List[][] gunMap, playerMap;
	static Player[] players;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		init();
//		printMap();
		playGame();
		printGetPoints();
	}

	private static void printGetPoints() {
		
		for (int i = 1; i < players.length; i++) {
			System.out.print(players[i].point + " ");
		}
		
	}

	private static void playGame() {

		for (int i = 0; i < k; i++) {
//			System.out.println("TURN" + i);

			// 1번부터 n번 플레이어까지 순차적으로 진행하기
			for (int j = 1; j < players.length; j++) {
				moveStraight(j);

				int otherPlayerIdx = checkOtherPlayer(j);
				if (otherPlayerIdx > 0) { // 다른 플레이어를 만났을 경우 싸움
					fightPlayers(j, otherPlayerIdx);
				} else { // 총이 있다면, 획득한다.
					checkGun(j);
				}
			}
			
//			printGetPoints();
			
		}

	}

	private static void checkGun(int idx) {

		int x = players[idx].x;
		int y = players[idx].y;

		if (gunMap[x][y].size() == 0) {
			return;
		}

		int playerGunPower = players[idx].gunPower;
		boolean flag = false;

		for (int i = 0; i < gunMap[x][y].size(); i++) {
			int currGun = (int) gunMap[x][y].get(i);

			if (playerGunPower < currGun) {
				playerGunPower = currGun;
				flag = true;
			}
		}

		// 공격력이 더 쎈 총을 획득하고, 나머지 총들은 해당 격자에 놓는다.
		
		if (flag) {
			gunMap[x][y].remove(Integer.valueOf(playerGunPower));
			if (players[idx].gunPower > 0) {
				gunMap[x][y].add(players[idx].gunPower);
			}
			players[idx].gunPower = playerGunPower;
		}
	}

	private static void fightPlayers(int idx, int otherPlayerIdx) {

		int currPlayerPower = players[idx].initPower + players[idx].gunPower;
		int otherPlayerPower = players[otherPlayerIdx].initPower + players[otherPlayerIdx].gunPower;

		int winPlayer = 0;
		int losePlayer = 0;

		if (currPlayerPower > otherPlayerPower) {
			winPlayer = idx;
			losePlayer = otherPlayerIdx;
		} else if (currPlayerPower < otherPlayerPower) {
			winPlayer = otherPlayerIdx;
			losePlayer = idx;
		} else { // 능력치가 같은 경우

			if (players[idx].initPower > players[otherPlayerIdx].initPower) {
				winPlayer = idx;
				losePlayer = otherPlayerIdx;
			} else {
				winPlayer = otherPlayerIdx;
				losePlayer = idx;
			}
		}

		// 이긴 플레이어 포인트 획득
		int point = Math.abs(currPlayerPower - otherPlayerPower);
		players[winPlayer].point += point;

		losePlayerAct(losePlayer);
		checkGun(winPlayer);

	}

	private static void losePlayerAct(int losePlayer) {

		int x = players[losePlayer].x;
		int y = players[losePlayer].y;

		// 총을 가지고 있었다면, 해당 격자에 총을 내려놓는다.
		if (players[losePlayer].gunPower > 0) {
			gunMap[x][y].add(players[losePlayer].gunPower);
			players[losePlayer].gunPower = 0;
		}

		int dir = players[losePlayer].dir;

		int nx = x + dx[dir];
		int ny = y + dy[dir];

		// 맵을 벗어나거나 다른 플레이어가 있는 경우, 방향을 오른쪽으로 90도 회전한다.
		while (nx < 0 || nx >= n || ny < 0 || ny >= n || playerMap[nx][ny].size() > 0) {
			dir = (dir + 1) % 4;

			nx = x + dx[dir];
			ny = y + dy[dir];
		}

		playerMap[x][y].remove(Integer.valueOf(losePlayer));
		playerMap[nx][ny].add(losePlayer);
		players[losePlayer].x = nx;
		players[losePlayer].y = ny;
		players[losePlayer].dir = dir;

		checkGun(losePlayer);
	}

	private static int checkOtherPlayer(int idx) {

		int x = players[idx].x;
		int y = players[idx].y;

		if (playerMap[x][y].size() > 1) {
			return (int) playerMap[x][y].get(0);
		}

		return 0;
	}

	private static void moveStraight(int idx) {

		int dir = players[idx].dir;

		int nx = players[idx].x + dx[dir];
		int ny = players[idx].y + dy[dir];

		// 맵을 벗어나는 경우, 방향을 반대로 바꾼다.
		if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
			dir = (dir + 2) % 4;

			players[idx].dir = dir;
			nx = players[idx].x + dx[dir];
			ny = players[idx].y + dy[dir];
		}

		playerMap[players[idx].x][players[idx].y].remove(Integer.valueOf(idx));
		playerMap[nx][ny].add(idx);
		players[idx].x = nx;
		players[idx].y = ny;
	}

	private static void printMap() {

		System.out.println("gunMap=========================");
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(gunMap[i]));
		}

		System.out.println("playerMap=========================");
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(playerMap[i]));
		}

	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		gunMap = new ArrayList[n][n];
		playerMap = new ArrayList[n][n];
		players = new Player[m + 1];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				gunMap[i][j] = new ArrayList<Integer>();
				playerMap[i][j] = new ArrayList<Integer>();
			}
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				int gunPower = Integer.parseInt(st.nextToken());
				if (gunPower == 0) {
					continue;
				}
				gunMap[i][j].add(gunPower);
			}
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			players[i] = new Player(x, y, d, s);
			playerMap[x][y].add(i);
		}

	}
}
