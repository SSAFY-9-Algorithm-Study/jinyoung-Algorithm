package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p20055 {
    static int N, K, result;
    static int[] belt;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        belt = new int[N * 2];
        robot = new boolean[N];

        for (int i = 0; i < N * 2; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        simulation();
        System.out.println(result);
    }

    static void simulation() {
        int cnt = 0;
        while (cnt < K) {
            result++;
            // 벨트 회전
            int end = belt[belt.length - 1];
            for (int i = N * 2 - 2; i >= 0; i--) {
                belt[i + 1] = belt[i];
            }
            belt[0] = end;

            // 로봇 이동
            for (int i = N - 2; i >= 0; i--) {
                robot[i + 1] = robot[i];
            }
            robot[0] = false;

            // 내리는 위치라면 로봇 내림
            if (robot[N - 1]) {
                robot[N - 1] = false;
            }
            // 이동하려는 칸의 내구도가 1이상이고, 로봇이 없다면 이동
            for (int i = N - 2; i >= 0; i--) {
                if (belt[i + 1] > 0 && !robot[i + 1]) {
                    if (robot[i]) {
                        robot[i + 1] = robot[i];
                        belt[i + 1]--;
                        robot[i] = false;
                    }
                }
            }
            // 내리는 위치라면 로봇 내림
            if (robot[N - 1]) {
                robot[N - 1] = false;
            }
            // 내구도 1 이상이면, 로봇 올림
            if (belt[0] > 0) {
                robot[0] = true;
                belt[0]--;
            }
            cnt = 0;
            for (int i = 0; i < 2 * N; i++) {
                if (belt[i] <= 0) {
                    cnt++;
                }
            }
        }
    }
}
