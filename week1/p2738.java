package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2738 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        // Array 1 입력
        int[][] arr1 = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(line[j]);
            }
        }

        // Array 2 입력
        int[][] arr2 = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr2[i][j] = Integer.parseInt(line[j]);
            }
        }

        // result
        int[][] resultArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                resultArr[i][j] = arr1[i][j] + arr2[i][j];
            }
        }

        // 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(resultArr[i][j] + " ");
            }
            System.out.println();
        }
    }
}