package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P13975_파일합치기3 {
	
	static int K;
	static Queue<Long> pq;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			
			pq = new PriorityQueue<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				Long num = Long.parseLong(st.nextToken());
				pq.offer(num);
			}
			
			System.out.println(joinFile());
		}
	}
	private static long joinFile() {
		
		long sum = 0;
		
		while (pq.size() > 1) {
			long num1 = pq.poll();
			long num2 = pq.poll();
			
			sum += num1 + num2;
			pq.offer(num1 + num2);
		}
		
		return sum;
	}
	
	
}
