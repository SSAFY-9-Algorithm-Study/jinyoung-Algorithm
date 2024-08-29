package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P31848_엉성한도토리분류기 {

	static int N, Q;
	static int[] sortMachine, acorns, hole;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sortMachine = new int[N];
		hole = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		int max = 0;
		for (int i = 0; i < N; i++) {
			sortMachine[i] = Integer.parseInt(st.nextToken()) + i;
			max = Math.max(max, sortMachine[i]);
			hole[i] = max;
		}

		Q = Integer.parseInt(br.readLine());
		acorns = new int[Q];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < Q; i++) {
			acorns[i] = Integer.parseInt(st.nextToken());
			System.out.print(solve(acorns[i]) + 1 + " ");
		}

	}

	private static int solve(int acorn) {

		int left = 0;
		int right = N - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (acorn <= hole[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return right + 1;
	}
}
