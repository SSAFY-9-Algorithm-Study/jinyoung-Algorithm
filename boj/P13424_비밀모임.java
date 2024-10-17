package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P13424_비밀모임 {

	static class Node {
		int node, cost;

		Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}

	static int N, M, K;
	static List<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			graph = new ArrayList[N + 1];

			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				graph[start].add(new Node(end, cost));
				graph[end].add(new Node(start, cost));
			}

			K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] friends = new int[K];
			for (int i = 0; i < K; i++) {
				friends[i] = Integer.parseInt(st.nextToken());
			}

			int[][] distArr = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				distArr[i] = djikstra(i);
			}

			int minNum = 0;
			int min = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {

				int sum = 0;
				for (int j = 0; j < K; j++) {
					int currFriend = friends[j];
					sum += distArr[i][currFriend];
				}

				if (min > sum) {
					min = sum;
					minNum = i;
				}
			}

			System.out.println(minNum);

		}
	}

	private static int[] djikstra(int start) {

		int[] dist = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			return o1.cost - o2.cost;
		});

		pq.offer(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			for (Node next : graph[curr.node]) {
				if (dist[next.node] > dist[curr.node] + next.cost) {
					dist[next.node] = dist[curr.node] + next.cost;
					pq.offer(new Node(next.node, dist[next.node]));
				}
			}
		}

		return dist;
	}
}
