package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P12904_A와B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();

		System.out.println(checkWord(S, T));
	}

	private static int checkWord(String S, String T) {
		
		
		while (S.length() != T.length()) {
			
			// T의 마지막 문자가 B인지 A인지 확인하기 
			if (T.charAt(T.length() - 1) == 'B') {
				T = T.substring(0, T.length() - 1);
				T = reverseWord(T);
			} else {
				T = T.substring(0, T.length() - 1);
			}
		}
		
		if (S.equals(T)) {
			return 1;
		}
		return 0;
	}

	private static String reverseWord(String T) {
		
		String newT = "";
		
		for (int i = T.length() - 1; i >= 0; i--) {
			newT += T.charAt(i);
		}
		
		return newT;
	}
}
