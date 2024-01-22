package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7576 {
	
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static int[][] tomatoes;
	static Queue<Point> queue;
	static int[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		tomatoes = new int[N][M];
		queue = new ArrayDeque<>();
		visited = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tomatoes[i][j] = Integer.parseInt(st.nextToken());
				if (tomatoes[i][j] == 1) {
					queue.offer(new Point(i, j));
					visited[i][j] = 1;
				}
			}
		}
		
		bfs();
		System.out.println(check());
	}
	
	static void bfs() {
		while (!queue.isEmpty()) {
			Point curr = queue.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				
				if (visited[nx][ny] > 0 || tomatoes[nx][ny] == -1) {
					continue;
				}
				
				queue.offer(new Point(nx, ny));
				visited[nx][ny] = visited[curr.x][curr.y] + 1;
			}
		}
	}
	
	static int check() {
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tomatoes[i][j] != -1 && visited[i][j] == 0) {
					return -1;
				}
				max = Math.max(max, visited[i][j] - 1);
			}
		}
		
		return max;
	}
}
