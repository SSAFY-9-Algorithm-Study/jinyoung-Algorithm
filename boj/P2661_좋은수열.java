package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2661_좋은수열 {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dfs(0, "", 0);
		
	}

	static void dfs(int depth, String number, int previousNumber) {
		if (depth == N) {
			System.out.println(number);
			System.exit(0);
			return;
		}

		for (int i = 1; i <= 3; i++) {

			if (previousNumber == i) {
				continue;
			}

			if (isBadNumber(number + i)) {
				continue;
			}

			dfs(depth + 1, number + i, i);
		}

	}

	static boolean isBadNumber(String number) {
		
		for (int i = 1; i <= number.length() / 2; i++) {
			
			String first = number.substring(number.length() - i);
			String second = number.substring(number.length() - i * 2, number.length() - i);
			
			if (first.equals(second)) {
				return true;
			}
		}

		return false;
	}
}
