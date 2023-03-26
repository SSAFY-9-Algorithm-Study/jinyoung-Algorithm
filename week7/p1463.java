package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                dp[i] = 0;
            } else if (i < 3) {
                dp[i] = 1;
            } else {
                int min = Integer.MAX_VALUE;
                if (i % 3 == 0) {
                    min = Math.min(min, dp[i / 3]);
                }
                if (i % 2 == 0) {
                    min = Math.min(min, dp[i / 2]);
                }
                min = Math.min(min, dp[i - 1]);
                dp[i] = min + 1;
            }
        }
        System.out.println(dp[N]);
    }
}
