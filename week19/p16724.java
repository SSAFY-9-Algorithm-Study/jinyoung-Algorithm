package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p16724 {
	static int N, M, result;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
				}
			}
		}
		System.out.println(result);
	}

	static void dfs(int x, int y) {
		visited[x][y] = true;
		
		int nx = x;
		int ny = y;
		if (map[x][y] == 'U') {
			nx += dx[0];
			ny += dy[0];
		} else if (map[x][y] == 'D') {
			nx += dx[1];
			ny += dy[1];
		} else if (map[x][y] == 'L') {
			nx += dx[2];
			ny += dy[2];
		} else if (map[x][y] == 'R') {
			nx += dx[3];
			ny += dy[3];
		}
		
		if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
			result++;
			return;
		}
		
		if (visited[nx][ny]) {
			result++;
			return;
		}
		dfs(nx, ny);
	}
}
