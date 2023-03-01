package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2573 {
    static int[][] map;
    static int[][] iceMap;
    static boolean[][] visited;
    static int n, m;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static int cntIce;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        iceMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                }
            }
        }

        int cnt = 0;
        int year = 0;
        while (true) {
            visited = new boolean[n][m];
            cnt = 0;
            cntIce = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }

            if (cnt > 1) {
                System.out.println(year);
                break;
            }
            if (cntIce == 0) {
                System.out.println(0);
                break;
            }
            melt();
            year++;
        }

    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<int[]>();
        iceMap = new int[n][m];
        queue.offer(new int[] { x, y });
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (map[nx][ny] > 0) {
                        queue.offer(new int[] { nx, ny });
                    }
                }
            }
            cntIce++;
        }

    }

    static void melt() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                            continue;
                        }
                        if (map[nx][ny] == 0) {
                            iceMap[i][j]++;
                        }
                    }
                }

            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] - iceMap[i][j] < 0) {
                    map[i][j] = 0;
                } else {
                    map[i][j] -= iceMap[i][j];
                }
            }
        }
    }

}