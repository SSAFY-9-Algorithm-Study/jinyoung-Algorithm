package week11;

import java.util.Arrays;
import java.util.Comparator;

import week11.Solution;

public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] routes = new int [][]{{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
		int result = solution.solution(routes);
		System.out.println(result);
	}

	public int solution(int[][] routes) {
		Arrays.sort(routes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int answer = 1;
		// 첫번째 차량의 진출시점 
		int out = routes[0][1];
		
		for (int i = 1; i < routes.length; i++) {
			// 현재 차량의 진출 시점과 다음 차량의 진입 시점을 비교 
			if (out < routes[i][0]) {
				answer++;
				out = routes[i][1];
			} else {
				// 현재 차량의 진출 시점과 다음 차량의 진출 시점 중 작은 값을 저장 
				out = Math.min(out, routes[i][1]);
			}
		}
		
		return answer;
	}

}
