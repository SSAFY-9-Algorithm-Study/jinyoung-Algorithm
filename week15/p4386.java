package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class p4386 {
	static class Star implements Comparable<Star>{
		int start, end;
		double cost;

		Star(int start, int end, double cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Star o) {
			return Double.compare(this.cost, o.cost);
//			return (int) (this.cost - o.cost);
		}
	}

	static class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static Point[] points;
	static ArrayList<Star> stars;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		points = new Point[n];
		parents = new int[n + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			points[i] = new Point(x, y);
		}
		
		for (int i = 0; i <= n; i++) {
			parents[i] = i;
		}
		
		stars = new ArrayList<>();
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (i == j) {
					continue;
				}
				int start = i;
				int end = j;
				double cost = Math.sqrt(Math.pow(Math.abs(points[start].x - points[end].x), 2) + Math.pow(Math.abs(points[start].y - points[end].y), 2));
				stars.add(new Star(start, end, cost));
			}
		}
		
		Collections.sort(stars);
		
		double cost = 0.0;
		for (Star star : stars) {
//			System.out.println(star.cost);
			if (union(star.start, star.end)) {
				cost += star.cost;
			}
		}
		System.out.println(Math.round(cost * 100) / 100.0);
	}
	
	static boolean union(int start, int end) {
		int startRoot = find(start);
		int endRoot = find(end);
		if (startRoot != endRoot) {
			parents[startRoot] = endRoot;
			return true;
		}
		return false;
	}
	
	static int find(int v) {
		if (parents[v] == v) {
			return parents[v];
		}
		return parents[v] = find(parents[v]);
	}
}
