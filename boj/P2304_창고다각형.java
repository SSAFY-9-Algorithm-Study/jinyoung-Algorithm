package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2304_창고다각형 {

	static int N;
	static int[][] containers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		containers = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			containers[i][0] = Integer.parseInt(st.nextToken()); // L
			containers[i][1] = Integer.parseInt(st.nextToken()); // H
		}

		Arrays.sort(containers, (o1, o2) -> {
			return o1[0] - o2[0];
		});

		System.out.println(calcArea());
	}

	private static int calcArea() {

		int maxLength = containers[containers.length - 1][0];
		int[] containerArr = new int[maxLength + 1];
		int[] leftArr = new int[maxLength + 1];
		int[] rightArr = new int[maxLength + 1];

		// 좌표 상에 컨테이너 배치
		for (int i = 0; i < N; i++) {
			int L = containers[i][0];
			int H = containers[i][1];
			containerArr[L] = H;
		}
		// 마지막 기둥 위치 채우기
		containerArr[maxLength] = containers[containers.length - 1][1];

		int leftMax = 0; // 배열의 왼쪽부터 최대값
		int rightMax = 0; // 배열의 오른쪽부터 최대값
		for (int i = 0; i <= maxLength; i++) {
			leftMax = Math.max(leftMax, containerArr[i]);
			leftArr[i] = leftMax;

			rightMax = Math.max(rightMax, containerArr[maxLength - i]);
			rightArr[maxLength - i] = rightMax;
		}

		// 면적 더하기
		int sum = 0;
		for (int i = 0; i <= maxLength; i++) {
			sum += Math.min(leftArr[i], rightArr[i]);
		}

		return sum;
	}
}
