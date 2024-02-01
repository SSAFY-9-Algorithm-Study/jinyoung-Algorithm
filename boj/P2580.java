package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P2580 {
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static final int SIZE = 9;
	static int[][] sudoku;
	static List<Point> blankList;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		sudoku = new int[SIZE][SIZE];
		blankList = new ArrayList<>();
		
		for (int i = 0; i < SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < SIZE; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
				
				if (sudoku[i][j] == 0) {
					blankList.add(new Point(i, j));
				}
			}
		}

		check = new boolean[10];
		backTracking(0);
	}
	
	static void backTracking(int count) {
		
		if (blankList.size() == count) {
			
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					System.out.print(sudoku[i][j] + " ");
				}
				System.out.println();
			}
			
			System.exit(0);
		}

		Point point = blankList.get(count);
		
		for (int i = 1; i <= SIZE; i++) {
			sudoku[point.x][point.y] = i;
			
			if (isPossible(point.x, point.y)) {
				backTracking(count + 1);
			}
			sudoku[point.x][point.y] = 0;
		}
		
	}
	
	static boolean isPossible(int x, int y) {
		Arrays.fill(check, false);
		
		// x축
		for (int i = 0; i < SIZE; i++) {
			
			if (sudoku[i][y] == 0) continue;
			
			if (check[sudoku[i][y]]) {
				return false;
			}
			check[sudoku[i][y]] = true;
		}
		Arrays.fill(check, false);
		
		// y축
		for (int i = 0; i < SIZE; i++) {
			
			if (sudoku[x][i] == 0) continue;
			
			if (check[sudoku[x][i]]) {
				return false;
			}
			check[sudoku[x][i]] = true;
		}
		Arrays.fill(check, false);
		
		// 네모
		int startX = x / 3 * 3;
		int startY = y / 3 * 3;
		
		for (int i = startX; i < startX + 3; i++) {
			for (int j = startY; j < startY + 3; j++) {
				
				if (sudoku[i][j] == 0) continue;
				
				if (check[sudoku[i][j]]) {
					return false;
				}
				check[sudoku[i][j]] = true;
			}
		}
		
		return true;
	}
}
