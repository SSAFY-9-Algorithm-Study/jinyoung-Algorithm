package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p16928 {
	static int N, M, result;
	static int[] ladders;
	static boolean[] visited;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ladders = new int[101];
		visited = new boolean[101];
		numbers = new int[101];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			ladders[x] = y;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			ladders[u] = v;
		}

		bfs();
		System.out.println(numbers[100]);
	}

	static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		visited[1] = true;
		numbers[1] = 0;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (int i = 1; i < 7; i++) {
				int newNum = curr + i;
				if (newNum > 100) {
					continue;
				}
				if (visited[newNum]) {
					continue;
				}
				visited[newNum] = true;
				
				if (ladders[newNum] != 0) {
					if (visited[ladders[newNum]]) {
						continue;
					}
					queue.offer(ladders[newNum]);
					visited[ladders[newNum]] = true;
					numbers[ladders[newNum]] = numbers[curr] + 1;
					
				} else {
					queue.offer(newNum);
					numbers[newNum] = numbers[curr] + 1;
				}
				
				if (numbers[100] != 0) {
					return;
				}

			}
		}
	}
}
