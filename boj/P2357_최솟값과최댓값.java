package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2357_최솟값과최댓값 {

	static int N, M;
	static int[] arr, minTree, maxTree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		minTree = new int[N * 4];
		maxTree = new int[N * 4];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		initTree(1, N, 1);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			int min = getMin(1, N, 1, left, right);
			int max = getMax(1, N, 1, left, right);

			sb.append(min + " " + max + " ").append("\n");
		}

		System.out.println(sb);
	}

	private static int getMax(int start, int end, int curr, int left, int right) {

		if (start > right || end < left) {
			return Integer.MIN_VALUE;
		}

		if (start >= left && end <= right) {
			return maxTree[curr];
		}

		int mid = (start + end) / 2;
		int l = getMax(start, mid, curr * 2, left, right);
		int r = getMax(mid + 1, end, curr * 2 + 1, left, right);

		return Math.max(l, r);
	}

	private static int getMin(int start, int end, int curr, int left, int right) {

		if (start > right || end < left) {
			return Integer.MAX_VALUE;
		}

		if (start >= left && end <= right) {
			return minTree[curr];
		}

		int mid = (start + end) / 2;
		int l = getMin(start, mid, curr * 2, left, right);
		int r = getMin(mid + 1, end, curr * 2 + 1, left, right);

		return Math.min(l, r);
	}

	private static void initTree(int start, int end, int curr) {

		if (start == end) {
			minTree[curr] = arr[start];
			maxTree[curr] = arr[start];
			return;
		}

		int mid = (start + end) / 2;
		initTree(start, mid, curr * 2);
		initTree(mid + 1, end, curr * 2 + 1);

		minTree[curr] = Math.min(minTree[curr * 2], minTree[curr * 2 + 1]);
		maxTree[curr] = Math.max(maxTree[curr * 2], maxTree[curr * 2 + 1]);

	}
}
