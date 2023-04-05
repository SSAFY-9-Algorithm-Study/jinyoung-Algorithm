package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p19238 {
    static class Taxi {
        int x, y, dist;

        Taxi(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static class Customer {
        int leaveX, leaveY, arriveX, arriveY;

        Customer(int leaveX, int leaveY, int arriveX, int arriveY) {
            this.leaveX = leaveX;
            this.leaveY = leaveY;
            this.arriveX = arriveX;
            this.arriveY = arriveY;
        }
    }

    static int N, M, oil, startX, startY;
    static int[][] map;
    static ArrayList<Customer> customerList;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        oil = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    map[i][j] = -1;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken()) - 1;
        startY = Integer.parseInt(st.nextToken()) - 1;
        customerList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int leaveX = Integer.parseInt(st.nextToken()) - 1;
            int leaveY = Integer.parseInt(st.nextToken()) - 1;
            int arriveX = Integer.parseInt(st.nextToken()) - 1;
            int arriveY = Integer.parseInt(st.nextToken()) - 1;
            customerList.add(new Customer(leaveX, leaveY, arriveX, arriveY));
            // 승객의 출발지에 승객 번호를 담아줌
            map[leaveX][leaveY] = i + 1;

        }
        int cnt = 0;
        int result = -1;
        while (true) {
            int[] customerAndDist = bfs(startX, startY);
            driveTaxi(customerAndDist);
            // 연료가 없거나, 최단거리가 갱신되지 않으면 break;
            if (oil < 0 || customerAndDist[1] == Integer.MAX_VALUE) {
                break;
            }
            cnt++;
            // 승객의 수만큼 모두 수행되면 return
            if (cnt == M) {
                result = oil;
                break;
            }
        }
        System.out.println(result);
    }

    static int[] bfs(int x, int y) {
        // 시작지점에 승객이 있을 경우 바로 return
        if (map[x][y] > 0) {
            return new int[] { map[x][y] - 1, 0 };
        }
        boolean[][] visited = new boolean[N][N];
        int minDist = Integer.MAX_VALUE;
        int nearCustomer = 0;
        Queue<Taxi> queue = new LinkedList<>();
        queue.offer(new Taxi(x, y, 0));
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            Taxi taxi = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = taxi.x + dx[i];
                int ny = taxi.y + dy[i];
                int dist = taxi.dist;

                if (nx >= N || nx < 0 || ny >= N || ny < 0)
                    continue;
                if (map[nx][ny] == -1 || visited[nx][ny])
                    continue;
                if (map[nx][ny] > 0) { // 승객
                    if (dist + 1 < minDist) {
                        minDist = dist + 1;
                        nearCustomer = map[nx][ny] - 1;
                        // 최단거리가 짧은 승객이 여러명이면, 행 비교
                    } else if (dist + 1 == minDist) {
                        // 행, 열 비교
                        Customer c = customerList.get(nearCustomer);
                        if (nx < c.leaveX) {
                            nearCustomer = map[nx][ny] - 1;
                        } else if (nx == c.leaveX) {
                            if (ny < c.leaveY) {
                                nearCustomer = map[nx][ny] - 1;
                            }
                        }
                    }
                }

                // 승객X
                queue.offer(new Taxi(nx, ny, dist + 1));
                visited[nx][ny] = true;
            }
        }
        // 가장 가까운 승객과 거리를 return
        return new int[] { nearCustomer, minDist };
    }

    static void driveTaxi(int[] customerAndDist) {
        int customerNum = customerAndDist[0];
        int dist = customerAndDist[1];
        // 승객의 출발지 ~ 목적지 까지의 거리
        int minDist = getDistance(customerNum);
        // 연료 -= (승객에게 가는 거리 + 승객의 출발지부터 도착지까지의 거리)
        oil -= (dist + minDist);
        // 연료가 없으면 return
        if (oil < 0) {
            return;
        }
        oil += (minDist * 2);
        // 승객 초기화
        Customer c = customerList.get(customerNum);
        map[c.leaveX][c.leaveY] = 0;
        // 택시 위치
        startX = c.arriveX;
        startY = c.arriveY;
    }

    // customer의 출발지부터 도착지까지의 최소 거리 구하기
    static int getDistance(int customerNum) {
        Customer c = customerList.get(customerNum);
        boolean[][] visited = new boolean[N][N];
        int minDist = Integer.MAX_VALUE;
        Queue<Taxi> queue = new LinkedList<>();
        queue.offer(new Taxi(c.leaveX, c.leaveY, 0));
        visited[c.leaveX][c.leaveY] = true;
        while (!queue.isEmpty()) {
            Taxi taxi = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = taxi.x + dx[i];
                int ny = taxi.y + dy[i];

                if (nx >= N || nx < 0 || ny >= N || ny < 0)
                    continue;
                if (map[nx][ny] == -1 || visited[nx][ny])
                    continue;
                if (nx == c.arriveX && ny == c.arriveY) {
                    if (taxi.dist + 1 < minDist) {
                        minDist = taxi.dist + 1;
                    }
                } else {
                    queue.offer(new Taxi(nx, ny, taxi.dist + 1));
                    visited[nx][ny] = true;
                }

            }
        }
        return minDist;
    }
}