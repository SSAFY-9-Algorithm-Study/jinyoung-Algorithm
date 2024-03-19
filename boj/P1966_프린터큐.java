package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1966_프린터큐 {
	
	static class Paper {
		int importance, turn;
		
		public Paper(int importance, int turn) {
			this.importance = importance;
			this.turn = turn;
		}
	}
	
	static Queue<Paper> queue;
	static Queue<Integer> importanceQueue;
	static int N, M;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		queue = new ArrayDeque<>();
		importanceQueue = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				int importance = Integer.parseInt(st.nextToken());
				queue.add(new Paper(importance, i));
				importanceQueue.offer(importance);
			}
			
			sb.append(findTurn()).append("\n");
			queue.clear();
			importanceQueue.clear();
		}
		
		System.out.println(sb);
	}
	
	static int findTurn() {
		
		int index = 1;
		while (!queue.isEmpty()) {
			Paper currPaper = queue.poll();
			int currImportance = importanceQueue.peek();
			
			if (currPaper.importance != currImportance) {
				queue.offer(currPaper);
				continue;
			}
			
			if (currPaper.turn == M) {
				return index;
			}
			
			importanceQueue.poll();
			index++;
			
		}
		return 0;
	}
}
