package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2170_선긋기 {

	static int N;
	static int[][] lines;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		lines = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a <= b) {
				lines[i][0] = a;
				lines[i][1] = b;
			} else {
				lines[i][0] = b;
				lines[i][1] = a;
			}

		}

		Arrays.sort(lines, (o1, o2) -> {
			if (o1[0] == o2[0]) {
				return o2[1] - o1[1];
			}
			return o1[0] - o2[0];
		});

//		for (int[] i : lines) {
//			System.out.println(Arrays.toString(i));
//		}

		if (lines.length == 1) {
			System.out.println(lines[0][1] - lines[0][0]);
			return;
		}

		drawLines();
	}

	static void drawLines() {

		int start = lines[0][0];
		int end = lines[0][1];
		int result = 0;

		for (int i = 1; i < N; i++) {

			if (end >= lines[i][1]) {
				continue;
			}

			// end가 선의 끝보다 작을 때
			
			if (end >= lines[i][0]) {
				end = lines[i][1];
			} else {
				result += end - start;
				start = lines[i][0];
				end = lines[i][1];
			}

		}
		result += end - start;

		System.out.println(result);
	}
}
