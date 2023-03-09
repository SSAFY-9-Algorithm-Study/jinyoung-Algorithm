package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class p15686 {
    static int N, M;
    static int[][] map;
    static ArrayList<int[]> homeList, chickenList;
    static int[][] selected;
    static ArrayList<Integer> resList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        homeList = new ArrayList<>();
        chickenList = new ArrayList<>();
        selected = new int[M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    homeList.add(new int[] { i, j });
                } else if (map[i][j] == 2) {
                    chickenList.add(new int[] { i, j });
                }
            }
        }
        resList = new ArrayList<>();
        dfs(0, 0);
        Collections.sort(resList);
        System.out.println(resList.get(0));
    }

    static void dfs(int depth, int start) {
        if (depth == M) {
            int result = 0;
            // 도시의 치킨 거리
            for (int i = 0; i < homeList.size(); i++) {
                int dist = 0;
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < selected.length; j++) {
                    int currHome[] = homeList.get(i);
                    int currChicken[] = selected[j];
                    dist = Math.abs(currHome[0] - currChicken[0]) + Math.abs(currHome[1] - currChicken[1]);
                    min = Math.min(min, dist);
                }
                result += min;
            }
            resList.add(result);
            return;
        }
        for (int i = start; i < chickenList.size(); i++) {
            selected[depth] = chickenList.get(i);
            dfs(depth + 1, i + 1);
        }
    }
}
