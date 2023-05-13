package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p1595 {
    static class Node {
        int end, dist;

        public Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }

    static ArrayList<ArrayList<Node>> tree;
    static boolean[] visited;
    static int max, leaf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        tree = new ArrayList<>();
        for (int i = 0; i <= 10000; i++) {
            tree.add(new ArrayList<>());
        }

        String line = "";
        while ((line = br.readLine()) != null && !line.equals("")) {
            st = new StringTokenizer(line);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            tree.get(start).add(new Node(end, dist));
            tree.get(end).add(new Node(start, dist));
        }

        visited = new boolean[10001];
        max = Integer.MIN_VALUE;
        visited[1] = true;
        dfs(1, 0);

        // 가장 거리가 긴 노드를 찾은 후 다시 dfs 돌리기 
        visited = new boolean[10001];
        max = Integer.MIN_VALUE;
        visited[leaf] = true;
        dfs(leaf, 0);

        System.out.println(max);
    }

    static void dfs(int start, int len) {
        if (len > max) {
            max = len;
            leaf = start;
        }

        for (int i = 0; i < tree.get(start).size(); i++) {
            Node next = tree.get(start).get(i);
            if (visited[next.end]) {
                continue;
            }
            visited[next.end] = true;
            dfs(next.end, len + next.dist);
        }
    }
}