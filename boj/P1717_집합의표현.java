package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1717_집합의표현 {

	static int n, m;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int oper = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (oper == 0) {
				union(a, b);
			} else {
				
				if (findParent(a) == findParent(b)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}

		}
	}

	private static void union(int a, int b) {

		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}

		int parentA = findParent(a);
		int parentB = findParent(b);

		if (parentA != parentB) {
			parents[parentB] = parentA;
		}

	}

	private static int findParent(int v) {

		if (parents[v] == v) {
			return v;
		}

		return parents[v] = findParent(parents[v]);
	}
}
