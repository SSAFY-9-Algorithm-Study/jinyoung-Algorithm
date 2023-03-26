package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p12865 {
    static int N, K;
    static int[] arrW, arrV; // 무게, 가치 저장할 배열
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arrW = new int[N + 1];
        arrV = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arrW[i] = Integer.parseInt(st.nextToken());
            arrV[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N + 1][K + 1];
        getMaxValue();
        System.out.println(dp[N][K]);
    }

    static void getMaxValue() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                int currW = arrW[i]; // 현재 물건의 무게
                int currV = arrV[i]; // 현재 물건의 가치

                if (currW > j) { // 현재 dp배열에 들어갈 무게보다 현재 물건의 무게가 무거우면 이전 값 넣음
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 이전 물건의 가치와
                    // 현재 물건의 가치 + 현재 물건의 무게를 뺀 무게의 가치를 비교
                    int tempV = currV + dp[i - 1][j - currW];
                    dp[i][j] = Math.max(dp[i - 1][j], tempV);
                }
            }
        }
    }
}
