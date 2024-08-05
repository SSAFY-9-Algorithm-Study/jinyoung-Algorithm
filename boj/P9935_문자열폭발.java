package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P9935_문자열폭발 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String bombWord = br.readLine();

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < input.length(); i++) {
			stack.add(input.charAt(i));

			if (stack.size() < bombWord.length()) {
				continue;
			}

			boolean flag = true;
			for (int j = 0; j < bombWord.length(); j++) {
				if (stack.get(stack.size() - 1 - j) != bombWord.charAt(bombWord.length() - 1 - j)) {
					flag = false;
					break;
				}
			}

			if (flag) {
				for (int j = 0; j < bombWord.length(); j++) {
					stack.pop();
				}
			}
		}

		if (stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuffer sb = new StringBuffer();
			
			while (!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			
			System.out.print(sb.reverse());
		}

	}
}
