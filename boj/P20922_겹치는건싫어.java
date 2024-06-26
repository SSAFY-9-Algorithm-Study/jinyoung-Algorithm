package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P20922_겹치는건싫어 {

	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(findMaxLength());
	}

	private static int findMaxLength() {

		int[] cntArr = new int[100_001];
		int max = 1;
		int left = 0;
		int right = 1;
		cntArr[arr[left]]++;

		while (left <= right && right < N) {

			if (cntArr[arr[right]] < K) {
				max = Math.max(max, right - left + 1);
				cntArr[arr[right]]++;
				right++;
			} else {
				cntArr[arr[left]]--;
				left++;
			}
		}

		return max;
	}
}
