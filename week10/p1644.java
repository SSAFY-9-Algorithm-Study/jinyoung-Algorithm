package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class p1644 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] prime = new boolean[N + 1];

		prime[0] = prime[1] = true; // 소수 제외
		// 제곱근으로 안 했을 때 런타임 에러 (ArrayIndexOutOfBounds) 발생 !!
		for (int i = 2; i <= Math.sqrt(N); i++) {
			if (!prime[i]) {
				for (int j = i * i; j <= N; j += i) {
					prime[j] = true;
				}
			}
		}

		// 소수만 저장
		ArrayList<Integer> primeList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (!prime[i]) {
				primeList.add(i);
			}
		}

		int start = 0;
		int end = 0;
		int sum = 0;
		int cnt = 0;
		while (start < primeList.size()) {
			if (sum == N) {
				cnt++;
			}
			if (sum <= N && end < primeList.size()) {
				sum += primeList.get(end++);
			} else {
				sum -= primeList.get(start++);
			}
		}
		// 결과 출력 
		System.out.println(cnt);
	}
}
