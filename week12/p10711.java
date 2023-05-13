package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p10711 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int H, W, result;
	static String[][] map;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[][] cntMap;
	static Queue<Point> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new String[H][W];
		cntMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().split("");

		}

		queue = new LinkedList<>();
		boolean flag = true;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j].equals(".")) {
					continue;
				}
				wave(i, j);
			}
		}
		while (flag) {
			flag = findDeleteSand();
		}

		System.out.println(result);
	}

	static void wave(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
				continue;
			}
			if (map[nx][ny].equals(".")) {
				cnt++;
			}
		}

		cntMap[x][y] = cnt;
		if (cntMap[x][y] >= Integer.parseInt(map[x][y])) {
			queue.offer(new Point(x, y));
		}
	}

	static boolean findDeleteSand() {
		boolean flag = false;
		while (!queue.isEmpty()) {
			int size=queue.size();
			for(int s=0;s<size;s++) {
				Point p = queue.poll();
				if (cntMap[p.x][p.y] >= Integer.parseInt(map[p.x][p.y])) {
					map[p.x][p.y] = ".";
					flag = true;
				}
				for (int i = 0; i < 8; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if (map[nx][ny].equals(".")) {
						continue;
					}
					cntMap[nx][ny] += 1;
					if (cntMap[nx][ny] == Integer.parseInt(map[nx][ny])) {
						queue.offer(new Point(nx, ny));
					}
				}
			}
			
			result++;
		}
		
		return flag;
	}

}