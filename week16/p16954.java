package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p16954 {
	static int N = 8;
	static int M = 8;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
	}
	
	static void bfs() {
		
	}
}
