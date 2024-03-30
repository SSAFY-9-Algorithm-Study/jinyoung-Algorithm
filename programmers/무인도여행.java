package programmers;

import java.util.*;

public class 무인도여행 {

	class Point {
		int x, y, sum;

		Point(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
	}

	static int N, M;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static char[][] map;
	boolean[][] visited;

	public int[] solution(String[] maps) {
		N = maps.length;
		M = maps[0].length();
		map = new char[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String line = maps[i];
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		ArrayList<Integer> sumList = new ArrayList();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] != 'X') {
					sumList.add(bfs(i, j));
				}
			}
		}

		if (sumList.size() == 0) {
			return new int[] { -1 };
		}

		Collections.sort(sumList);
		int[] answer = new int[sumList.size()];
		for (int i = 0; i < sumList.size(); i++) {
			answer[i] = sumList.get(i);
		}
		System.out.println(Arrays.toString(answer));
		return answer;
	}

	int bfs(int x, int y) {
		Queue<Point> queue = new LinkedList();
		queue.offer(new Point(x, y, map[x][y] - '0'));
		visited[x][y] = true;
		int linkedSum = map[x][y] - '0';
		while (!queue.isEmpty()) {
			Point curr = queue.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				if (visited[nx][ny] || map[nx][ny] == 'X') {
					continue;
				}
				queue.offer(new Point(nx, ny, curr.sum + map[nx][ny] - '0'));
				linkedSum += map[nx][ny] - '0';
				visited[nx][ny] = true;
			}

		}
		return linkedSum;
	}

}
