package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p15683 {
    static class CCTV {
        int x, y, type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static ArrayList<CCTV> cctvs;
    static int[][] map, copyMap;
    static int[] directions;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int cctvNum, N, M;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cctvs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        directions = new int[cctvs.size()];
        cctvNum = cctvs.size();

        dfs(0);

        System.out.println(min);
    }

    // cctv가 가지는 방향에 대한 순열
    private static void dfs(int cnt) {
        if (cnt == cctvNum) {
            copyMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }
            System.out.println(Arrays.toString(directions));
            for (int i = 0; i < cctvNum; i++) {
                int d = directions[i];
                CCTV cctv = cctvs.get(i);

                observeCCTV(d, cctv);
            }

            checkMinArea();

        } else {
            for (int i = 0; i < 4; i++) {
                directions[cnt] = i;
                dfs(cnt + 1);
            }
        }
    }

    // cctv의 감시 방향이 d일 때 각 cctv의 방향 개수에 맞추어 감시
    // 0: 상, 1: 우, 2: 하, 3: 좌
    private static void observeCCTV(int d, CCTV cctv) {
        if (cctv.type == 1) {
            observeDirection(cctv, d);
        } else if (cctv.type == 2) {
            if (d == 0 || d == 2) {
                observeDirection(cctv, 0);
                observeDirection(cctv, 2);
            } else {
                observeDirection(cctv, 1);
                observeDirection(cctv, 3);
            }
        } else if (cctv.type == 3) {
            if (d == 3) {
                observeDirection(cctv, 0);
                observeDirection(cctv, 3);
            } else {
                observeDirection(cctv, d);
                observeDirection(cctv, d + 1);
            }
        } else if (cctv.type == 4) {
            if (d == 0) {
                observeDirection(cctv, 0);
                observeDirection(cctv, 1);
                observeDirection(cctv, 3);
            } else if (d == 1) {
                observeDirection(cctv, 0);
                observeDirection(cctv, 1);
                observeDirection(cctv, 2);
            } else if (d == 2) {
                observeDirection(cctv, 1);
                observeDirection(cctv, 2);
                observeDirection(cctv, 3);
            } else if (d == 3) {
                observeDirection(cctv, 2);
                observeDirection(cctv, 3);
                observeDirection(cctv, 0);
            }
        } else if (cctv.type == 5) {
            observeDirection(cctv, 0);
            observeDirection(cctv, 1);
            observeDirection(cctv, 2);
            observeDirection(cctv, 3);
        }
    }

    // cctv를 direction 방향으로 쭉 감시
    // 0: 상, 1: 우, 2: 하, 3: 좌
    private static void observeDirection(CCTV cctv, int direction) {
        int nx = cctv.x + dx[direction];
        int ny = cctv.y + dy[direction];

        while (nx >= 0 && nx < N && ny >= 0 && ny < M && copyMap[nx][ny] != 6) {
            copyMap[nx][ny] = -1;

            nx += dx[direction];
            ny += dy[direction];
        }
    }

    // 사각지대 개수
    private static void checkMinArea() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
                    count++;
                }
            }
        }
        min = Math.min(min, count);
    }
}