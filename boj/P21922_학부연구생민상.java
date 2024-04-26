package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P21922_학부연구생민상 {
	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static int[][] map;
	static List<Point> airconList;
	static boolean[][][] visited;
	static int[] dx = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		airconList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 9) {
					airconList.add(new Point(i, j));
				}
			}
		}

		visited = new boolean[N][M][dx.length];
		workAirConditioner();
		System.out.println(countVisted());
	}

	static int countVisted() {

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j][0] || visited[i][j][1] || visited[i][j][2] || visited[i][j][3]) {
					answer++;
				}
			}
		}

		return answer;
	}

	static void workAirConditioner() {

		for (int i = 0; i < airconList.size(); i++) {
			Point curr = airconList.get(i);
			moveWind(curr.x, curr.y);
		}

	}

	static void moveWind(int x, int y) {

		for (int i = 0; i < dx.length; i++) {
			int dir = i;
			int nx = x;
			int ny = y;

			while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
				if (visited[nx][ny][dir]) {
					break;
				}
				visited[nx][ny][dir] = true;

				if (map[nx][ny] > 0 && map[nx][ny] < 9) {
					
					// 물건 1 [좌 -> 우 / 우 -> 좌]
					if (map[nx][ny] == 1 && (dir == 1 || dir == 3)) {
						dir = (dir + 2) % 4;
						nx += dx[dir];
						ny += dy[dir];
						continue;
					}

					// 물건 2 [상 -> 하 / 하 -> 상]
					if (map[nx][ny] == 2 && (dir == 0 || dir == 2)) { // 상하
						dir = (dir + 2) % 4;
						nx += dx[dir];
						ny += dy[dir];
						continue;
					}

					// 물건 3 [좌 -> 하 / 우 -> 상 / 상 -> 우 / 하 -> 좌]
					if (map[nx][ny] == 3) {
						if (dir == 1 || dir == 3) { // 좌우 
							dir -= 1;
						} else {
							dir += 1;
						}
						
						nx += dx[dir];
						ny += dy[dir];
						continue;
					}

					// 물건 4 [좌 -> 상 / 우 -> 하 / 상 -> 좌 / 하 -> 우]
					if (map[nx][ny] == 4) {
						if (dir == 1 || dir == 3) { // 좌우 
							dir = (dir + 1) % 4;
						} else {
							dir = (dir + 3) % 4;
						}
						
						nx += dx[dir];
						ny += dy[dir];
						continue;
					}
				}

				nx += dx[dir];
				ny += dy[dir];
			}
		}

	}
}
