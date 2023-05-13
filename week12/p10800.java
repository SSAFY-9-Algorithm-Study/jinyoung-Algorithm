package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class p10800 {
	static class Ball {
		int num, color, size;

		public Ball(int num, int color, int size) {
			this.num = num;
			this.color = color;
			this.size = size;
		}
	}

	static int N;
	static ArrayList<Ball> balls;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		balls = new ArrayList<>();
		balls.add(new Ball(0, 0, 0));
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			balls.add(new Ball(i, C, S));
		}
		// 크기 순으로 정렬
		Collections.sort(balls, new Comparator<Ball>() {

			@Override
			public int compare(Ball o1, Ball o2) {
				if (o1.size == o2.size) {
					return o1.color - o2.color;
				}
				return o1.size - o2.size;
			}
		});

		int[] sumBySize = new int[N + 1]; // 크기순으로 누적합 저장 할 배열
		int[] sumByColor = new int[N + 1]; // 색상 별로 누적합 저장 할 배열
		int[] sumBySameSize = new int[2001]; // 크기가 같은 공의 누적합 저장 할 배열 
		int[] result = new int[N + 1]; // 결과

		// ball 중에서 크기가 가장 작은 값 넣어주기
		Ball firstBall = balls.get(1);
		sumBySize[1] = firstBall.size;
		sumByColor[firstBall.color] = firstBall.size;

		for (int i = 2; i <= N; i++) {
			Ball curr = balls.get(i);
			sumBySize[i] = sumBySize[i - 1] + curr.size;
			if (curr.size == balls.get(i - 1).size) {
				sumBySameSize[curr.size] += curr.size;
			}
			// 결과 : 현재까지 계산한 공 크기 누적합 - 같은 색의 공 크기 누적합  - 같은 크기의 공 누적합 
			result[curr.num] = sumBySize[i - 1] - sumByColor[curr.color] - sumBySameSize[curr.size];
			sumByColor[curr.color] += curr.size;
			
			int idx = i - 1;
			while (idx >= 0 && balls.get(idx).size == curr.size && balls.get(idx).color == curr.color) {
				result[curr.num] += curr.size;
				idx --;
			}
		}
//		System.out.println(Arrays.toString(sumBySize));
//		System.out.println(Arrays.toString(sumByColor));
//		System.out.println(Arrays.toString(sumBySameSize));
//		System.out.println(Arrays.toString(result));
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append("\n");
		}
		System.out.println(sb);
	}
}
