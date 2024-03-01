package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2110_공유기설치 {
	
	static int N, C;
	static int[] houses;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		houses = new int[N];
		
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(houses);
		System.out.println(binarySearch());
	}
	
	private static int binarySearch() {
		
		int left = 1;
		int right = houses[N - 1] - houses[0];
		while (left <= right) {
			int mid = (left + right) / 2;
			int cnt = countHouse(mid);
			
			if (cnt >= C) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
//		System.out.println(left + " " + right);
		
		
		return left - 1;
	}

	private static int countHouse(int distance) {
		
		int cnt = 1;
		int lastHouse = houses[0];
		
		for (int i = 1; i < N; i++) {
			int currHouse = houses[i];
			
			if (currHouse - lastHouse >= distance) {
				cnt++;
				lastHouse = currHouse;
			}
		}
		
		return cnt;
	}
}
