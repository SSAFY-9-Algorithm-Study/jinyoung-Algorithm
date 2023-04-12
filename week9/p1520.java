package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1520 {
    static int M, N, result;
    static int[][] map, dp;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dfs(0, 0);
        System.out.println(result);
    }

    static int dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) {
            return 1;
        }
        // 이미 방문한 곳이면 저장된 값 return
        if (visited[x][y]) {
            return dp[x][y];
        }
        // 첫방문시 방문 처리
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                continue;
            }
            // map[nx][ny]부터 끝까지 도달하는 경로의 개수 더하기
            if (map[nx][ny] < map[x][y]) {
                dp[x][y] += dfs(nx, ny);
            }
        }
        return dp[x][y];
    }
}
