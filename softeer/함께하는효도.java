package softeer;

import java.io.*;
import java.util.*;

public class 함께하는효도 {

	static int n, m, max;
	static int[][] map, friends;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		friends = new int[m][2];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			friends[i][0] = Integer.parseInt(st.nextToken()) - 1;
			friends[i][1] = Integer.parseInt(st.nextToken()) - 1;
			visited[friends[i][0]][friends[i][1]] = true;
			result += map[friends[i][0]][friends[i][1]];
		}

		dfs(friends[0][0], friends[0][1], 0, 0, 0);
		System.out.println(result + max);
	}

	static void dfs(int x, int y, int friendNum, int depth, int profit) {
		if (depth == 3) {

			if (friendNum == m - 1) {
				max = Math.max(max, profit);
				return;
			}
			dfs(friends[friendNum + 1][0], friends[friendNum + 1][1], friendNum + 1, 0, profit);
			return;
		}

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
				continue;
			}

			if (!visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny, friendNum, depth + 1, profit + map[nx][ny]);
				visited[nx][ny] = false;
			}

		}

	}
}
