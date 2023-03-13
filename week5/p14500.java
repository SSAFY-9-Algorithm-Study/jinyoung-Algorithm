package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14500 {
    static int N, M, max;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
                checkException(i, j);
            }
        }
        System.out.println(max);

    }

    // ㅜ 제외한 테트로미노
    static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            if (visited[nx][ny]) {
                continue;
            }
            visited[nx][ny] = true;
            dfs(nx, ny, depth + 1, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    static void checkException(int x, int y) {
        // ㅜ
        if (x + 1 < N && y + 2 < M) {
            int sum1 = map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x + 1][y + 1];
            max = Math.max(max, sum1);
        }
        // ㅓ
        if (x + 2 < N && y - 1 >= 0) {
            int sum2 = map[x][y] + map[x + 1][y - 1] + map[x + 1][y] + map[x + 2][y];
            max = Math.max(max, sum2);
        }
        // ㅗ
        if (x + 1 < N && y - 1 >= 0 && y + 1 < M) {
            int sum3 = map[x][y] + map[x + 1][y - 1] + map[x + 1][y] + map[x + 1][y + 1];
            max = Math.max(max, sum3);
        }
        // ㅏ
        if (x + 2 < N && y + 1 < M) {
            int sum4 = map[x][y] + map[x + 1][y] + map[x + 1][y + 1] + map[x + 2][y];
            max = Math.max(max, sum4);
        }
    }

}
