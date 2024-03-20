package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1405_미친로봇 {
	
	
	static final int initialXY = 14;
	static int N;
	static double result;
	static double[] percents;
	static int[] dx = { 0, 0, 1, -1 }; // 동 서 남 북 
	static int[] dy = { 1, -1, 0, 0 };
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		percents = new double[4];
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 4; i++) {
			percents[i] = (double) (Integer.parseInt(st.nextToken()) * 0.01);
		}
		
		visited = new boolean[initialXY * 2 + 1][initialXY * 2 + 1];
		visited[initialXY][initialXY] = true;
		
		dfs(0, 1.0, 14, 14);
		System.out.println(result);
		
	}
	
	static void dfs(int depth, double percent, int x, int y) {
		
		if (depth == N) {
			result += percent;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (percents[i] == 0.0) {
				continue;
			}
			
			if (!visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(depth + 1, percent * percents[i], nx, ny);
				visited[nx][ny] = false;
			}
		}
	}
}
