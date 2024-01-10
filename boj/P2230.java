package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2230 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(numbers);
		
		int left = 0;
		int right = 0;
		int min = Integer.MAX_VALUE;
		
		while (right < numbers.length) {
			int diff = numbers[right] - numbers[left];
			
			if (diff < M) {
				right++;
			} else if (diff > M) {
				left++;
				min = Math.min(min, diff);
			} else {
				min = diff;
				break;
			}
		}
		
		System.out.println(min);
	}
}
