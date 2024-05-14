package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P16953_AB {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int originalNum = Integer.parseInt(st.nextToken());
		int resultNum = Integer.parseInt(st.nextToken());

		int result = 1;
		
		while (originalNum < resultNum) {

			String tempResult = resultNum + "";

			if (resultNum % 2 == 0) {
				resultNum = resultNum / 2;
			} else if (tempResult.charAt(tempResult.length() - 1) == '1') {
				tempResult = tempResult.substring(0, tempResult.length() - 1);
				resultNum = Integer.parseInt(tempResult);
			} else {
				break;
			}
			
			result++;
		}

		if (originalNum == resultNum) {
			System.out.println(result);
		} else {
			System.out.println(-1);
		}
	}
}
