package week14;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

	public static void main(String[] args) {
		Solution soluton = new Solution();
		String[] commands = { "UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean",
				"UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant",
				"UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4",
				"UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4" };
		soluton.solution(commands);
	}

	public String[] solution(String[] commands) {
		String[] answer = {};
		ArrayList<String> answerList = new ArrayList<>();
		String[][] matrix = new String[51][51];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = "";
			}
		}
		
//		for (int i = 0; i < matrix.length; i++) {
//			System.out.println(Arrays.toString(matrix[i]));
//		}

		for (int C = 0; C < commands.length; C++) {
			String[] splitedStr = commands[C].split(" ");
			String command = splitedStr[0];

			if (command.equals("UPDATE")) {
				if (splitedStr.length >= 4) { // "UPDATE r c value"
					int r = Integer.parseInt(splitedStr[1]);
					int c = Integer.parseInt(splitedStr[2]);
					matrix[r][c] = splitedStr[3];
				} else { // "UPDATE value1 value2"
					String value1 = splitedStr[1];
					String value2 = splitedStr[2];
					for (int i = 0; i < matrix.length; i++) {
						for (int j = 0; j < matrix.length; j++) {
							if (matrix[i][j].equals(value1)) {
								matrix[i][j] = value2;
							}
						}
					}
				}
			} else if (command.equals("MERGE")) {
				int r1 = Integer.parseInt(splitedStr[1]);
				int c1 = Integer.parseInt(splitedStr[2]);
				int r2 = Integer.parseInt(splitedStr[3]);
				int c2 = Integer.parseInt(splitedStr[4]);

				if (!matrix[r1][c1].equals("") && !matrix[r2][c2].equals("")) {
					matrix[r2][c2] = matrix[r1][c1];
				} else if (!matrix[r1][c1].equals("")) {
					matrix[r2][c2] = matrix[r1][c1];
				} else if (!matrix[r2][c2].equals("")) {
					matrix[r1][c1] = matrix[r2][c2];
				}

			} else if (command.equals("UNMERGE")) {
				int r = Integer.parseInt(splitedStr[1]);
				int c = Integer.parseInt(splitedStr[2]);

			} else if (command.equals("PRINT")) {
				int r = Integer.parseInt(splitedStr[1]);
				int c = Integer.parseInt(splitedStr[2]);

				if (matrix[r][c].equals("")) {
					answerList.add("EMPTY");
				} else {
					answerList.add(matrix[r][c]);
				}

			}

		}

		return answer;
	}

}
