package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2839_설탕배달 {

	static int N, result;
	static final int[] sugars = new int[] { 5, 3 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		result = Integer.MAX_VALUE;
		solution();
		
		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(result);
	}

	static void solution() {
		
		int sum = 0;
		
		for (int i = 0; i * 5 <= 5000; i++) {
			
			sum = i * 5;
			
			if (sum == N) {
				result = Math.min(result, i);
			}
			
			for (int j = 0; j * 3 <= 5000; j++) {
				int jsum = sum + j * 3;
				
				if (jsum == N) {
					result = Math.min(result, i + j);
				}
				
				if (j * 3 > N) {
					break;
				}
			}
			
			if (i * 5 > N) {
				return;
			}
			
			sum = 0;
		}
	}
}
