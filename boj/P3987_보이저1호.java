package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3987_보이저1호 {

	static int N, M, PR, PC;
	static char[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static char[] directionStr = { 'U', 'R', 'D', 'L' };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		st = new StringTokenizer(br.readLine());
		PR = Integer.parseInt(st.nextToken()) - 1;
		PC = Integer.parseInt(st.nextToken()) - 1;

		int max = 0;
		char dir = 'U';
		for (int i = 0; i < dx.length; i++) {
			int cnt = moveSignal(i);

			if (max < cnt) {
				max = cnt;
				dir = directionStr[i];
			}

			if (max == Integer.MAX_VALUE) {
				break;
			}
		}

		System.out.println(dir);
		if (max == Integer.MAX_VALUE) {
			System.out.println("Voyager");
		} else {
			System.out.println(max);
		}

	}

	private static int moveSignal(int dir) {
		int x = PR;
		int y = PC;
		int cnt = 0;
		boolean[][][] visited = new boolean[N][M][dx.length];

		while (x >= 0 && x < N && y >= 0 && y < M && map[x][y] != 'C') {

			if (visited[x][y][dir]) {
				return Integer.MAX_VALUE;
			}
			visited[x][y][dir] = true;

			if (map[x][y] == '\\' || map[x][y] == '/') {
				dir = changeDirection(dir, map[x][y]);
			}

			x += dx[dir];
			y += dy[dir];
			cnt++;
		}

		return cnt;
	}

	private static int changeDirection(int dir, char planet) {
		if (planet == '\\') {
			switch (dir) {
			case 0:
				dir = 3;
				break;
			case 1:
				dir = 2;
				break;
			case 2:
				dir = 1;
				break;
			case 3:
				dir = 0;
				break;
			}

			return dir;
		}

		if (planet == '/') {
			switch (dir) {
			case 0:
				dir = 1;
				break;
			case 1:
				dir = 0;
				break;
			case 2:
				dir = 3;
				break;
			case 3:
				dir = 2;
				break;
			}
		}

		return dir;
	}
}
