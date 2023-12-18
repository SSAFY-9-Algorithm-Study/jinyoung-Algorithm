package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1707 {
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int[] color;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int K = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < K; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<>());
			}
			visited = new boolean[V + 1];
			color = new int[V + 1];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				graph.get(start).add(end);
				graph.get(end).add(start);
			}

			boolean flag = true;
			for (int i = 1; i <= V; i++) {
				if (!visited[i]) {
					flag = bfs(i);
				}
				if (!flag) {
					break;
				}
			}

			if (flag) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}
	}

	static boolean bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		color[start] = 1;
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (Integer next : graph.get(curr)) {
				// 인접 노드의 색이 동일하면 이분 그래프가 아님! 
				if (color[next] == color[curr]) {
					return false;
				}
				// 인접 노드 방문하지 않았을 때 방문처리 & 반대 색상 입력 
				if (!visited[next]) {
					visited[next] = true;
					color[next] = color[curr] * -1;
					queue.offer(next);
				}
			}
		}
		return true;
	}
}
