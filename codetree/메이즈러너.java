package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 움직이는 조건
 *  - 상하좌우로 움직일 수 있으며, 벽이 없는 곳으로 이동한다.
 *  - 움직일 수 있는 칸이 2개 이상이라면, 상하로 움직이는 것을 우선시한다.
 *  - 한 칸에 2명 이상의 참가자가 있을 수 있다. 
 *  
 * 2. 미로 회전
 *  - 한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형을 잡는다.
 *  - 가장 작은 크기를 갖는 정사각형이 2개 이상이라면, 
 *    좌상단 r 좌표가 작은 것이 우선되고, 그래도 같으면 c 좌표가 작은 것이 우선된다.
 *  - 선택된 정사각형은 시계방향으로 90도 회전하며, 회전된 벽은 내구도가 1씩 깎인다.
 */

public class 메이즈러너 {

	static class Person {
		int r, c, cnt;
		boolean isGone;

		public Person(int r, int c) {
			this.r = r;
			this.c = c;
			this.isGone = false;
		}
	}

	static int N, M, K, cnt, exitR, exitC;
	static int[][] map;
	static List<Integer>[][] peopleMap;
	static Person[] people;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		init();
		escapeMiro();
		printResult();
	}

	private static void printResult() {
		int sum = 0;
		for (int i = 1; i < people.length; i++) {
			sum += people[i].cnt;
		}

		System.out.println(sum);
		System.out.println((exitR + 1) + " " + (exitC + 1));
	}

	private static void escapeMiro() {

		for (int i = 0; i < K; i++) {
			checkMovePeople();
			if (checkEnd()) {
				break;
			}
			checkSquare();
			// peopleMap의 사람 좌표를 newPeopleMap으로 변경해주기
			changePeoplePoint();
		}

	}

	private static boolean checkEnd() {

		boolean flag = true;
		for (int i = 1; i < people.length; i++) {
			if (!people[i].isGone) {
				flag = false;
				return flag;
			}
		}

		return flag;
	}

	private static void checkSquare() {

		int minLen = N - 1;
		int minR = 0;
		int minC = 0;

		loop: for (int range = 1; range < N; range++) {
			for (int i = 0; i < N - range; i++) { //
				for (int j = 0; j < N - range; j++) {

					boolean exitFlag = false;
					boolean personFlag = false;

					// 사각형 범위(k) 내에 사람과 출구가 존재하는 지 확인
					for (int k = 0; k <= range; k++) {
						for (int l = 0; l <= range; l++) {
							if (peopleMap[i + k][j + l].size() > 0) {
								personFlag = true;
							}

							if (exitR == i + k && exitC == j + l) {
								exitFlag = true;
							}

							if (personFlag && exitFlag) {
								minLen = range;
								minR = i;
								minC = j;
								break loop;
							}
						}
					}

				}
			}
		}

		TurnMap(minLen + 1, minR, minC);
	}

	private static void TurnMap(int minLen, int minR, int minC) {

		int[][] newMap = new int[N][N];
		List<Integer>[][] newPeopleMap = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newPeopleMap[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < minLen; i++) {
			for (int j = 0; j < minLen; j++) {
				newMap[j + minR][minLen - i - 1 + minC] = map[i + minR][j + minC];
				newPeopleMap[j + minR][minLen - i - 1 + minC] = peopleMap[i + minR][j + minC];
			}
		}

		for (int i = 0; i < minLen; i++) {
			for (int j = 0; j < minLen; j++) {
				// 벽의 내구도 1 감소시키기
				if (newMap[i + minR][j + minC] > 0) {
					map[i + minR][j + minC] = newMap[i + minR][j + minC] - 1;
				} else if (newMap[i + minR][j + minC] == -1) {
					map[i + minR][j + minC] = newMap[i + minR][j + minC];
//					map[exitR][exitC] = 0; 이 쓰레기 같은 로직때문에...... 
					exitR = i + minR;
					exitC = j + minC;
				} else {
					map[i + minR][j + minC] = newMap[i + minR][j + minC];
				}

				peopleMap[i + minR][j + minC] = newPeopleMap[i + minR][j + minC];

			}
		}

	}

	private static void changePeoplePoint() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (peopleMap[i][j].size() > 0) {
					List<Integer> peopleList = peopleMap[i][j];

					for (int idx : peopleList) {
						people[idx].r = i;
						people[idx].c = j;
					}
				}

			}
		}

	}

	private static void checkMovePeople() {

		for (int i = 1; i < people.length; i++) {

			if (people[i].isGone) { // 이미 탈출한 경우
				continue;
			}

			if (movePeople(i)) {
				// 이동에 성공한 경우
				people[i].cnt++;

				// 탈출 성공
				if (people[i].r == exitR && people[i].c == exitC) {
					people[i].isGone = true;
					peopleMap[people[i].r][people[i].c].remove(Integer.valueOf(i));
				}
			}
		}

	}

	private static boolean movePeople(int idx) {
		Person person = people[idx];

		// 출구가 사람 위에 있는 경우
		if (person.r > exitR) {
			if (checkPointAndMove(person.r, person.c, person.r - 1, person.c, idx)) {
				return true;
			}
		}

		// 출구가 사람 아래에 있는 경우
		if (person.r < exitR) {
			// 위치를 옮기는 데 성공함
			if (checkPointAndMove(person.r, person.c, person.r + 1, person.c, idx)) {
				return true;
			}
		}

		// 출구가 사람 왼쪽에 있는 경우
		if (person.c > exitC) {
			if (checkPointAndMove(person.r, person.c, person.r, person.c - 1, idx)) {
				return true;
			}
		}

		// 출구가 사람 오른쪽에 있는 경우
		if (person.c < exitC) {
			if (checkPointAndMove(person.r, person.c, person.r, person.c + 1, idx)) {
				return true;
			}
		}

		return false;
	}

	private static boolean checkPointAndMove(int currR, int currC, int nr, int nc, int idx) {

		// 확인한 좌표가 출구이거나, 빈 칸일 경우 이동 가능
		if (map[nr][nc] <= 0) {
			// 위치 이동하기
			peopleMap[currR][currC].remove(Integer.valueOf(idx));
			peopleMap[nr][nc].add(Integer.valueOf(idx));
			people[idx].r = nr;
			people[idx].c = nc;
			return true;
		}

		return false;
	}

	private static void printMap() {

		System.out.println("map =============");
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}

		System.out.println("peopleMap ===========");
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(peopleMap[i]));
		}
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		peopleMap = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				peopleMap[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		people = new Person[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			peopleMap[r][c].add(Integer.valueOf(i));
			people[i] = new Person(r, c);
		}

		st = new StringTokenizer(br.readLine());
		exitR = Integer.parseInt(st.nextToken()) - 1;
		exitC = Integer.parseInt(st.nextToken()) - 1;
		map[exitR][exitC] = -1; // 출구

	}
}
