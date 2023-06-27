package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p13549 {
	static class Location implements Comparable<Location>{
		int location, cnt;
		public Location(int location, int cnt) {
			this.location = location;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Location o) {
			return this.cnt - o.cnt;
		}
		
	}
	static int N, K, min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		min = Integer.MAX_VALUE;
		
		bfs();
		System.out.println(min);
		
	}
	static void bfs() {
		PriorityQueue<Location> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[100_001];
		
		if (N == K) {
			min = 0;
			return;
		}
		
		pq.offer(new Location(N, 0));
		visited[N] = true;
		while (!pq.isEmpty()) {
			Location l = pq.poll();
			// 특정 지점에 큐에서 더 늦게 나온 정점으로부터 도달하는 것이 더 빠를 수도 있기 때문에, 이때 방문처리 해야 함!! 
			visited[l.location] = true;
			if (l.location == K) {
				min = Math.min(min, l.cnt);
				return;
			}
			
			if (l.location > K && !visited[l.location - 1]) {
				pq.offer(new Location(l.location - 1, l.cnt + 1));
//				visited[l.location - 1] = true;
			} else {
				if (l.location * 2 < 100_001 && !visited[l.location * 2]) {
					pq.offer(new Location(l.location * 2, l.cnt));
//					visited[l.location * 2] = true;
				}
				
				if (l.location + 1 < 100_001 && !visited[l.location + 1]) {
					pq.offer(new Location(l.location + 1, l.cnt + 1));
//					visited[l.location + 1] = true;
				}
				
				if (l.location - 1 >= 0 && !visited[l.location - 1]) {
					pq.offer(new Location(l.location - 1, l.cnt + 1));
//					visited[l.location - 1] = true;
				}
			}
			
		}
	}
}
