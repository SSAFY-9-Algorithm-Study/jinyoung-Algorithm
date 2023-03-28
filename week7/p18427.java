package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p18427 {
    static int N, M, H;
    static int[][] block, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        block = new int[N + 1][M];
        dp = new int[N + 1][H + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while (st.hasMoreTokens()) {
                block[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        setBlock();
        System.out.println(dp[N][H] % 10007);
    }

    static void setBlock() {
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1; // 0�� ����� ���� �׻� 1
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= H; j++) {
                for (int k = 0; k < M; k++) {
                    if (j < block[i][k]) { // ����� ���� �� ���� ���
                        continue;
                    }
                    if (block[i][k] < 1) {
                        continue;
                    }
                    // ����� ���� �� �ִ� ���
                    dp[i][j] += dp[i - 1][j - block[i][k]] % 10007;
                }
                dp[i][j] += dp[i - 1][j] % 10007;
            }
        }
        
    }
}