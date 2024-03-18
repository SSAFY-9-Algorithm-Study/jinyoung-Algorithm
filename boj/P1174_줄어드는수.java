package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P1174_줄어드는수 {
	
	static int N;
	static int[] numbers;
	static Set<Long> set;
	static final int LENGTH = 10;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		set = new HashSet<>();
		
		if (N > 1023) {
			System.out.println(-1);
			return;
		}
		
		dfs(0, 0);
		
		Long[] result = set.toArray(new Long[0]);
		Arrays.sort(result);
		
		System.out.println(result[N - 1]);
	}
	
	static void dfs(int depth, long number) {
		set.add(number);
		
		if (depth == LENGTH) {
			return;
		}
		
		dfs(depth + 1, number * 10 + numbers[depth]);
		dfs(depth + 1, number);
		
	}
}
