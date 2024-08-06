package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2467_용액 {

	static int N;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		int[] result = solve();
		System.out.println(result[0] + " " + result[1]);
	}

	private static int[] solve() {

		int left = 0;
		int right = N - 1;
		int flag = Math.abs(numbers[left] + numbers[right]);
		int[] result = new int[2];

		while (left < right) {
			int sum = numbers[left] + numbers[right];

			if (sum == 0) {
				return new int[] { numbers[left], numbers[right] };
			}

			if (flag >= Math.abs(sum)) {
				flag = Math.abs(sum);
				result[0] = numbers[left];
				result[1] = numbers[right];
			}

			if (sum < 0) {
				left++;
			} else {
				right--;
			}

		}

		return result;
	}
}
