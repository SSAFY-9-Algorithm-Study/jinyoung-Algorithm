package programmers;

import java.util.*;

public class 인사고과 {

	public int solution(int[][] scores) {
		int[] wanho = scores[0];

		Arrays.sort(scores, (o1, o2) -> {
			if (o1[0] == o2[0]) {
				return o1[1] - o2[1];
			}
			return o2[0] - o1[0];
		});

		int comparedScore = scores[0][1];
		for (int i = 1; i < scores.length; i++) {

			if (comparedScore > scores[i][1]) {
				if (wanho == scores[i]) {
					return -1;
				}
				scores[i][0] = -1;
				scores[i][1] = -1;
				continue;
			}
			// 현재 score의 동료 평가 점수가 더 클 경우 점수 갱신
			comparedScore = scores[i][1];
		}

		Arrays.sort(scores, (o1, o2) -> {
			return (o2[0] + o2[1]) - (o1[0] + o1[1]);
		});

		if (wanho == scores[0]) {
			return 1;
		}

		int answer = 0;
		int scoreSum = scores[0][0] + scores[0][1];

		for (int i = 1; i < scores.length; i++) {
			if (scoreSum > scores[i][0] + scores[i][1]) {
				answer = i;
			}

			if (wanho == scores[i]) {
				return answer + 1;
			}
		}

		return answer;
	}

}
