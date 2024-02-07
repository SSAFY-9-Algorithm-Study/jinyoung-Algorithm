package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1495 {
	
	static int N, S, M;
	static int[] volumes;
	static boolean[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		volumes = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			volumes[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new boolean[N + 1][M + 1];
		System.out.println(changeVolume());
		
	}
	
	static int changeVolume() {
		
		dp[0][S] = true;
		
		for (int i = 0; i < N; i++) {
			
			for (int j = 0; j <= M; j++) {
				
				if (dp[i][j] == true) {
					
					if (j - volumes[i] >= 0) {
						dp[i + 1][j - volumes[i]] = true;
					}
					
					if (j + volumes[i] <= M) {
						dp[i + 1][j + volumes[i]] = true;
					}
				}
			}
			
		}
		
		for (int i = M; i >= 0; i--) {
			if (dp[N][i] == true) return i;
		}
		
		return -1;
	}
}
