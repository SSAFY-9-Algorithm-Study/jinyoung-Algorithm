package week15;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] board = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
//		int[][] board = new int[][] {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
		solution.solution(board);
	}

	static class Node {
		int x, y, dir, cost;

		Node(int x, int y, int dir, int cost) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cost = cost;
		}
	}

	static int N, result;
	static int[][] visited;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;
	static Queue<Node> queue;

	public int solution(int[][] board) {
		int answer = Integer.MAX_VALUE;

		N = board.length;
		visited = new int[N][N];

		map = new int[N][N];
		map = board;
		queue = new LinkedList<>();

		if (map[1][0] != 1) {
			queue.offer(new Node(0, 0, 0, 100));
		}
		if (map[0][1] != 1) {
			queue.offer(new Node(0, 0, 1, 100));
		}
	
		bfs(0, 0);
		for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(visited[i]));
		}

		return answer;
	}

	static void bfs(int x, int y) {
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			System.out.println(curr.x + " " + curr.y);
			if (curr.x == N - 1 && curr.y == N - 1) {
				System.out.println(curr.cost);
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				if (map[nx][ny] == 1) {
					continue;
				}
				int cost = 0;
				// 방향이 같다
				if (curr.dir == i) {
					cost = curr.cost + 100;
				} else {
					cost = curr.cost + 500;
				}
				
				if (visited[nx][ny] == 0 || visited[nx][ny] >= cost) {
					visited[nx][ny] = cost;
					queue.offer(new Node(nx, ny, i, cost));
				}
			}
		}

	}

}
