package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p17779 {
    static int N, result;
    static int[][] population;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MAX_VALUE;
        for (int x = 1; x < N; x++) {
            for (int y = 1; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 >= N || y + d2 >= N || y - d1 < 0) {
                            continue;
                        }
                        divide(x, y, d1, d2);
                    }
                }
            }
        }
        System.out.println(result);
    }

    static void divide(int x, int y, int d1, int d2) {
        int[][] map = new int[N][N];
        // 경계선
        for (int i = 0; i <= d1; i++) {
            map[x + i][y - i] = 5;
        }

        for (int i = 0; i <= d2; i++) {
            map[x + i][y + i] = 5;
        }

        for (int i = 0; i <= d2; i++) {
            map[x + d1 + i][y - d1 + i] = 5;
        }

        for (int i = 0; i <= d1; i++) {
            map[x + d2 + i][y + d2 - i] = 5;
        }

        // 경계선 안
        int[] temp = { 0, 0 };
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 5) {
                    // 행이 같으면 안을 채워줌
                    if (temp[0] == i) {
                        for (int k = j; k > temp[1]; k--) {
                            map[i][k] = 5;
                        }
                    }
                    temp = new int[] { i, j };
                }
            }
        }

        // 1번 선거구
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                map[i][j] = 1;
            }
        }

        // 2번 선거구
        for (int i = 0; i <= x + d2; i++) {
            for (int j = y; j < N; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                map[i][j] = 2;
            }
        }

        // 3번 선거구
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                map[i][j] = 3;
            }
        }

        // 4번 선거구
        for (int i = x + d2; i < N; i++) {
            for (int j = y - d1 + d2; j < N; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                map[i][j] = 4;
            }
        }

        diff(map);
    }

    static void diff(int[][] map) {
        int sum[] = new int[6];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int n = map[i][j];
                sum[n] += population[i][j];
            }
        }
        Arrays.sort(sum);
        int diff = sum[5] - sum[1];
        result = Math.min(result, diff);
    }
}
