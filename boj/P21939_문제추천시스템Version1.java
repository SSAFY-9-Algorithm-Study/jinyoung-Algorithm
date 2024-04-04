package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class P21939_문제추천시스템Version1 {

	static class Problem {
		int number, level;
		boolean isSolved;

		public Problem(int number, int level) {
			this.number = number;
			this.level = level;
		}
	}

	static Queue<Problem> difficultPQ, easyPQ;
	static Map<Integer, Integer> savedProblem;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		easyPQ = new PriorityQueue<>((o1, o2) -> {
			if (o1.level == o2.level) {
				return o1.number - o2.number;
			}
			return o1.level - o2.level;
		});

		difficultPQ = new PriorityQueue<>((o1, o2) -> {
			if (o1.level == o2.level) {
				return o2.number - o1.number;
			}
			return o2.level - o1.level;
		});

		savedProblem = new HashMap<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			easyPQ.offer(new Problem(P, L));
			difficultPQ.offer(new Problem(P, L));
			savedProblem.put(P, L);
		}

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();

			if (input.equals("recommend")) {
				int value = Integer.parseInt(st.nextToken());
				if (value == -1) {
					while (true) {
						if (savedProblem.containsKey(easyPQ.peek().number)
								&& savedProblem.get(easyPQ.peek().number) == easyPQ.peek().level) {
							sb.append(easyPQ.peek().number).append("\n");
							break;
						}
						easyPQ.poll();
					}

				} else {
					while (true) {
						if (savedProblem.containsKey(difficultPQ.peek().number)
								&& savedProblem.get(difficultPQ.peek().number) == difficultPQ.peek().level) {
							sb.append(difficultPQ.peek().number).append("\n");
							break;
						}
						difficultPQ.poll();
					}

				}
				continue;
			}

			if (input.equals("add")) {
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				easyPQ.offer(new Problem(P, L));
				difficultPQ.offer(new Problem(P, L));
				savedProblem.put(P, L);
				continue;
			}

			if (input.equals("solved")) {
				int P = Integer.parseInt(st.nextToken());
				savedProblem.remove(P);
				continue;
			}
		}

		System.out.println(sb);
	}
}
