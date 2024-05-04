package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class P22866_탑보기 {

	static int N;
	static int[] heights, cntArr, nearArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		heights = new int[N + 1];
		cntArr = new int[N + 1];
		nearArr = new int[N + 1];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
			nearArr[i] = -100_000;
		}

		checkLeftTop();
		checkRightTop();


		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			
			sb.append(cntArr[i]);
			
			if (cntArr[i] > 0) {
				sb.append(" " + nearArr[i]);
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void checkRightTop() {

		Stack<Integer> stack = new Stack<>();

		for (int i = N; i >= 1; i--) {

			while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
				stack.pop();
			}
			
			cntArr[i] += stack.size();
			
			if (stack.size() > 0 && stack.peek() - i < i - nearArr[i]) {
				nearArr[i] = stack.peek();
			}

			stack.push(i);
		}

	}

	private static void checkLeftTop() {

		Stack<Integer> stack = new Stack<>();

		for (int i = 1; i <= N; i++) {

			while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
				stack.pop();
			}
			
			cntArr[i] = stack.size();
			if (cntArr[i] > 0) {
				nearArr[i] = stack.peek();
			}

			stack.push(i);
		}
	}
}
