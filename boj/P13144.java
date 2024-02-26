package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P13144 {
	
	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve());
	}
	
	public static long solve() {
		
		Set<Integer> set = new HashSet<>();
		
		int left = 0;
		int right = 0;
		long result = 0;
		
		while (left < N) {
			
			while (right < N && !set.contains(arr[right])) {
				set.add(arr[right]);
				right++;
			}
			
			result += right - left;
			
			set.remove(arr[left]);
			left++;
		}
		
		return result;
	}
}
