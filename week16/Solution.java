package week16;

class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] arr = new String[] { "1", "-", "3", "+", "5", "-", "8" };
		solution.solution(arr);
	}

	public int solution(String arr[]) {
		int answer = -1;
		
		int operands = arr.length / 2;
		int[][] maxDP = new int[operands][operands];
		int[][] minDP = new int[operands][operands];
		for (int i = 0; i < operands; i++) {
			for (int j = 0; j < operands; j++) {
				maxDP[i][j] = Integer.MIN_VALUE;
				minDP[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// 피연산자 자체가 최대값 
		for (int i = 0; i < operands; i++) {
			maxDP[i][i] = Integer.parseInt(arr[i * 2]);
			minDP[i][i] = Integer.parseInt(arr[i * 2]);
		}

		
		return answer;
	}
}
