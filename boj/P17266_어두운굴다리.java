package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17266_어두운굴다리 {

	static int N, M;
	static int[] bridges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		bridges = new int[M];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			bridges[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = N;

		while (left <= right) {

			int mid = (left + right) / 2;

			if (checkLight(mid)) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}

		}

		System.out.println(right + 1);
	}

	private static boolean checkLight(int mid) {

		int prev = 0;

		for (int i = 0; i < M; i++) {

			if (prev + mid >= bridges[i]) {
				prev = bridges[i] + mid;
			} else {
				return false;
			}

		}

		if (prev < N) {
			return false;
		}

		return true;
	}
}
