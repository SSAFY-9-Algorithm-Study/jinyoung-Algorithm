package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1916_최소비용구하기 {

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static int N, M;
	static List<Node>[] graph;
	static int[] distances;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new ArrayList[N + 1];
		distances = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[start].add(new Node(end, cost));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		System.out.println(dijkstra(start, end));
	}

	private static int dijkstra(int start, int end) {

		for (int i = 1; i <= N; i++) {
			distances[i] = Integer.MAX_VALUE;
		}

		Queue<Node> queue = new PriorityQueue<>((o1, o2) -> {
			return o1.cost - o2.cost;
		});

		boolean[] visited = new boolean[N + 1];
		
		distances[start] = 0;
		queue.offer(new Node(start, 0));

		while (!queue.isEmpty()) {
			Node curr = queue.poll();
			
			if (visited[curr.idx]) {
				continue;
			}

			visited[curr.idx] = true;
			
			for (Node nextNode : graph[curr.idx]) {
				if (distances[nextNode.idx] > distances[curr.idx] + nextNode.cost) {
					distances[nextNode.idx] = distances[curr.idx] + nextNode.cost;
					queue.offer(new Node(nextNode.idx, distances[nextNode.idx]));
				}
			}
		}

		System.out.println(Arrays.toString(distances));
		return distances[end];
	}
}
