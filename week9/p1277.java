package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1277 {
	static int N, W;
	static double M;
	static ArrayList<int[]> station;
	static ArrayList<ArrayList<Integer>> linkedStation;
	static boolean[][] linked;
	static double[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		M = Double.parseDouble(br.readLine());
		station = new ArrayList<>();
		station.add(new int[] {});
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			station.add(new int[] { x, y });
		}
		linkedStation = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			linkedStation.add(new ArrayList<>());
		}
		linked = new boolean[N + 1][N + 1];
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			linkedStation.get(start).add(end);
			linked[start][end] = true;
			linked[end][start] = true;
		}
		linkStation();
//		System.out.println(Arrays.toString(dist));
		System.out.println((long) Math.floor(dist[N] * 1000));
	}

	static void linkStation() {
		boolean[] visited = new boolean[N + 1];
		dist = new double[N + 1];

		for (int i = 0; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		// 1번 발전소와 연결된 발전소의 dist값 0
		dist[1] = 0;
		for (int i = 0; i < N; i++) {
			if (linked[1][i]) {
				dist[i] = 0;
			}	
		}

		for (int i = 0; i < N; i++) {
			int currIdx = 0;
			double currValue = Double.MAX_VALUE;
			for (int j = 1; j <= N; j++) {
				if (!visited[j] && dist[j] <= currValue) {
					currIdx = j;
					currValue = dist[j];
				}
			}

			// 현재 발전소 방문 처리
			visited[currIdx] = true;

			for (int j = 1; j <= N; j++) {
				int nearN = j;
				double length = calculateLength(currIdx, nearN);
				if (length > M) {
					continue;
				}
				// 발전소의 거리 갱신
				if (dist[nearN] > dist[currIdx] + length) {
					dist[nearN] = dist[currIdx] + length;
				}
			}
		}
	}

// 현재 발전소에서 인접 발전소로 가는 전선의 길이
	static double calculateLength(int curr, int near) {

		if (linkedStation.get(curr).contains(near)) {
			return 0;
		} else if (linkedStation.get(near).contains(curr)) {
			return 0;
		}

		int[] currPoint = station.get(curr);
		int[] nearPoint = station.get(near);
		double length = Math.pow(currPoint[0] - nearPoint[0], 2) + Math.pow(currPoint[1] - nearPoint[1], 2);
		return Math.sqrt(length);

	}
}