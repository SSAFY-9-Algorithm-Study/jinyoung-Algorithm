package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1107_리모콘 {
	
	static final int CURRENT_CHANNAL = 100;
	static final int BUTTON_COUNT = 10;
	static int N, M, min;
	static boolean[] brokenButtons;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		brokenButtons = new boolean[BUTTON_COUNT];
		min = Math.abs(N - CURRENT_CHANNAL);
		
		if (N == CURRENT_CHANNAL) {
			System.out.println(0);
			return;
		}
		
		if (M == 0) {
			min = Math.min(min, Integer.toString(N).length());
			System.out.println(min);
			return;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < M; i++) {
			int input = Integer.parseInt(st.nextToken());
			brokenButtons[input] = true;
		}
		
		dfs(0, 0);
		System.out.println(min);
	}
	
	static void dfs(int number, int cnt) {

		if (cnt >= min) {
			return;
		}
		
		if (cnt > 0) {
			int currCnt = cnt + Math.abs(number - N);
			min = Math.min(min, currCnt);
		}
		
		if (cnt >= 6) {
			return;
		}
		
		for (int i = 0; i < BUTTON_COUNT; i++) {
			
			if (!brokenButtons[i]) {
				dfs(number * 10 + i, cnt + 1);
			}
		}
	}
}
