package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1669_멍멍이쓰다듬기 {

	static int X, Y;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		System.out.println(findMinDay());
	}

	private static long findMinDay() {

		int diff = Y - X;
		long n = 0; // long으로 선언해야 함... 

		if (diff == 0) {
			return 0;
		}

		while (n * n < diff) {
			n++;
		}

		if (n * n == diff) {
			return n * 2 - 1;
		}

		// n의 제곱으로 나누어 떨어지지 않는 경우
		n--;
		diff -= n * n;
		long answer = n * 2 - 1;

		while (diff > 0) {
			diff -= Math.min(n, diff);
			answer++;
		}

		return answer;
	}

}
