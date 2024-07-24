package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class P11286_절댓값힙 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> {

			if (Math.abs(o1) == Math.abs(o2)) {
				return o1 - o2;
			}
			
			return Math.abs(o1) - Math.abs(o2);
		});

		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());

			if (input != 0) {
				pq.offer(input);
			} else {
				if (pq.isEmpty()) {
					System.out.println(0);
				} else {
					System.out.println(pq.poll());
				}
			}
		}
	}
}
