package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P10828_스택 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<String> stack = new Stack<>();
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			
			if (input.equals("push")) {
				stack.push(st.nextToken());
				continue;
			}
			
			if (input.equals("pop")) {
				
				if (stack.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(stack.pop()).append("\n");
				}
				continue;
			}
			
			if (input.equals("size")) {
				sb.append(stack.size()).append("\n");
				continue;
			}
			
			if (input.equals("empty")) {
				
				if (stack.isEmpty()) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
				continue;
			}
			
			if (input.equals("top")) {
				
				if (stack.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(stack.peek()).append("\n");
				}
				continue;
			}
		}
		
		System.out.println(sb);
	}
}
