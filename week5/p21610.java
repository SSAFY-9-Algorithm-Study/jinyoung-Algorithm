package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p21610 {
    static int N, M;
    static int[][] map, dir;
    static boolean[][] visited;
    static ArrayList<int[]> list, list2;
    static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dir = new int[M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            dir[i][0] = Integer.parseInt(st.nextToken());
            dir[i][1] = Integer.parseInt(st.nextToken());
        }
        // 구름 저장
        list = new ArrayList<>();
        list.add(new int[] { N - 2, 0 });
        list.add(new int[] { N - 2, 1 });
        list.add(new int[] { N - 1, 0 });
        list.add(new int[] { N - 1, 1 });

        simulation();
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += map[i][j];
            }
        }
        System.out.println(result);
    }

    static void simulation() {
        for (int i = 0; i < M; i++) {
            visited = new boolean[N][N];
            moveCloud(dir[i][0], dir[i][1]);
            copyWater();
            list.clear();
            makeCloud();
        }
    }

    static void moveCloud(int d, int s) {
        // 구름이 d방향으로 s만큼 이동
        for (int[] arr : list) {
            int nx = (N + arr[0] + dx[d] * (s % N)) % N;
            int ny = (N + arr[1] + dy[d] * (s % N)) % N;
            // int nx = (((arr[0] + (dx[d] * s)) % N) + N) % N;
            // int ny = (((arr[1] + (dx[d] * s)) % N) + N) % N;
            // 이동 후 물의 양 + 1
            map[nx][ny]++;
            visited[nx][ny] = true;
            arr[0] = nx;
            arr[1] = ny;
        }
    }

    static void copyWater() {
        for (int[] arr : list) {
            int cnt = 0;
            for (int i = 2; i < dx.length; i += 2) {
                int nx = arr[0] + dx[i];
                int ny = arr[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (map[nx][ny] > 0) {
                    cnt++;
                }
            }
            map[arr[0]][arr[1]] += cnt;
        }
    }

    static void makeCloud() {
        // 구름 사라진 칸 제외 물의 양이 2 이상인 칸은 구름 생성.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] >= 2) {
                    list.add(new int[] { i, j });
                    map[i][j] -= 2;
                }
            }
        }
    }
}
