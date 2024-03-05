package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1477_휴게소세우기 {
	
	static int N, M, L;
	static int[] locations;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		locations = new int[N + 2];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			locations[i] = Integer.parseInt(st.nextToken());
		}
		
		locations[N] = L;
		locations[N + 1] = 0;
		
		Arrays.sort(locations);
//		System.out.println(Arrays.toString(locations));
		binarySearch();
	}
	
	private static void binarySearch() {
		
		int left = 1;
		int right = L;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			int cnt = countLocation(mid);
			
			if (cnt <= M) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}

		}
		
		System.out.println(right + 1);
	}

	private static int countLocation(int distance) {
		
		int cnt = 0;
		
		for (int i = 1; i <= N + 1; i++) {
			cnt += (locations[i] - locations[i - 1] - 1) / distance;
		}
		
		return cnt;
	}
	
	
}
