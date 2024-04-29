package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class P25401_카드바꾸기 {
	
	static int N, min;
	static int[] cards;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cards = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		checkASC();
		checkDESC();
		checkSame();
		System.out.println(min);
	}
	
	static void checkASC() {
		int[] checked = new int[2_000_001];
		int[] newCards = new int[N];
		newCards = cards.clone();
		
		for (int i = 0; i < N - 1; i++) {
			int diff = cards[i + 1] - cards[i];
			if (diff <= 0) {
				continue;
			}
			checked[diff]++;
		}
		
		int max = 0;
		for (int i = 1; i <= 2_000_000; i++) {
			if (max < checked[i]) {
				max = checked[i];
			}
		}
		
		for (int i = 1; i <= 2_000_000; i++) {
			
			if (max != checked[i]) {
				continue;
			}
			
			int changed = 0;
			for (int j = 0; j < N - 1; j++) {
				int diff = newCards[j + 1] - newCards[j];

				if (diff != i) {
					newCards[j + 1] = newCards[j] + i;
					changed++;
				}
			}
			
			min = Math.min(min, changed);
			newCards = cards.clone();
		}
	}
	
	static void checkDESC() {
		int[] checked = new int[2_000_001];
		int[] newCards = new int[N];
		newCards = cards.clone();
		
		for (int i = 0; i < N - 1; i++) {
			int diff = cards[i] - cards[i + 1];
			if (diff <= 0) {
				continue;
			}
			checked[diff]++;
		}
		
		int max = 0;
		for (int i = 1; i <= 2_000_000; i++) {
			if (max < checked[i]) {
				max = checked[i];
			}
		}
		
		for (int i = 1; i <= 2_000_000; i++) {
			
			if (max != checked[i]) {
				continue;
			}
			
			int changed = 0;
			for (int j = 0; j < N - 1; j++) {
				int diff = newCards[j] - newCards[j + 1];
				
				if (diff != i) {
					newCards[j + 1] = newCards[j] - i;
					changed++;
				}
			}
			
			min = Math.min(min, changed);
			newCards = cards.clone();
		}

		

	}
	
	static void checkSame() {
		int[] checked = new int[2_000_001];
		
		for (int i = 0; i < N; i++) {
			
			if (cards[i] > 0) {
				checked[cards[i]]++;
			} else {
				// 1_000_000 - (-1)
				checked[1_000_000 - cards[i]]++;
			}
		}
		
		int max = 0;
		for (int i = 0; i < checked.length; i++) {
			max = Math.max(max, checked[i]);
		}
		
		min = Math.min(min, N - max);
	}
	
	
}
