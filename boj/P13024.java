package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P13024 {
	
	static final int FRIEND_NUMBER = 4;
	static int N, M;
	static List<List<Integer>> friends;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		friends = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			friends.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			friends.get(start).add(end);
			friends.get(end).add(start);
		}
		
		
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			dfs(i, 0);
			visited[i] = false;
		}
		
		System.out.println(0);
		
	}
	
	static void dfs(int v, int depth) {
		if (depth == FRIEND_NUMBER) {
			System.out.println(1);
			System.exit(0);
		}
		
		for (int i = 0; i < friends.get(v).size(); i++) {
			int nextV = friends.get(v).get(i);
			
			if (visited[nextV]) {
				continue;
			}
			
			visited[nextV] = true;
			dfs(nextV, depth + 1);
			visited[nextV] = false;
		}
		
	}
}
