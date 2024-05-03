package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P17298_오큰수 {
	
	static int N;
	static int[] numbers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		solve();
	}
	private static void solve() {
		
		Stack<Integer> stack = new Stack<>();
		int[] results = new int[N];
		
		for (int i = 0; i < N; i++) {
			
			while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
				results[stack.pop()] = numbers[i];
			}
			
			stack.add(i);
		}
		
		while (!stack.isEmpty()) {
			results[stack.pop()] = -1;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			sb.append(results[i] + " ");
		}
		
		System.out.println(sb);
	}
}
