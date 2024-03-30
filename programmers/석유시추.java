package programmers;

import java.util.*;

public class 석유시추 {

	class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	int N, M, oilNumber;
	int[][] land;
	int[][] visited;
	int[] dx = { -1, 0, 0, 1 };
	int[] dy = { 0, 1, -1, 0 };
	List<Integer> oilList;

	public int solution(int[][] land) {
		N = land.length;
		M = land[0].length;
		this.land = land;
		visited = new int[N][M];
		oilList = new ArrayList<>();
		oilList.add(0);

		int answer = 0;

		Set<Integer> oilSet = new HashSet<>();

		for (int i = 0; i < M; i++) {
			int amount = 0;
			for (int j = 0; j < N; j++) {

				if (land[j][i] == 1) {
					if (visited[j][i] == 0) {
						int oilAmount = bfs(j, i, ++oilNumber);
						oilList.add(oilAmount);
					}

					if (!oilSet.contains(visited[j][i])) {
						oilSet.add(visited[j][i]);
						amount += oilList.get(visited[j][i]);
					}
				}
			}

			answer = Math.max(answer, amount);
			oilSet.clear();
		}
		System.out.println(oilList);
		return answer;
	}

	int bfs(int x, int y, int oilNumber) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited[x][y] = oilNumber;
		int oilAmount = 1;
		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				if (visited[nx][ny] != 0) {
					continue;
				}

				if (land[nx][ny] == 1) {
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = oilNumber;
					oilAmount++;
				}

			}
		}
		return oilAmount;
	}

}
