package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p1949 {
	static int N;
	static int[] people;
	static ArrayList<ArrayList<Integer>> graph;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		people = new int[N + 1];
		graph = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			graph.add(new ArrayList<>());
		}
	

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph.get(start).add(end);
			graph.get(end).add(start);
		}
		
		dp = new int[N + 1][2];
	}
	
	
}
