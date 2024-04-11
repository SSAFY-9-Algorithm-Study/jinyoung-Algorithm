package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15662_톱니바퀴2 {

	static final int GEAR_NUMBER = 8;
	static int T;
	static boolean[][] gears;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		gears = new boolean[T][8];

		for (int i = 0; i < T; i++) {
			String input = br.readLine();
			for (int j = 0; j < 8; j++) {

				if (input.charAt(j) == '0') { // N극
					gears[i][j] = false;
				} else {
					gears[i][j] = true;
				}
			}
		}

		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());

			visited = new boolean[T];
			rotateGear(num, dir);
		}

		int result = 0;
		for (int i = 0; i < T; i++) {
			if (gears[i][0]) {
				result++;
			}
		}

		System.out.println(result);
	}

	private static void rotateGear(int num, int dir) {

		visited[num] = true;

		boolean currLeftGear = gears[num][GEAR_NUMBER - 2];
		boolean currRightGear = gears[num][2];

		if (num > 0) {
			boolean leftGear = gears[num - 1][2];

			if (currLeftGear != leftGear && !visited[num - 1]) {
				rotateGear(num - 1, dir * -1);
			}
		}

		if (num < T - 1) {
			boolean rightGear = gears[num + 1][GEAR_NUMBER - 2];

			if (currRightGear != rightGear && !visited[num + 1]) {
				rotateGear(num + 1, dir * -1);
			}
		}

		if (dir == -1) {
			rotateLeft(num);
		} else {
			rotateRight(num);
		}

	}

	private static void rotateLeft(int num) {

		boolean temp = gears[num][0];

		for (int i = 0; i < GEAR_NUMBER - 1; i++) {
			gears[num][i] = gears[num][i + 1];
		}

		gears[num][GEAR_NUMBER - 1] = temp;

	}

	private static void rotateRight(int num) {

		boolean temp = gears[num][GEAR_NUMBER - 1];

		for (int i = GEAR_NUMBER - 1; i > 0; i--) {
			gears[num][i] = gears[num][i - 1];
		}

		gears[num][0] = temp;
	}
}
