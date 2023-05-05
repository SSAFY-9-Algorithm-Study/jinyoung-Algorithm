package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1005 {
	static int N, K, W;
	static int[] time, indegree;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			time = new int[N + 1];
			indegree = new int[N + 1]; // 진입 차수 
			graph = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList());
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				graph.get(start).add(end);
				indegree[end]++;
			}

			W = Integer.parseInt(br.readLine());
			System.out.println(topologicalSort());
		}
	}

	static int topologicalSort() {
		Queue<Integer> queue = new LinkedList<>();
		int[] result = new int[N + 1];
		
		// 진입 차수가 0인 값을 queue에 넣어줌 
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				result[i] = time[i];
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			
			for (int i : graph.get(curr)) {
				// 이전 건물까지의 소요시간 + 현재 건물의 소요시간 
				result[i] = Math.max(result[i], result[curr] + time[i]);
				indegree[i]--;
				
				if (indegree[i] == 0) {
					queue.offer(i);
				}
			}
		}
		System.out.println(Arrays.toString(result));
		return result[W];
	}
}
