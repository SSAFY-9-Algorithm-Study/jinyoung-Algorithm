package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2470 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] solution = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			solution[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(solution);
		int start = 0;
		int end = N - 1;
		int min = Integer.MAX_VALUE;
		int[] result = new int[2];
		while (end > start) {
			int sum = Math.abs(solution[end] + solution[start]);
			if (sum < min) { // 합의 절대값이 작을 경우 정답 갱신
				min = sum;
				result[0] = solution[start];
				result[1] = solution[end];
			}
			// 두 수의 합이 양수일 경우 end를 왼쪽으로 옮겨서 값을 작게 만듦 
			if (solution[start] + solution[end] > 0) {
				end--;
			} else {
				start++;
			}
		}
		System.out.println(result[0] + " " + result[1]);
	}
}
