package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class p1922 {
	static class Computer implements Comparable<Computer>{
		int start, end, cost;
		public Computer(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(Computer o) {
			return this.cost - o.cost;
		}
	}
	static int N, M;
	static int[] parents;
	static ArrayList<Computer> computers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		computers = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			computers.add(new Computer(start, end, cost));
		}
		// 간선 데이터 오름차순 정렬 
		Collections.sort(computers);
		
		// 연결 정보 초기
		for(int i = 1 ; i <= N ; i++) {
			parents[i] = i;
		}
		
		int sum = 0;
		for (Computer c : computers) {
			// 연결했을 때 비용 더하
			if (union(c.start, c.end)) {
				sum += c.cost;
			}
		}
		System.out.println(sum);
	}
	
	// 간선을 확인하며 현 간선이 사이클을 발생시키는 지 확인 
	static boolean union(int start, int end) {
		int startRoot = findParents(start);
		int endRoot = findParents(end);
		if (startRoot != endRoot) { //연결X
			parents[startRoot] = endRoot;
			return true;
		} 
		return false;
	}
	
	static int findParents(int v) {
		if (parents[v] == v) {
			return v;
		} else {
			return parents[v] = findParents(parents[v]);
		}
	}
}
