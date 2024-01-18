package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numbers);
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N - 1;
			
			while (left < right) {
				int plusNum = numbers[left] + numbers[right];
				
				if (plusNum > numbers[i]) {
					right--;
				} else if (plusNum < numbers[i]) {
					left++;
				} else if (plusNum == numbers[i]) {
					if (left != i && right != i) {
						result++;
						break;
					} else if (right == i) {
						right--;
					} else if (left == i) {
						left++;
					}
				}
				
			}
		}
		
		System.out.println(result);
	}
}
