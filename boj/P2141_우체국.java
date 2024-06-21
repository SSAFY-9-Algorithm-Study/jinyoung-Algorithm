package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2141_우체국 {

	static int N, result;
	static int[][] towns;
	static long middle;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		towns = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());

			towns[i][0] = X;
			towns[i][1] = A;
			middle += towns[i][1];
		}

		middle = (middle + 1) / 2;

		Arrays.sort(towns, (o1, o2) -> {
			return o1[0] - o2[0];
		});

		solve();
		System.out.println(result);
	}

	private static void solve() {

		long sum = 0;
		for (int i = 0; i < N; i++) {
			sum += towns[i][1];

			if (sum >= middle) {
				result = towns[i][0];
				return;
			}
		}

	}
}
