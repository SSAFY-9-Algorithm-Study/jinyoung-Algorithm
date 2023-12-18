package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p9205 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Point[] set;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			set = new Point[n + 2];
			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				set[i] = new Point(x, y);
			}

			parents = new int[n + 2];
			for (int i = 0; i < n + 2; i++) {
				parents[i] = i;
			}

			for (int i = 0; i < n + 1; i++) {
				for (int j = i + 1; j < n + 2; j++) {
					if (checkLength(set[i], set[j])) {
						union(i, j);
					}
				}
			}


			String result = "happy";
			// 시작점과 도착점을 비교 
			if (find(0) != find(n + 1)) {
				result = "sad";
			}

			System.out.println(result);

		}
	}

	static void union(int start, int end) {
		int startRoot = find(start);
		int endRoot = find(end);
		if (startRoot != endRoot) {
			if (startRoot < endRoot) {
				parents[startRoot] = endRoot;
			} else {
				parents[endRoot] = startRoot;
			}
		}
	}

	static int find(int v) {
		if (parents[v] == v) {
			return parents[v];
		}
		return parents[v] = find(parents[v]);
	}

	static boolean checkLength(Point a, Point b) {
		if (Math.abs(a.x - b.x) + Math.abs(a.y - b.y) > 1000) {
			return false;
		}
		return true;
	}
}
