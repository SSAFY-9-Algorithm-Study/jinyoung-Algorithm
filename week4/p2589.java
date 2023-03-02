package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p2589 {
    static int n, m;
    static char[][] map;
    static int[][] visited;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static int dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited = new int[n][m];
                if (map[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }
        System.out.println(dist);
    }

    static void bfs(int x, int y) {
        visited[x][y] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y });
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny] == 0 && map[nx][ny] == 'L') {
                    visited[nx][ny] = visited[curr[0]][curr[1]] + 1;
                    if (dist < visited[nx][ny]) {
                        dist = visited[nx][ny] - 1;
                    }
                    queue.add(new int[] { nx, ny });
                }
            }
        }
    }
}
