package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p1261 {
	static class Miro implements Comparable<Miro>{
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
	static int[][] map;
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
		bfs(0, 0);
		System.out.println(result);

	}
	
	static void bfs(int x, int y) {
		// 벽을 최소로 부수기 위해 벽을 부순 개수로 오름차순 정렬! 
		PriorityQueue<Miro> pq = new PriorityQueue<>();
		visited = new boolean[N][M];
		
		pq.offer(new Miro(x, y, 0));
		visited[x][y] = true;
		
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
				if (visited[nx][ny]) {
					continue;
				}
				
				if (map[nx][ny] == 1) {
					visited[nx][ny] = true;
					pq.offer(new Miro(nx, ny, m.cnt + 1));
				} else {
					visited[nx][ny] = true;
					
					pq.offer(new Miro(nx, ny, m.cnt));
				}
			}
		}
		
		
	}


}
