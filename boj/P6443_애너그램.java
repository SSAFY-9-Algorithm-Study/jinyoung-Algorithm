package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class P6443_애너그램 {
	
	static int N;
	static String input;
	static int[] alphabet;
	static Queue<String> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			alphabet = new int['z' - 'a' + 1];
			input = br.readLine();
			
			for (int j = 0; j < input.length(); j++) {
				int curr = input.charAt(j) - 'a';
				alphabet[curr]++; 
			}
			
			dfs(0, "");
			
			while (!pq.isEmpty()) {
				sb.append(pq.poll()).append("\n");
			}

		}
		
		System.out.println(sb);
	}
	
	static void dfs(int depth, String word) {
		
		if (depth == input.length()) {
			pq.offer(word);
			return;
		}
		
		for (int i = 0; i < ('z' - 'a' + 1); i++) {
			
			if (alphabet[i] > 0) {
				alphabet[i]--;
				dfs(depth + 1, word + (char)(i + 'a'));
				alphabet[i]++;
			}

		}
	}
}
