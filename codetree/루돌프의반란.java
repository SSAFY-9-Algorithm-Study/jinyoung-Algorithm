package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 루돌프의 움직임 -> 가장 가까운 산타에게 돌진 (8방향) 
 *    - 가까운 산타가 여러명일 경우 1. r좌표가 가까운 순 2. c좌표가 가까운 순 
 *    
 * 2. 산타의 움직임 -> 루돌프에게 가까워지는 방향으로 이동 (4방향)
 *     - 1번부터 P번까지 순서대로 이동 
 *     - 움직일 수 있는 칸이 없다면 움직이지 않음
 *     - 루돌프에게 가까워질 수 있는 칸이 없다면 움직이지 않음 
 *     - 가까워질 수 있는 방향이 여러 개라면, 상우하좌 순으로 이동 
 *     
 * 3. 충돌 -> 산타와 루돌프가 같은 칸인 경우
 *     - 루돌프가 움직였을 때 충돌된 경우 
 *     	 -> 산타는 C만큼 점수를 얻음, 산타는 루돌프가 이동한 방향으로 C칸 밀려남.
 *     - 산타가 움직였을 때 충돌된 경우 
 *     	 -> 산타는 D만큼 점수를 얻음, 산타는 산타가 이동한 반대 방향으로 D칸 밀려남. 
 *     
 * 4. 상호작용 -> 충돌 후 착지한 곳에 다른 산타가 있는 경우 그 산타는 1칸 이동 (연쇄적으로)
 * 
 * 5. 기절 -> 루돌프와 충돌한 산타는 (k + 1)번째 턴까지 기절
 */

public class 루돌프의반란 {

	static class Santa {
		boolean isDead;
		int r, c, stunCount, score;

		public Santa(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, P, C, D, RR, RC;
	static int[][] map;
	static Santa[] santas;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		init();
		playGame();
		countSantaScore();
	}

	private static void countSantaScore() {

		for (int i = 1; i < santas.length; i++) {
			System.out.print(santas[i].score + " ");
		}

	}

	private static void playGame() {

		for (int i = 0; i < M; i++) {
			moveRudolph();
			moveSanta();
			checkStunSanta();

			// 모든 산타가 탈락했다면 게임 종료, 살아있는 산타에게는 1점씩 추가
			if (!checkSantaState()) {
				break;
			}
			
		}

	}

	private static void checkStunSanta() {
		// 기절 체크는 턴 끝날 때 체크 해야 함.
		// 아까는 산타 이동할 때 체크하면서 카운트 줄여주니까 틀렸다... ㅜ 
		for (int i = 1; i < santas.length; i++) {
			if (santas[i].stunCount > 0) {
				santas[i].stunCount--;
			}
		}
		
	}

	private static void moveSanta() {

		for (int i = 1; i < santas.length; i++) {

			if (santas[i].isDead) {
				continue;
			}

			if (santas[i].stunCount > 0) {
				continue;
			}

			closeWithRudolph(i);
		}

	}

	private static void closeWithRudolph(int santaIdx) {

		Santa santa = santas[santaIdx];
		int min = (int) (Math.pow(santa.r - RR, 2) + Math.pow(santa.c - RC, 2));
		int nextR = 0;
		int nextC = 0;
		int nextDir = 0;
		boolean flag = false;

		for (int i = 0; i < dr.length; i++) {
			int nr = santa.r + dr[i];
			int nc = santa.c + dc[i];

			// 맵 밖으로 나갈 수 없음
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
				continue;
			}

			// 다른 산타가 있는 곳으로 갈 수 없음
			if (map[nr][nc] > 0) {
				continue;
			}

			int distance = (int) (Math.pow(nr - RR, 2) + Math.pow(nc - RC, 2));

			// 루돌프와 더 가까워 질 수 있는 경우
			if (min > distance) {
				min = distance;
				nextR = nr;
				nextC = nc;
				nextDir = i;
				flag = true;
			}
		}

		// 이동이 가능하다!
		if (flag) {
			// 루돌프와 충돌!
			if (map[nextR][nextC] == -1) {
				collisionRudolph(santaIdx, nextR, nextC, nextDir);
			} else {
				// 이동만 처리
				map[santa.r][santa.c] = 0;
				map[nextR][nextC] = santaIdx;
				santas[santaIdx].r = nextR;
				santas[santaIdx].c = nextC;
			}
		}

	}

	private static void collisionRudolph(int santaIdx, int nextR, int nextC, int nextDir) {
		// 충돌한 산타는 D만큼 점수 획득
		santas[santaIdx].score += D;

		// 산타 기절
		santas[santaIdx].stunCount = 2;


		// 자신이 이동해온 반대 방향으로 D 칸 만큼 밀려남
		int santaR = nextR + dr[(nextDir + 2) % 4] * D;
		int santaC = nextC + dc[(nextDir + 2) % 4] * D;

		// 맵 밖으로 나가는 경우 산타 탈락
		if (santaR < 0 || santaR >= N || santaC < 0 || santaC >= N) {
			santas[santaIdx].isDead = true;
			map[santas[santaIdx].r][santas[santaIdx].c] = 0;
			return;
		}

		// 다른 산타와 상호작용
		if (map[santaR][santaC] > 0 && map[santaR][santaC] != santaIdx) {
			meetOtherSanta(map[santaR][santaC], santaR, santaC, dr[(nextDir + 2) % 4], dc[(nextDir + 2) % 4]);
		}

		// 산타 이동 (자기 자신인 경우는 건너 뛰어야 함. 이거 때매 틀렸따... )
		map[santas[santaIdx].r][santas[santaIdx].c] = 0;
		map[santaR][santaC] = santaIdx;
		santas[santaIdx].r = santaR;
		santas[santaIdx].c = santaC;
	}

	private static void moveRudolph() {

		// 가장 가까운 산타의 번호를 찾기
		int santaIdx = findCloseSanta();

		// 기존 루돌프 위치는 지워주기
		map[RR][RC] = 0;

		int rudolphDR = 0;
		int rudolphDC = 0;
		if (santas[santaIdx].r > RR) {
			rudolphDR = 1;
		} else if (santas[santaIdx].r < RR) {
			rudolphDR = -1;
		}

		if (santas[santaIdx].c > RC) {
			rudolphDC = 1;
		} else if (santas[santaIdx].c < RC) {
			rudolphDC = -1;
		}

		RR += rudolphDR;
		RC += rudolphDC;

		// 산타와 충돌
		if (map[RR][RC] > 0) {
			collisionSanta(map[RR][RC], rudolphDR, rudolphDC);
		}

		map[RR][RC] = -1;
	}

	private static void collisionSanta(int santaIdx, int dr, int dc) {
		// 충돌한 산타는 C만큼 점수 획득
		santas[santaIdx].score += C;

		// 산타 기절
		santas[santaIdx].stunCount = 2;

		// 루돌프가 이동해온 방향으로 C칸 만큼 밀려남
		int santaR = santas[santaIdx].r + dr * C;
		int santaC = santas[santaIdx].c + dc * C;

		// 맵 밖으로 나가는 경우 산타 탈락
		if (santaR < 0 || santaR >= N || santaC < 0 || santaC >= N) {
			santas[santaIdx].isDead = true;
			map[santas[santaIdx].r][santas[santaIdx].c] = 0;
			return;
		}

		// 다른 산타와 상호작용
		if (map[santaR][santaC] > 0) {
			meetOtherSanta(map[santaR][santaC], santaR, santaC, dr, dc);
		}

		// 산타 이동
		map[santas[santaIdx].r][santas[santaIdx].c] = 0;
		map[santaR][santaC] = santaIdx;
		santas[santaIdx].r = santaR;
		santas[santaIdx].c = santaC;

	}

	private static void meetOtherSanta(int santaIdx, int santaR, int santaC, int dr, int dc) {

		int nr = santaR + dr;
		int nc = santaC + dc;
		// 맵 밖으로 나가는 경우 산타 탈락
		if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
			santas[santaIdx].isDead = true;
			map[santaR][santaC] = 0;
			return;
		}

		// 다른 산타와 상호작용
		if (map[nr][nc] > 0) {
			meetOtherSanta(map[nr][nc], nr, nc, dr, dc);
		}

		map[santas[santaIdx].r][santas[santaIdx].c] = 0;
		map[nr][nc] = santaIdx;
		santas[santaIdx].r = nr;
		santas[santaIdx].c = nc;
	}

	private static int findCloseSanta() {
		int min = (int) (Math.pow(N, 2) * 2);
		int santaIdx = 0;

		for (int i = N - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {

				if (map[i][j] > 0) {
					int distance = (int) (Math.pow(i - RR, 2) + Math.pow(j - RC, 2));

					if (min > distance) {
						min = distance;
						santaIdx = map[i][j];
					}
				}
			}
		}
		return santaIdx;
	}

	private static boolean checkSantaState() {

		boolean isAlive = false;

		for (int i = 1; i < santas.length; i++) {
			if (!santas[i].isDead) {
				isAlive = true;
				santas[i].score++;
			}
		}

		return isAlive;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		st = new StringTokenizer(br.readLine());
		RR = Integer.parseInt(st.nextToken()) - 1;
		RC = Integer.parseInt(st.nextToken()) - 1;
		map[RR][RC] = -1; // 루돌프의 초기 위치

		santas = new Santa[P + 1];
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int SR = Integer.parseInt(st.nextToken()) - 1;
			int SC = Integer.parseInt(st.nextToken()) - 1;
			santas[idx] = new Santa(SR, SC);
			map[SR][SC] = idx;
		}

	}
}
