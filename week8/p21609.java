package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p21609 {
    static class Block implements Comparable<Block> {
        int x, y, cnt, rainbowCnt;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Block(int x, int y, int cnt, int rainbowCnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.rainbowCnt = rainbowCnt;
        }

        @Override
        public int compareTo(Block o) {
            if (this.cnt == o.cnt) {
                if (this.rainbowCnt == o.rainbowCnt) {
                    if (this.x == o.x) {
                        return this.y - o.y;
                    }
                    return this.x - o.x;
                }
                return this.rainbowCnt - o.rainbowCnt;
            }
            return this.cnt - o.cnt;
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static ArrayList<Block> blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = 0;
        blocks = new ArrayList<>();
        while (true) {
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 방문X, 검은색 블록X, 무지개 블록X
                    if (!visited[i][j] && map[i][j] > 0) {
                        initRainbowBlock();
                        bfs(i, j, map[i][j]);
                    }
                }
            }
            if (blocks.size() == 0) {
                break;
            }
            Collections.sort(blocks);
            Block b = blocks.get(blocks.size() - 1);
            removeBlocks(b.x, b.y);
            result += (b.cnt * b.cnt);
            pullGravity();
            rotate();
            pullGravity();
            blocks.clear();
        }
        System.out.println(result);

    }

    static void removeBlocks(int x, int y) {
        Queue<Block> queue = new LinkedList<>();
        queue.offer(new Block(x, y));
        visited = new boolean[N][N];
        visited[x][y] = true;
        int color = map[x][y];
        map[x][y] = -2;
        while (!queue.isEmpty()) {
            Block b = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = b.x + dx[i];
                int ny = b.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == 0 || map[nx][ny] == color) {
                    // 블록 제거
                    map[nx][ny] = -2;
                    queue.offer(new Block(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static void pullGravity() {
        for (int j = 0; j < N; j++) {
            int cnt = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] == -2) {
                    cnt++;
                } else if (map[i][j] != -1 && cnt > 0) {
                    map[i + cnt][j] = map[i][j];
                    map[i][j] = -2;
                    i = i + cnt;
                    cnt = 0;
                } else {
                    cnt = 0;
                }
            }
        }
    }

    static void rotate() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = map[j][N - i - 1];
            }
        }
        map = temp;
    }

    static void bfs(int x, int y, int color) {
        Queue<Block> queue = new LinkedList<>();
        queue.offer(new Block(x, y));
        visited[x][y] = true;
        int cnt = 1;
        int rainbowCnt = 0;

        while (!queue.isEmpty()) {
            Block b = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = b.x + dx[i];
                int ny = b.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                // 블록 색이 다르고, 무지개 블록도 아닌 경우 continue
                if (map[nx][ny] != color && map[nx][ny] != 0) {
                    continue;
                }
                // 검은색 블록 continue
                if (map[nx][ny] == -1) {
                    continue;
                }
                // 무지개 블록
                if (map[nx][ny] == 0) {
                    rainbowCnt++;
                }
                queue.offer(new Block(nx, ny));
                visited[nx][ny] = true;
                cnt++;
            }
        }
        // 그룹의 개수는 2이상
        if (cnt >= 2) {
            blocks.add(new Block(x, y, cnt, rainbowCnt));
        }

    }

    static void initRainbowBlock() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    visited[i][j] = false;
                }
            }
        }
    }
}