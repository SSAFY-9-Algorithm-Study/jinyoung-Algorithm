package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class P1655_가운데를말해요 {

	static Queue<Integer> leftPq, rightPq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		leftPq = new PriorityQueue<>(Collections.reverseOrder());
		rightPq = new PriorityQueue<>();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());

			if (leftPq.size() == rightPq.size()) {
				
				leftPq.offer(input);

			} else {
				rightPq.offer(input);
			}
			
			if (!leftPq.isEmpty() && !rightPq.isEmpty()) {
				
				if (leftPq.peek() > rightPq.peek()) {
					rightPq.offer(leftPq.poll());
					leftPq.offer(rightPq.poll());
				}
				
			}

			sb.append(leftPq.peek()).append("\n");
//			System.out.println(leftPq);
//			System.out.println(rightPq);

		}

		System.out.println(sb);

//		while (!leftPq.isEmpty()) {
//			System.out.print(leftPq.poll() + " ");
//
//		}
//		System.out.println();
//
//		while (!rightPq.isEmpty()) {
//			System.out.print(rightPq.poll() + " ");
//
//		}
	}
}
