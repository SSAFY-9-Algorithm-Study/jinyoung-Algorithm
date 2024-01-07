package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2343 {
	
	static int N, M;
	static int[] lectures;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lectures = new int[N];
		
		st = new StringTokenizer(br.readLine());
		int left = 0;
		int right = 0;
		for (int i = 0; i < N; i++) {
			lectures[i] = Integer.parseInt(st.nextToken());
			right += lectures[i];
			left = Math.max(left, lectures[i]);
		}
		
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			int cnt = countBluray(mid);
			
			if (cnt > M) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		System.out.println(left);
		
	}
	
	private static int countBluray(int limit) {
		int sum = 0;
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			if (sum + lectures[i] > limit) {
				sum = 0;
				cnt++;
			}
			sum += lectures[i];
		}
		
		if (sum != 0) {
			cnt++;
		}
		
		return cnt;
	}
}
