package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1806 {
	
	static int N, S;
	static int[] arr, sumArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); 
		S = Integer.parseInt(st.nextToken()); 
		arr = new int[N];
		sumArr = new int[N + 1];
		sumArr[0] = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (i == 0) {
				sumArr[i + 1] = arr[i];
			} else {
				sumArr[i + 1] = sumArr[i] + arr[i];
			}
		}
		
		int left = 0;
		int right = 1;
		int min = Integer.MAX_VALUE;
		while (left <= right && right <= N) {
			int sum = sumArr[right] - sumArr[left];
			
			if (sum >= S) {
				min = Math.min(min, right - left);
				left++;
			} else {
				right++;
			}
		}
		
		if (min == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(min);
		}
	}
}
