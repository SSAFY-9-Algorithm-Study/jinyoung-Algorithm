package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p17144 {
    static class Dust {
        int x, y, amount;

        public Dust(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }

    static int R, C, T;
    static int[][] map;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static int airCleanerX;
    static Queue<Dust> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue = new LinkedList<>();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    airCleanerX = i;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            saveDust();
            spreadDust();
            workAirCleaner();
        }
        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    result += map[i][j];
                }
            }
        }
        System.out.println(result);
    }

    static void saveDust() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    queue.offer(new Dust(i, j, map[i][j]));
                }
            }
        }
    }

    static void spreadDust() {
        while (!queue.isEmpty()) {
            Dust d = queue.poll();
            int dust = d.amount / 5; // 확산되는 양
            int num = 0; // 확산된 방향의 개수
            for (int i = 0; i < 4; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                if (map[nx][ny] == -1) {
                    continue;
                }
                map[nx][ny] += dust;
                num++;
            }
            map[d.x][d.y] -= (dust * num);
        }
    }

    static void workAirCleaner() {
        int top = airCleanerX - 1;
        int bottom = airCleanerX;
        // 반시계방향 순환
        // 위 -> 아래
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        // 오른 -> 왼
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        // 아래 -> 위
        for (int i = 0; i < top; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        // 왼 -> 오른
        for (int i = C - 1; i > 1; i--) {
            map[top][i] = map[top][i - 1];
        }
        map[top][1] = 0;

        // 시계방향 순환
        // 아래 -> 위
        for (int i = bottom + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        // 오른 -> 왼
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        // 위 -> 아래
        for (int i = R - 1; i > bottom; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        // 왼 -> 오른
        for (int i = C - 1; i > 1; i--) {
            map[bottom][i] = map[bottom][i - 1];
        }
        map[bottom][1] = 0;
    }
}
