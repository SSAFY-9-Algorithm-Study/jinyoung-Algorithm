package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2805_나무자르기 {

	static int N, M;
	static int[] trees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(trees);

		System.out.println(findMaxHeight());
	}

	private static int findMaxHeight() {

		int left = 0;
		int right = trees[N - 1];

		while (left <= right) {
			int mid = (left + right) / 2;
			long height = getTreeHeight(mid);

			if (height >= M) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return left - 1;
	}

	private static long getTreeHeight(int mid) {

		long sum = 0;

		for (int i = N - 1; i >= 0; i--) {

			if (trees[i] <= mid) {
				break;
			}

			if (sum >= M) {
				break;
			}

			sum += trees[i] - mid;
		}

		return sum;
	}
}
