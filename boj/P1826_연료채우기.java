package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1826_연료채우기 {

	static class OilInfo {
		int start, end;

		public OilInfo(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return start + " " + end ;
		}
	}

	static int N, L, P;
	static Queue<OilInfo> pq1;
	static int[] roots;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		pq1 = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			pq1.offer(new OilInfo(a, b));
		}

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		System.out.println(bfs());
	}

	private static int bfs() {

		int answer = 0;
		Queue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());


		while (P < L) {
			
			while (!pq1.isEmpty() && pq1.peek().start <= P) {
				pq2.offer(pq1.poll().end);
			}
			
			if (pq2.isEmpty()) {
				return -1;
			}

			P += pq2.poll();
			answer++;
		}

		return answer;
	}
}
