package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2563_색종이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[100][100];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pointX = Integer.parseInt(st.nextToken());
			int pointY = Integer.parseInt(st.nextToken());
			
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 10; y++) {
					map[pointX + x][pointY + y]++;
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] >= 1) {
					sum++;
				}
			}
		}
		
		System.out.println(sum);
	}
}
