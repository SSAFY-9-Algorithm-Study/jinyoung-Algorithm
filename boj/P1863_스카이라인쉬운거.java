package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P1863_스카이라인쉬운거 {

	static int n, answer;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			updateStack(y);
		}

//		System.out.println(stack + " " + answer);
		checkRemainStack();
		System.out.println(answer);
	}

	private static void checkRemainStack() {

		while (!stack.isEmpty()) {

			int popBuilding = stack.pop();

			if (popBuilding != 0) {
				answer++;
			}

			// stack에 들어간 건물이 같은 높이라면, 모두 pop
			while (!stack.isEmpty() && popBuilding == stack.peek()) {
				stack.pop();
			}
		}

	}

	private static void updateStack(int y) {

		if (stack.isEmpty()) {
			stack.add(y);
			return;
		}

		if (y < stack.peek()) {

			// 현재 건물의 높이가 stack에 들어간 건물의 높이보다 낮다면, pop 
			while (!stack.isEmpty() && y < stack.peek()) {
				int popBuilding = stack.pop();
				answer++;

				while (!stack.isEmpty() && popBuilding == stack.peek()) {
					popBuilding = stack.pop();
				}

			}
		}

		stack.add(y);

	}
}
