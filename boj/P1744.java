package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

public class P1744 {
	
	static int N;
	static Integer[] numbers;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new Integer[N];
		
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(numbers, Collections.reverseOrder());
		
		System.out.println(findMaxSum());
	}
	
	static int findMaxSum() {
		
		Deque<Integer> deque = new ArrayDeque<>();
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			deque.offer(numbers[i]);
		}
		
		int num1 = 0;
		int num2 = 0;
		while (!deque.isEmpty()) {
			
			if (deque.peek() >= 0) {
				num1 = deque.poll();
			} else {
				num1 = deque.pollLast();
			}
			
			if (deque.isEmpty()) {
				result += num1;
				break;
			}
			
			if (num1 >= 0) {
				num2 = deque.peek();
			} else {
				num2 = deque.peekLast();
			}
			
			if (num1 == 0 && (num2 == 0 || deque.size() % 2 == 1)) {
				deque.poll();
				continue;
			}
			
			if (num1 < 0 && num2 < 0) {
				result += num1 * deque.pollLast();
				continue;
			}
			
			if (num1 > 0 && num2 <= 0) {
				result += num1;
				continue;
			}
			
			if (num1 <= 1 || num2 <= 1) {
				result += num1;
				continue;
			}
			
			result += num1 * deque.poll();
			
		}
		
		return result;
	}
}
