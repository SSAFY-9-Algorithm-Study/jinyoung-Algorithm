package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p7576 {
    static int M, N, tomato;
    static int[][] box;
    static int[][] visited;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        visited = new int[N][M];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    queue.offer(new int[] { i, j });
                }
            }
        }

        bfs();
        int max = Integer.MIN_VALUE;
        boolean result = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    result = false;
                }
                if (max < visited[i][j]) {
                    max = visited[i][j];
                }
            }
        }

        if (result) {
            System.out.println(max);
        } else {
            System.out.println(-1);
        }

    }

    static void bfs() {
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (visited[nx][ny] > 0 || box[nx][ny] == -1 || box[nx][ny] == 1) {
                    continue;
                }
                queue.offer(new int[] { nx, ny });
                box[nx][ny] = 1;
                visited[nx][ny] = visited[curr[0]][curr[1]] + 1;
            }
        }
    }
}
