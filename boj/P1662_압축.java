package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1662_압축 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		Stack<String> stack = new Stack<>();
		stack.push(input.charAt(0) + "");

		int idx = 1;
		while (idx < input.length()) {

			if (input.charAt(idx) != ')') {
				stack.push(input.charAt(idx) + "");
			} else {
				int strLength = 0;
				while (!stack.peek().equals("(")) {
					String oper = stack.pop();
					if (oper.equals("+")) { // '+'가 들어간 경우 그대로 수 더해주기 
						strLength += Integer.parseInt(stack.pop());
					} else {
						strLength++;
					}
				}

				// '(' 빼기
				stack.pop();

				// 계산한 길이를 넣을 경우 '+'와 함께 넣기 
				int cnt = Integer.parseInt(stack.pop());
				strLength *= cnt;
				stack.push(strLength + "");
				stack.push("+");
			}
			
			idx++;
		}
		
		int result = 0;
		while (!stack.isEmpty()) {
			String curr = stack.pop();
			
			if (curr.equals("+")) {
				result += Integer.parseInt(stack.pop());
			} else {
				result++;
			}
		}
		
		System.out.println(result);
	}
}
