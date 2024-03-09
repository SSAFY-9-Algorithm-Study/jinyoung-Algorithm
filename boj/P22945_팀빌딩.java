package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P22945_팀빌딩 {
	
	static int N;
	static int[] stats;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stats = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			stats[i] = Integer.parseInt(st.nextToken());
		}
		
		findMaxValue();
	}
	
	static void findMaxValue() {
		
		int left = 0;
		int right = N - 1;
		int max = 0;
		while (left < right) {
			int num = right - left - 1;
			
			max = Math.max(max, num * Math.min(stats[left], stats[right]));
			
			if (stats[left] < stats[right]) {
				left++;
			} else if (stats[left] > stats[right]){
				right--;
			} else {
				left++;
				right--;
			}
		}
		
		System.out.println(max);
	}
}
