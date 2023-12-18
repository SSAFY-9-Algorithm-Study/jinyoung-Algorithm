package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_줄기세포배양 {
	static class Cell{
		int x, y, life;
		public Cell(int x, int y, int life) {
			this.x = x;
			this.y = y;
			this.life = life;
		}
	}
	static int N, M, K;
	static int[][] map, visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static Queue<Cell> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			map = new int[500][500];
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			queue = new LinkedList<>();
			visited = new int[500][500];
			for (int i = 200; i < 200 + N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 200; j < 200 + M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					queue.offer(new Cell(i, j, map[i][j]));
					visited[i][j] = map[i][j];
				}
			}
			
			bfs();

			for (int i = 0; i < 500; i++) {
				System.out.println(Arrays.toString(visited[i]));
			}
			int result = 0;
			for (int i = 0; i < 500; i++) {
				for (int j = 0; j < 500; j++) {
					// 활성화 시간 - 생명력 수치 = 번식한 시간 
					// 번식한 시간이 K보다 작거나 같아야 함.
					// 죽는 시간 = 활성화 시간 + 생명력 수치 
					if (visited[i][j] - map[i][j] <= K && visited[i][j] + map[i][j] > K) {
						result++;
					}
				}
			}
			System.out.println("#" + tc + " " + result);
			
		}
	}
	static void bfs() {
		while(!queue.isEmpty()) {
			Cell c = queue.poll();
			// ???????????
			if (visited[c.x][c.y] - c.life > K ) {
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = c.x + dx[i];
				int ny = c.y + dy[i];
				
				if (visited[nx][ny] > 0) {
					continue;
				}
				
				queue.offer(new Cell(nx, ny, c.life));
				map[nx][ny] = c.life; // map : 생명력 수치 저장 
				// visited : 활성화 시간 = 현재 세포의 활성화 시간 + 생명력 수치 
				visited[nx][ny] = visited[c.x][c.y] + c.life;
				
			}
		}
	}
}
