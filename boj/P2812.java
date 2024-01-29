package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P2812 {
	
	static int N, K;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		String input = br.readLine();
		
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = input.charAt(i) - '0';
			
		}

		makeBigNumber();
	}
	
	static void makeBigNumber() {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && K > 0 && stack.peek() < arr[i]) {
				stack.pop();
				K--;
			}
			
			stack.push(arr[i]);
		}
		
		while (K > 0) {
			stack.pop();
			K--;
		}
		
		Object[] stackArr = stack.toArray();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < stackArr.length; i++) {
			sb.append(stackArr[i]);
		}
		System.out.println(sb);
		
	}


}
