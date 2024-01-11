package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1300 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int left = 1;
		int right = k;
		int result = 0;
		while(left <= right) {
			int mid = (left + right) / 2;
			
			
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				cnt += Math.min(mid / i, N);
			}
			System.out.println(left + " " + right + " " + " "+ mid + " " + cnt);
			if (cnt < k) {
				left = mid + 1;
			} else {
				right = mid - 1;
				result = mid;
				System.out.println(result);
			}
			
		}
		
		System.out.println(result);
	}
}
