package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P12015_가장긴증가하는부분수열2 {

	static int N;
	static List<Integer> descList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		descList = new ArrayList<>();
		descList.add(0);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());

			if (descList.get(descList.size() - 1) < input) {
				descList.add(input);
			} else {
				int idx = findIndex(input);
				descList.set(idx, input);
			}
		}

		System.out.println(descList.size() - 1);
	}

	private static int findIndex(int input) {

		int left = 0;
		int right = descList.size() - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (descList.get(mid) < input) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return right + 1;
	}
}
