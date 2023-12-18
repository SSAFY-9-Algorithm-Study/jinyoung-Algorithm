package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p1976 {
	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					// 더 작은 숫자를 부모로 갱신 
					if (i < j) {
						union(j, i);
					} else {
						union(i, j);
					}
				}
			}
		}

		String result = "YES";
		st = new StringTokenizer(br.readLine());
		int start = findParents(Integer.parseInt(st.nextToken()));
		for (int i = 0; i < M - 1; i++) {
			int curr = findParents(Integer.parseInt(st.nextToken()));
			if (start != curr) {
				result = "NO";
				break;
			}
		}

		System.out.println(result);
	}

	static void union(int start, int end) {
		int startRoot = findParents(start);
		int endRoot = findParents(end);

		if (startRoot != endRoot) {
			parents[startRoot] = endRoot;
		}
	}

	static int findParents(int v) {
		if (parents[v] == v) {
			return v;
		} else {
			return parents[v] = findParents(parents[v]);
		}
	}
}
