package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1316_그룹단어체커 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int result = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			if (check(input)) {
				result++;
			}
		}
		
		System.out.println(result);
	}

	static boolean check(String input) {

		for (int i = 0; i < input.length() - 1; i++) {
			char curr = input.charAt(i);
			boolean flag = true;

			for (int j = i + 1; j < input.length(); j++) {
				char next = input.charAt(j);

				if (curr == next && !flag) {
					return false;
				}

				if (curr != next) {
					flag = false;
				}
			}
		}

		return true;
	}
}
