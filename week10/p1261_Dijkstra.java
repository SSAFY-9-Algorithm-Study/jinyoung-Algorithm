package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p1261_Dijkstra {
	static class Miro implements Comparable<Miro> {
		int x, y, cnt;

		public Miro(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Miro o) {
			return this.cnt - o.cnt;
		}
	}

	static int M, N, result;
	static int[][] map, dist;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		dijkstra(0, 0);
		System.out.println(result);

	}

	static void dijkstra(int x, int y) {
		PriorityQueue<Miro> pq = new PriorityQueue<>();
		visited = new boolean[N][M]; // visited 처리 안 했을 때 메모리 초과
		visited[x][y] = true;
		pq.offer(new Miro(x, y, 0));
		dist[x][y] = 0;

		while (!pq.isEmpty()) {
			Miro m = pq.poll();
			if (m.x == N - 1 && m.y == M - 1) {
				result = m.cnt;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nx = m.x + dx[i];
				int ny = m.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				
				visited[nx][ny] = true;
				if (dist[nx][ny] > dist[m.x][m.y] + map[nx][ny]) {
					dist[nx][ny] = dist[m.x][m.y] + map[nx][ny];
					pq.offer(new Miro(nx, ny, dist[nx][ny]));
				}

			}
		}

	}

}
