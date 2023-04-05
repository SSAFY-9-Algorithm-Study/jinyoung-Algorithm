package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p20056 {
    // 파이어볼의 정보 : r(x), c(y), m(질량), s(속력), d(방향)
    static class Fireball {
        int r, c, m, s, d;

        Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int N, M, K;
    static int[][] fireball;
    static ArrayList<Fireball> fireballList;
    static ArrayList<Fireball>[][] map;
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fireball = new int[M][5];
        fireballList = new ArrayList<>();
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireballList.add(new Fireball(r, c, m, s, d));
        }

        while (K-- > 0) {
            moveFireball();
            changeFireball();
        }

        int result = 0;
        for (Fireball f : fireballList) {
            result += f.m;
        }
        System.out.println(result);
    }

    static void moveFireball() {
        // 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동
        for (Fireball f : fireballList) {
            // 이동
            f.r = (f.r + dx[f.d] * f.s) % N;
            if (f.r < 0) {
                f.r = N - Math.abs(f.r);
            }
            f.c = (f.c + dy[f.d] * f.s) % N;
            if (f.c < 0) {
                f.c = N - Math.abs(f.c);
            }
            // map에 파이어볼 정보를 넣어줌
            map[f.r][f.c].add(f);
        }
    }

    static void changeFireball() {
        // 같은 칸에 있는 파이어볼 합치기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() >= 2) {
                    int sumM = 0; // 질량의 합
                    int sumS = 0; // 속력의 합
                    boolean isOdd = false; // 홀수 여부
                    boolean isEven = false; // 짝수 여부
                    for (Fireball f : map[i][j]) {
                        sumM += f.m;
                        sumS += f.s;
                        if (f.d % 2 == 0) {
                            isEven = true;
                        } else {
                            isOdd = true;
                        }
                        fireballList.remove(f);
                    }
                    int newM = sumM / 5;
                    int newS = sumS / map[i][j].size();
                    // 질량이 0인 파이어볼은 소멸됨
                    if (newM == 0) {
                        continue;
                    }

                    if (isEven != isOdd) { // 모두 짝수 or 모두 홀수
                        fireballList.add(new Fireball(i, j, newM, newS, 0));
                        fireballList.add(new Fireball(i, j, newM, newS, 2));
                        fireballList.add(new Fireball(i, j, newM, newS, 4));
                        fireballList.add(new Fireball(i, j, newM, newS, 6));
                    } else {
                        fireballList.add(new Fireball(i, j, newM, newS, 1));
                        fireballList.add(new Fireball(i, j, newM, newS, 3));
                        fireballList.add(new Fireball(i, j, newM, newS, 5));
                        fireballList.add(new Fireball(i, j, newM, newS, 7));
                    }
                }
                // 현재 위치의 파이어볼은 지워줌
                map[i][j].clear();
            }
        }

    }

}
