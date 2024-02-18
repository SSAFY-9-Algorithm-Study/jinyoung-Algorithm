package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P8983 {

	static int M, N, L;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if (binarySearch(x, y)) {
				result++;
			}
		}
		
		System.out.println(result);
	}
	
	static boolean binarySearch(int x, int y) {
		
		int left = 0;
		int right = M - 1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			int distance =  Math.abs(arr[mid] - x) + y;
			
			if (distance <= L) {
				return true;
			} else {
				if (arr[mid] - x > 0) {
					right = mid - 1;
				} else if (arr[mid] - x < 0) {
					left = mid + 1;
				} else { // 사대의 위치와 동물의 x값이 같은데 사정거리 내에 들지 못함 
					return false;
				}
			}
		}
		
		return false;
	}
}
