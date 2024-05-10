package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2412_암벽등반 {

	static class Point {
		int index, count;

		Point(int index, int count) {
			this.index = index;
			this.count = count;
		}
	}

	static int n, T, Tcnt, min;
	static int[][] wall;
	static List<Integer>[] possibleList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		wall = new int[n][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			wall[i][0] = Integer.parseInt(st.nextToken());
			wall[i][1] = Integer.parseInt(st.nextToken());

			if (wall[i][1] == T) {
				Tcnt++;
			}
		}

		Arrays.sort(wall, (o1, o2) -> {
			if (o1[1] == o2[1]) {
				return o2[0] - o1[0];
			}
			return o2[1] - o1[1];
		});

		possibleList = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			possibleList[i] = new ArrayList<>();
		}
		
		isPossibleWall();

		min = n;
		bfs();
		
		if (min == n) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(min);
	}

	static void isPossibleWall() {

		for (int i = 0; i < n; i++) {

			for (int j = i + 1; j < n; j++) {

				if (Math.abs(wall[i][1] - wall[j][1]) > 2) {
					break;
				}

				if (Math.abs(wall[i][0] - wall[j][0]) > 2) {
					continue;
				}
				possibleList[i].add(j);
				possibleList[j].add(i);
			}

			// 시작 지점에 도착할 수 있는 경우 
			if (wall[i][0] <= 2 && wall[i][1] <= 2) {
				possibleList[i].add(-1);
			}
		}
	}

	static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[n];

		for (int i = 0; i < Tcnt; i++) {
			queue.offer(new Point(i, 1));
			visited[i] = true;
		}

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			int size = possibleList[curr.index].size();
			for (int i = 0; i < size; i++) {
				int next = possibleList[curr.index].get(i);
				
				if (next == -1) { // 시작 지점에 도착할 수 있다! 
					min = Math.min(min, curr.count);
//					System.out.println(curr.count);
					continue;
				}

				if (visited[next]) {
					continue;
				}
				
				queue.offer(new Point(next, curr.count + 1));
				visited[next] = true;
			}
		}
	}
}
