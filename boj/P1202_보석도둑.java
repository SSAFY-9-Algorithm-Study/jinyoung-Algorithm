package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1202_보석도둑 {
	
	static int N, K;
	static int[][] jewels;
	static int[] bags;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		jewels = new int[N][2];
		bags = new int[K];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewels[i][0] = Integer.parseInt(st.nextToken());
			jewels[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		
		// 무게 순으로 오름차순 정렬
		Arrays.sort(jewels, (o1, o2) -> {
			return o1[0] - o2[0];
		});
		
		Arrays.sort(bags);
		
		
		Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int index = 0;
		long result = 0;
		for (int i = 0; i < K; i++) {
			
			// 무게가 가방보다 작으면 pq에 넣어주기 
			while (index < N && bags[i] >= jewels[index][0]) {
				pq.offer(jewels[index][1]);
				index++;
			}
			
			if (!pq.isEmpty()) {
				result += (long) pq.poll();
			}
		}
		
		System.out.println(result);
	}
}
