package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        int[] dp = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            if (i + T[i] <= N + 1) { // 기간 내에 상담 가능한 경우
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
            // dp에 금액이 없어도, 이전 금액이 유지되어야 함
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

        }
        System.out.println(dp[N + 1]);
    }
}
