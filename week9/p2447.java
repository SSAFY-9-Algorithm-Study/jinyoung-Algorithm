package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2447 {
	static int N;
	// StringBuilder 사용하지 않으면 시간초과.
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				printStar(i, j);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		
	}
	static void printStar(int x, int y) {
		while(x != 0) {
			if (x % 3 == 1 && y % 3 == 1) {
				sb.append(" ");
				return;
			}
			x /= 3;
			y /= 3;
		}
		sb.append("*");
		
	}
}