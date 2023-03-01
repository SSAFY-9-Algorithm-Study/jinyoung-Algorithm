package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2468 {
    static int[][] region;
    static boolean[][] visited;
    static int n, maxH, cnt, h;
    static int result;
    private static int[] dx = { 0, 1, 0, -1 }; // 상우하좌
    private static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        region = new int[n][n];
        visited = new boolean[n][n];
        maxH = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                region[i][j] = Integer.parseInt(st.nextToken());
                if (region[i][j] > maxH)
                    maxH = region[i][j];
            }
        }

        int safeMax = 0;
        for (int h = 1; h <= maxH; h++) {
            int cnt = 0;
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && region[i][j] > h) {
                        cnt++;
                        dfs(i, j, h);
                    }
                }
            }
            safeMax = Math.max(safeMax, cnt);

        }
        System.out.println(safeMax);

    }

    static void dfs(int x, int y, int h) {
        visited[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }

            if (!visited[nx][ny] && region[nx][ny] > h) {
                dfs(nx, ny, h);
            }
        }
    }
}
