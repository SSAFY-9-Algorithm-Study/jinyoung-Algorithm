package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3020 {
	
	static int N, H;
	static int[] up, down;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		up = new int[H + 1];
		down = new int[H + 1];
		
		for (int i = 0; i < N / 2; i++) {
			down[Integer.parseInt(br.readLine())]++;
			up[Integer.parseInt(br.readLine())]++;
		}
		
		
		for (int i = H - 2; i > 0; i--) {
			down[i] += down[i + 1];
			up[i] += up[i + 1];
		}

		
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= H; i++) {
			int count = down[i] + up[H - i + 1];
			min = Math.min(count, min);
		}
		
		int minSum = 0;
		for (int i = 1; i <= H; i++) {
			if (up[i] + down[H - i + 1] == min) minSum++; 
		}
		
		System.out.println(min + " " + minSum);
		
	}
}
