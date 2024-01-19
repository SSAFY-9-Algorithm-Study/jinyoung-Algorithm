package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3078 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		String[] friends = new String[N];
		for (int i = 0; i < N; i++) {
			friends[i] = br.readLine();
		}
		
		int[] names = new int[21];
		long result = 0;
		for (int i = 0; i <= K; i++) {
			int length = friends[i].length();
			
			names[length]++;
			result += names[length] - 1;
		}
		
		for (int i = K + 1; i < N; i++) {
			names[friends[i - K - 1].length()]--;
			
			int length = friends[i].length();
			names[length]++;
			result += names[length] - 1;
		}
		
		System.out.println(result);
		
		
		
		
	}
}
