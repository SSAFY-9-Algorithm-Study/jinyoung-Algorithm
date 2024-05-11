package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2023 {
	
	static int N, min, max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		backTracking(0, "");

	}
	
	static void backTracking(int cnt, String number) {
		
		if (cnt == N) {
			System.out.println(number);
			return;
		}
		
		String nextNumber = number;
		for (int i = 1; i <= 9; i++) {
			nextNumber += i;
			
			if (isPrimeNumber(nextNumber)) {
				backTracking(cnt + 1, nextNumber);
			}
			nextNumber = number;
		}
	}
	
	
	static boolean isPrimeNumber(String num) {
		String curr = "";
		
		if (Integer.parseInt(num) < 2) return false;
		
		for (int i = 0; i < num.length(); i++) {
			curr += num.charAt(i);
			int currNum = Integer.parseInt(curr);
			
			for (int j = 2; j <= Math.sqrt(currNum); j++) {
				
				if (currNum % j == 0) {
					return false;
				}
			}
			
		}
		
		return true;
	}
}
