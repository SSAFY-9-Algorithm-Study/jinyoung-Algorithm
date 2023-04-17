package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p1238 {
	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static int N, M, X;
	static ArrayList<ArrayList<Node>> graph;
	static int[][] goDist;
	static int[] backDist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(start).add(new Node(end, cost));
		}
		// 목적지 X까지 가는 최소 거리를 담은 배열
		goDist = new int[N + 1][N + 1];
		// 다시 집으로 돌아가는 최소 거리를 담은 배열
		backDist = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			goDist[i] = dijkstra(i);
		}
		backDist = dijkstra(X);
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, goDist[i][X] + backDist[i]);
		}
		System.out.println(max);
	}

	static int[] dijkstra(int start) {
		boolean[] visited = new boolean[N + 1];
		// start 노드부터 end 노드까지 최소 거리를 저장할 배
		int[] dist = new int[N + 1];

		// 최소 거리 정보를 담을 배열 초기화
		for (int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		// 출발 지점의 비용은 0
		dist[start] = 0;

		// 다익스트라 알고리즘
		for (int i = 0; i < N; i++) {
			// 해당 노드의 현재 비용
			int nodeValue = Integer.MAX_VALUE;
			// 해당 노드의 인덱스
			int nodeIdx = 0;
			for (int j = 1; j <= N; j++) {
				// 해당 노드를 방문X, 현재 모든 거리비용 중 최솟값을 찾음
				if (!visited[j] && dist[j] < nodeValue) {
					nodeValue = dist[j];
					nodeIdx = j;
				}
			}
			// 최종 선택된 노드 방문 처리
			visited[nodeIdx] = true;

			// 해당 지점을 기준으로 인접 노드의 최소 거리값 갱신
			for (int j = 0; j < graph.get(nodeIdx).size(); j++) {
				// 인접 노드 선택
				Node nNode = graph.get(nodeIdx).get(j);
				// 인접 노드의 현재 최소 비용 vs 현재 선택된 노드의 값 + 현재 노드에서 인접 노드로 가는
				if (dist[nNode.idx] > dist[nodeIdx] + nNode.cost) {
					dist[nNode.idx] = dist[nodeIdx] + nNode.cost;
				}
			}

		}
		return dist;
	}
}
