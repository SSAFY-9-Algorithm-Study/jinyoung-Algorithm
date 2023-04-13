package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p20951 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int MOD = (int) (1e9 + 7);

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(end);
            list.get(end).add(start);
        }

        int[][] dp = new int[8][N + 1];
        for (int i = 1; i <= N; i++) {
            dp[1][i] = list.get(i).size();

        }

        for (int i = 2; i <= 7; i++) {
            for (int j = 1; j <= N; j++) {
                int tempSum = 0;
                for (int k = 0; k < list.get(j).size(); k++) {
                    int temp = list.get(j).get(k);
                    tempSum = (tempSum + dp[i - 1][temp]) % MOD;
                }
                dp[i][j] = tempSum % MOD;
            }
        }
        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = (result + dp[7][i]) % MOD;
        }
        System.out.println(result);
        for (int i = 0; i <= 7; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }
}
