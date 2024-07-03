package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1654_랜선자르기 {

	static int K, N;
	static int[] lines;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lines = new int[K];

		for (int i = 0; i < K; i++) {
			lines[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(lines);

		System.out.println(calcMaxLength());
	}

	private static long calcMaxLength() {

		long left = 1;
		long right = lines[K - 1];

		while (left <= right) {
			long mid = (left + right) / 2;

			int cnt = countLine(mid);
			if (cnt >= N) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}

		return left - 1;
	}

	private static int countLine(long mid) {

		int cnt = 0;

		for (int i = K - 1; i >= 0; i--) {
			cnt += lines[i] / mid;

			if (cnt >= N) {
				break;
			}
		}

		return cnt;
	}
}
