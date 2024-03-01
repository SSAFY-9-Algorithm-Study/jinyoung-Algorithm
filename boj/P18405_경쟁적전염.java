package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P18405_경쟁적전염 {

	static class Virus {
		int x, y, type;

		Virus(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

	}

	static int N, K, S, X, Y;
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static Queue<Virus> queue;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		queue = new PriorityQueue<>((o1, o2) -> o1.type - o2.type);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 0) {
					queue.offer(new Virus(i, j, map[i][j]));
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		while (S-- > 0) {
			bfs();
		}
		
		System.out.println(map[X - 1][Y - 1]);
	}

	private static void bfs() {
		Queue<Virus> queue2 = new PriorityQueue<>((o1, o2) -> o1.type - o2.type);

		while (!queue.isEmpty()) {
			Virus curr = queue.poll();
			
			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}

				if (map[nx][ny] == 0) {
					map[nx][ny] = curr.type;
					queue2.offer(new Virus(nx, ny, curr.type));
				}

			}

		}
		
		queue = queue2;
	}
}