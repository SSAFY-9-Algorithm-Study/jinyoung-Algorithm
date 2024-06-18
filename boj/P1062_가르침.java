package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1062_가르침 {

	static String INIT_WORD = "antatica";
	static int N, K, initWordCnt, maxCnt;
	static String[] words;
	static boolean[] alphabet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		words = new String[N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			words[i] = input.substring(4, input.length() - 4);
		}


		alphabet = new boolean['z' - 'a' + 1];

		for (int i = 0; i < INIT_WORD.length(); i++) {
			int currAlphabet = INIT_WORD.charAt(i) - 'a';

			if (alphabet[currAlphabet]) {
				continue;
			}

			alphabet[currAlphabet] = true;
			initWordCnt++;
		}
		
		if (K < initWordCnt) {
			System.out.println(0);
			System.exit(0);
		}
		
//		System.out.println(Arrays.toString(words));

		K -= initWordCnt;

		dfs(0, 0);
		System.out.println(maxCnt);
	}

	private static void dfs(int depth, int start) {
		
		if (depth == K) {
			int sum = checkWord();
			
			maxCnt = Math.max(maxCnt, sum);
			return;
		}
		
		for (int i = start; i <= 'z' - 'a'; i++) {
			
			if (alphabet[i]) {
				continue;
			}
			
			alphabet[i] = true;
			dfs(depth + 1, i + 1);
			alphabet[i] = false;
		}

	}

	private static int checkWord() {
		
		int sum = 0;
		
		for (int i = 0; i < words.length; i++) {
			
			boolean flag = true;
			for (int j = 0; j < words[i].length(); j++) {
				int curr = words[i].charAt(j) - 'a';
				
				if (!alphabet[curr]) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				sum++;
			}
		}
		
		return sum;
	}
}
