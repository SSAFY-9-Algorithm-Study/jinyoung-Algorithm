package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p17352 {
	static int N;
	static ArrayList<ArrayList<Integer>> tree;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < N - 2; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			//union 
			union(start, end);
		}

		for (int i = 2; i <= N; i++) {
			int[] result = union(1, i);
			if (result.length == 2) {
				System.out.println(result[0]+ " " +result[1]);
			}
		}

	}

	static int[] union(int start, int end) {
		int startRoot = findParents(start);
		int endRoot = findParents(end);
		if (startRoot != endRoot) { // 연결X
			parents[startRoot] = endRoot;
			return new int[] {startRoot, endRoot};
		}
		return new int[] {};
	}

	static int findParents(int v) {
		if (parents[v] == v) {
			return v;
		} else {
			return parents[v] = findParents(parents[v]);
		}
	}
}
