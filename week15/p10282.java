package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p10282 {
	static class Computer implements Comparable<Computer>{
		int idx, cost;
		Computer(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		@Override
		public int compareTo(Computer o) {
			return this.cost - o.cost;
		}
	}
	static int n, d, c;
	static ArrayList<ArrayList<Computer>> computers;
	static int[] dist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			dist = new int[n + 1];
			computers = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				computers.add(new ArrayList<>());
			}
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				// a 컴퓨터가 b 컴퓨터를 의존 
				// 컴퓨터 b가 감염되면 s초 후 컴퓨터 a도 감염됨 
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				computers.get(b).add(new Computer(a, s));
			}
			
			dijkstra(c);
			
			int totalCnt = 0; // 총 감염되는 컴퓨터 수 
			int totalTime = 0; // 마지막 컴퓨터가 감염되기까지 걸리는 시간 
			for (int i = 1; i <= n; i++) {
				if (dist[i] != Integer.MAX_VALUE) {
					totalCnt++;
					totalTime = Math.max(totalTime, dist[i]);
				}
			}
			System.out.println(totalCnt + " " + totalTime);
			
		}
	}
	
	static void dijkstra(int start) {
		boolean[] visited = new boolean[n + 1];
		for (int i = 0; i <= n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		PriorityQueue<Computer> queue = new PriorityQueue<>();
		queue.offer(new Computer(start, 0));
		
		while(!queue.isEmpty()) {
			Computer curr = queue.poll();
			if (!visited[curr.idx]) {
				visited[curr.idx] = true;
				
				// 인접 노드의 최소 거리값 갱신 
				for (Computer next : computers.get(curr.idx)) {
					// 인접 노드의 현재 거리 vs 현재 선택된 노드의 거리  + 현재 노드에서 인접 노드로 가는 거리 
					if (dist[next.idx] > dist[curr.idx] + next.cost) {
						dist[next.idx] = dist[curr.idx] + next.cost;
						queue.offer(new Computer(next.idx, dist[next.idx]));
					}
				}
			}
		}
	}
}
