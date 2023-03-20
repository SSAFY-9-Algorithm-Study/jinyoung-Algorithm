package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
8 8
01000100
01010100
01010100
01010100
01010100
01010100
01010100
00010100
 */
public class p2206 {
    static class Point {
        int x, y, use, cnt;

        public Point(int x, int y, int use, int cnt) {
            this.x = x;
            this.y = y;
            this.use = use;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];
        queue.offer(new Point(0, 0, 0, 1));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == N - 1 && p.y == M - 1) {
                return p.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int use = p.use;
                int cnt = p.cnt;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (visited[nx][ny][use]) {
                    continue;
                }
                // 0일때 그냥 이동
                if (map[nx][ny] == 0) {
                    visited[nx][ny][use] = true;
                    queue.offer(new Point(nx, ny, use, cnt + 1));
                } else if (map[nx][ny] == 1 && use == 0) {
                    visited[nx][ny][use] = true; // visited[nx][ny][use+1] = true;
                    queue.offer(new Point(nx, ny, use + 1, cnt + 1));

                }
            }
        }
        return -1;
    }
}
