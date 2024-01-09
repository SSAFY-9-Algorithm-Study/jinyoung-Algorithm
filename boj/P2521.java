package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2521 {
	
	static int N, M;
	static int[] moneys;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		moneys = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int sum = 0;
		int max = 0;
		for (int i = 0; i < N; i++) {
			moneys[i] = Integer.parseInt(st.nextToken());
			sum += moneys[i];
			max = Math.max(max, moneys[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		if (sum <= M) {
			System.out.println(max);
			return;
		}
		
		Arrays.sort(moneys);
		
		int left = 0;
		int right = max;
		while (left <= right) {
			int mid = (left + right) / 2;
			int sumMoney = checkMoney(mid);
			
			if (sumMoney > M) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
			
			System.out.println(left + " " + right + " " + sumMoney);
		}
		
		System.out.println(right);
		
	}
	
	static int checkMoney(int price) {
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			if (moneys[i] < price) {
				sum += moneys[i];
			} else {
				sum += price * (N - i);
				break;
			}
		}
		return sum;
	}
}
