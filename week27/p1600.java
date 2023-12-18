package week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1600 {
	static class Point {
		int x, y, horse, total;

		Point(int x, int y, int horse, int total) {
			this.x = x;
			this.y = y;
			this.horse = horse;
			this.total = total;
		}
	}

	static int[] hdx = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] hdy = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int K, H, W;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = bfs(0, 0);
		System.out.println(result);
	}

	static int bfs(int x, int y) {
		boolean[][][] visited = new boolean[H][W][K + 1];
		Queue<Point> queue = new LinkedList<>();
		visited[x][y][0] = true;
		queue.offer(new Point(x, y, 0, 0));

		while (!queue.isEmpty()) {
			Point curr = queue.poll();
			
			if (curr.x == H - 1 && curr.y == W - 1) {
				return curr.total;
			}
			
			// 원숭이 
			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
					continue;
				}
				if (visited[nx][ny][curr.horse] || map[nx][ny] == 1) {
					continue;
				}
				
				visited[nx][ny][curr.horse] = true;
				queue.offer(new Point(nx, ny, curr.horse, curr.total + 1));
			}
			
			if (curr.horse >= K) continue;
			
			// 말 
			for (int i = 0; i < hdx.length; i++) {
				int nx = curr.x + hdx[i];
				int ny = curr.y + hdy[i];
				
				if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
					continue;
				}
				if (visited[nx][ny][curr.horse + 1] || map[nx][ny] == 1) {
					continue;
				}
				
				visited[nx][ny][curr.horse + 1] = true;
				queue.offer(new Point(nx, ny, curr.horse + 1, curr.total + 1));
			}
		}
		return -1;

	}
}
