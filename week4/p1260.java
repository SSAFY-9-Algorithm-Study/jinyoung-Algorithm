package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1260 {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int m, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        graph = new ArrayList<ArrayList<Integer>>();
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);

        }
        for (int i = 0; i < graph.size(); i++) {
            Collections.sort(graph.get(i));
        }
        dfs(v);
        System.out.println();
        visited = new boolean[n + 1];
        bfs(v);

    }

    static void dfs(int v) {
        System.out.print(v + " ");
        visited[v] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph.get(v).size(); j++) {
                int nv = graph.get(v).get(j);
                if (!visited[nv]) {
                    dfs(nv);
                }
            }
        }
    }

    static void bfs(int v) {
        System.out.print(v + " ");
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        queue.offer(graph.get(v));
        visited[v] = true;
        while (!queue.isEmpty()) {
            ArrayList<Integer> curr = queue.poll();
            for (int i = 0; i < curr.size(); i++) {
                int nv = curr.get(i);
                if (!visited[nv]) {
                    queue.offer(graph.get(nv));
                    System.out.print(nv + " ");
                    visited[nv] = true;
                }
            }
        }
    }
}
