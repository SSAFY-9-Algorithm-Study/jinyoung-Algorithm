package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p17822 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, T;
    static int[][] circle, xdk;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static boolean[][] visited;
    static boolean chk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        circle = new int[N][M];
        visited = new boolean[N][M];

        xdk = new int[T][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                xdk[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            chk = false;
            rotate(xdk[i][0], xdk[i][1], xdk[i][2]);
            for (int j = 0; j < N; j++) {

                for (int k = 0; k < M; k++) {
                    bfs(j, k);
                }
            }
            // 인접하면서 수가 같은 것이 없으면 실행
            if (!chk) {
                setCircle();
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] > 0) {
                    sum += circle[i][j];
                }
            }
        }
        System.out.println(sum);
    }

    static void rotate(int x, int d, int k) {
        int[][] newCircle = new int[N][M];
        // x의 배수인 원판을 d방향으로 k칸 회전시키기
        for (int i = 0; i < circle.length; i++) {
            if ((i + 1) % x == 0) {
                if (d == 0) { // 시계방향
                    for (int j = 0; j < M; j++) {
                        newCircle[i][j] = circle[i][(M - k + j) % M];
                    }
                } else { // 반시계방향
                    for (int j = 0; j < M; j++) {
                        newCircle[i][j] = circle[i][(M + k + j) % M];
                    }
                }
                circle[i] = newCircle[i];
            }
        }
    }

    static void bfs(int x, int y) {
        visited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        int removeNum = circle[x][y];
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (ny < 0) {
                    ny = M - 1;
                } else if (ny >= M) {
                    ny = 0;
                }
                if (nx < 0 || nx >= N) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                // 인접하면서 같은 수 제거
                if (circle[nx][ny] != 0 && circle[nx][ny] == removeNum) {
                    circle[nx][ny] = 0;
                    circle[p.x][p.y] = 0;
                    chk = true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }

    static void setCircle() {
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] > 0) {
                    cnt++;
                    sum += circle[i][j];
                }
            }
        }
        double avg = sum / (double) cnt;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] == 0)
                    continue;
                if (circle[i][j] > avg) {
                    circle[i][j]--;
                } else if (circle[i][j] < avg) {
                    circle[i][j]++;
                }
            }
        }
    }
}
