package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class p14950 {
	static class Road implements Comparable<Road>{
		int start, end, cost;
		Road(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(Road o) {
			return this.cost - o.cost;
		}
	}
	static int N, M, t;
	static int[] parents;
	static ArrayList<Road> roads;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		roads = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			roads.add(new Road(start, end, cost));
		}
		
		Collections.sort(roads);
		
		int result = 0;
		int cnt = 0;
		for (Road road : roads) {
			if (union(road.start, road.end)) {
				result += road.cost + cnt * t;
				cnt++;
			}
		}
		System.out.println(result);
	}
	
	static boolean union(int start, int end) {
		int startRoot = find(start);
		int endRoot = find(end);
		// 사이클 여부 판단 
		if (startRoot != endRoot) {
			parents[startRoot] = endRoot;
			return true;
		}
		return false;
	}
	
	static int find(int v) {
		if (parents[v] == v) {
			return parents[v];
		}
		return parents[v] = find(parents[v]);
	}
}
