package week7;

public class Solution {
    static int max;
    static int[] arr, lionScoreArr, result;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 });
    }

    public int[] solution(int n, int[] info) {
        arr = new int[n];
        lionScoreArr = new int[info.length];

        max = Integer.MIN_VALUE;
        result = new int[info.length];
        repeatedcombination(0, 0, n, info);

        if (max == Integer.MIN_VALUE)
            return new int[] { -1 };
        return result;
    }

    static void repeatedcombination(int depth, int start, int n, int[] info) {
        if (depth == n) {
            lionScoreArr = new int[info.length];
            calculateScore(info);
            return;
        }
        for (int i = start; i < 11; i++) {
            arr[depth] = i;
            repeatedcombination(depth + 1, i, n, info);
        }
    }

    static void calculateScore(int[] info) {
        for (int i = 0; i < arr.length; i++) {
            lionScoreArr[10 - arr[i]]++;
        }
        int lionScore = 0;
        int apeachScore = 0;
        for (int i = 0; i < info.length; i++) {
            // 라이언이 더 많이 맞혔을 경우 점수 가져감
            if (lionScoreArr[i] > info[i]) {
                lionScore += 10 - i;
            } else if (lionScoreArr[i] <= info[i] && info[i] != 0) {
                apeachScore += 10 - i;
            }
        }
        // 점수 차이
        int diff = lionScore - apeachScore;
        // 라이언이 우승한 경우
        if (diff > 0) {
            // 현재 점수차가 최대점수차라면 max, result 변경
            if (max < diff) {
                max = diff;
                result = lionScoreArr.clone();
            } else if (max == diff) { // 현재 점수차와 기존의 최대점수차가 같다면, 점수 배열 비교
                // 낮은 점수부터 비교
                for (int i = lionScoreArr.length - 1; i >= 0; i--) {
                    if (lionScoreArr[i] > result[i]) {
                        result = lionScoreArr.clone();
                        break;
                    } else if (lionScoreArr[i] < result[i]) {
                        break;
                    }
                }
            }
        }

    }
}
