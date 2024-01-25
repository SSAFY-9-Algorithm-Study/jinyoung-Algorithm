package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13422 {
	
	static int N, M, K;
	static int[] moneys;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			moneys = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				moneys[i] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println(slide());
		}
	}
	
	static int slide() {
		
		int result = 0;
		int sum = 0;
		for (int i = 0; i < M; i++) {
			sum += moneys[i];
		}
		
		if (sum < K) result++;
		
		if (N == M) return result;
		
		for (int i = 0; i < N - 1; i++) {
			sum -= moneys[i];
			sum += moneys[(i + M) % N];
			
			if (sum < K) result++;
		}
		
		return result;
	}
}
