package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P4949_균형잡힌세상 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			String input = br.readLine();
			stack.clear();
			
			if (input.equals(".")) {
				break;
			}
			
			boolean flag = true;
			for (int i = 0; i < input.length(); i++) {
				
				if (input.charAt(i) == '.') {
					
					if (!stack.isEmpty()) {
						flag = false;
					} else {
						break;
					}
				}
				
				if (input.charAt(i) == '(') {
					stack.add('(');
					continue;
				}
				
				if (input.charAt(i) == '[') {
					stack.add('[');
					continue;
				}
				
				if (input.charAt(i) == ')') {
					
					if (!stack.isEmpty() && stack.peek() == '(') {
						stack.pop();
						continue;
					} else {
						flag = false;
						break;
					}
					
				}
				
				if (input.charAt(i) == ']') {
					
					if (!stack.isEmpty() && stack.peek() == '[') {
						stack.pop();
						continue;
					} else {
						flag = false;
						break;
					}
					
				}
			}
			
			if (flag) {
				sb.append("yes").append("\n");
			} else {
				sb.append("no").append("\n");
			}
			
		}
		
		System.out.println(sb);
	}
}
