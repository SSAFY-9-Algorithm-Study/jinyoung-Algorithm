package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class p2667 {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j);
                if ((cnt) > 0) {
                    result++;
                    list.add(cnt);
                    cnt = 0;
                }
            }
        }
        Collections.sort(list);
        System.out.println(result);
        for (int i : list) {
            System.out.println(i);
        }

    }

    private static void dfs(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n) {
            return;
        }
        if (map[x][y] == 1 && !visited[x][y]) {
            visited[x][y] = true;
            cnt++;
            dfs(x - 1, y);
            dfs(x, y - 1);
            dfs(x + 1, y);
            dfs(x, y + 1);
        }
    }
}
