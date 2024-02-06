package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P17609 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			String word = br.readLine();
			System.out.println(checkPalindrome(word, 0, word.length() - 1, false));
		}
	}
	
	static int checkPalindrome(String word, int left, int right, boolean check) {
		
		while (left <= right) {
			
			if (word.charAt(left) != word.charAt(right)) {
				
				if (!check) {
					
					if (checkPalindrome(word, left + 1, right, true) == 0) {
						return 1;
					}
					
					if (checkPalindrome(word, left, right - 1, true) == 0) {
						return 1;
					}
					
					return 2;
				}
				
				if (check) {
					return 2;
				}
				
				
			} else {
				left += 1;
				right -= 1;
			}
		}
		
		return 0;
	}
}
