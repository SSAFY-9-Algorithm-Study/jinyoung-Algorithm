package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2469_사다리타기 {
	
	static int K, N, resultLine;
	static char[] startPeople, endPeople;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		startPeople = new char[K];
		endPeople = new char[K];
		map = new char[N][K - 1];
		
		for (int i = 0; i < K; i++) {
			startPeople[i] = (char) ('A' + i);
		}
		
		String input = br.readLine();
		
		for (int i = 0; i < K; i++) {
			endPeople[i] = input.charAt(i);
		}
		
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			
			for (int j = 0; j < K - 1; j++) {
				map[i][j] = input.charAt(j);
			}
			
			if (map[i][0] == '?') {
				resultLine = i;
			}
		}
		
		playLadderGame();
	}
	
	static void playLadderGame() {
		
		for (int i = 0; i < resultLine; i++) {
			
			for (int j = 0; j < K - 1; j++) {
				
				if (map[i][j] == '-') {
					char temp = startPeople[j];
					startPeople[j] = startPeople[j + 1];
					startPeople[j + 1] = temp;
				}
			}
		}
		
		for (int i = N - 1; i > resultLine; i--) {
			
			for (int j = 0; j < K - 1; j++) {
				
				if (map[i][j] == '-') {
					char temp = endPeople[j];
					endPeople[j] = endPeople[j + 1];
					endPeople[j + 1] = temp;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < K - 1; i++) {
			
			if (startPeople[i] == endPeople[i + 1] || startPeople[i + 1] == endPeople[i]) {
				sb.append('-');
				char temp = startPeople[i];
				startPeople[i + 1] = startPeople[i];
				startPeople[i] = startPeople[i + 1];

			}
			
			else if (startPeople[i] == endPeople[i]) {
				sb.append('*');
			}
			
			else {
				for (int j = 0; j < K - 1; j++) {
					System.out.print('x');
				}
				return;
			}
		}
		
		System.out.println(sb);
	}
}
