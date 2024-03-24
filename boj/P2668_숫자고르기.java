package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P2668_숫자고르기 {

	static int N;
	static List<Integer> result;
	static int[] numbers;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		numbers = new int[N + 1];
		visited = new boolean[N + 1];
		result = new ArrayList<>();


		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 1; i <= N; i++) {
			dfs(i, i);
		}

		Collections.sort(result);
		System.out.println(result.size());
		for (int i : result) {
			System.out.println(i);
		}
	}

	static void dfs(int start, int num) {
		
		if (numbers[num] == start) {
			result.add(start);
			return;
		}
		
		if (!visited[numbers[num]]) {
			visited[numbers[num]] = true;
			dfs(start, numbers[num]);
			visited[numbers[num]] = false;
		}

	}
}
