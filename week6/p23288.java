package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p23288 {
    static int N, M, K, dir, x, y, B, score;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 }; // 동남서북
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dice = { 6, 3, 4, 5, 2, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dir = 0; // 처음 이동 방향 : 동쪽
        x = 0;
        y = 0;
        score = 0;
        while (K-- > 0) {
            rollDice();
        }
        System.out.println(score);
    }

    static void rollDice() {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
            if (dir == 0 || dir == 1) {
                dir += 2;
            } else {
                dir -= 2;
            }
        }
        x += dx[dir];
        y += dy[dir];
        // x = nx;
        // y = ny;
        B = map[x][y];
        changeDice();
        setDirection();
        getScore();

    }

    static void changeDice() {
        int[] temp = new int[6];
        for (int i = 0; i < 6; i++) {
            temp[i] = dice[i];
        }

        if (dir == 0) {
            dice[0] = temp[1];
            dice[1] = temp[5];
            dice[2] = temp[0];
            dice[5] = temp[2];
        } else if (dir == 1) {
            dice[0] = temp[3];
            dice[3] = temp[5];
            dice[4] = temp[0];
            dice[5] = temp[4];
        } else if (dir == 2) {
            dice[0] = temp[2];
            dice[1] = temp[0];
            dice[2] = temp[5];
            dice[5] = temp[1];
        } else if (dir == 3) {
            dice[0] = temp[4];
            dice[3] = temp[0];
            dice[4] = temp[5];
            dice[5] = temp[3];
        }
    }

    static void getScore() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int C = 0;
        queue.offer(new int[] { x, y });
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            C++;
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (!visited[nx][ny] && map[nx][ny] == B) {
                    queue.offer(new int[] { nx, ny });
                    visited[nx][ny] = true;
                }
            }
        }
        score += B * C;
    }

    static void setDirection() {
        if (dice[0] > B) {
            dir = (dir + 1) % 4;
        } else if (dice[0] < B) {
            dir = (dir + 3) % 4;
        }
    }
}
