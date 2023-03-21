package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p17142 {
    static int N, M, min, virus;
    static int[][] map, visited;
    static ArrayList<int[]> list;
    static int[] arr;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static boolean chk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        arr = new int[M];
        boolean empty = false;
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    list.add(new int[] { i, j });
                } else if (map[i][j] == 0) {
                    virus++;
                    empty = true;
                }
            }
        }

        if (!empty) {
            System.out.println(0);
        } else {
            min = Integer.MAX_VALUE;
            combination(0, 0);
            if (min == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(min);
            }
        }

    }

    static void combination(int depth, int start) {
        if (depth == M) {
            Queue<int[]> queue = new LinkedList<>();
            visited = new int[N][N];
            for (int i = 0; i < arr.length; i++) {
                // 활성 바이러스의 좌표를 큐에 넣음
                int[] active = list.get(arr[i]);
                visited[active[0]][active[1]] = -1;
                queue.offer(active);
            }
            int cnt = virus;
            // bfs 실행
            bfs(queue, cnt);
            // 최소 시간 구하기
            int time = getMin();
            if (time > 0 && time < min) {
                min = time;
            }

            return;
        }

        for (int i = start; i < list.size(); i++) {
            arr[depth] = i;
            combination(depth + 1, i + 1);
        }

    }

    static void bfs(Queue<int[]> q, int cnt) {
        while (!q.isEmpty()) {
            // 바이러스 전부 퍼지면 return
            if (cnt == 0) {
                return;
            }
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (visited[nx][ny] != 0) {
                    continue;
                }
                if (map[nx][ny] == 1) { // 벽
                    visited[nx][ny] = -2;
                } else if (map[nx][ny] == 0) { // 빈 칸
                    cnt--;
                    q.add(new int[] { nx, ny });
                    if (visited[x][y] == -1) {
                        visited[nx][ny] = 1;
                    } else {
                        visited[nx][ny] = visited[x][y] + 1;
                    }
                } else if (map[nx][ny] == 2) { // 비활성 바이러스의 위치
                    q.add(new int[] { nx, ny });
                    if (visited[x][y] == -1) {
                        visited[nx][ny] = 1;
                    } else {
                        visited[nx][ny] = visited[x][y] + 1;
                    }
                }
            }
        }
    }

    static int getMin() {
        // 방문 시간 구하기
        boolean chk = false;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] > max) {
                    max = visited[i][j];
                }
                // 방문하지 않은 곳이 있을 경우 check
                if (visited[i][j] == 0 && map[i][j] == 0) {
                    chk = true;
                }
            }
        }
        if (chk) {
            return -1;
        }
        return max;
    }
}
