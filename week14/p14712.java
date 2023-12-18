package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14712 {
	static int N, M, result;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		dfs(0);
		System.out.println(result);
	}

	static void dfs(int cnt) {
		int r = cnt / M;
		int c = cnt % M;

		if (cnt == N * M) {
			result++;
			return;
		}

		// 현재 자리에 놓을 수 없음
		if (r - 1 >= 0 && c - 1 >= 0 && map[r - 1][c] && map[r][c - 1] && map[r - 1][c - 1]) {
			dfs(cnt + 1);
			return;
		}
		map[r][c] = true;
		dfs(cnt + 1);
		map[r][c] = false;
		dfs(cnt + 1);

	}

}
