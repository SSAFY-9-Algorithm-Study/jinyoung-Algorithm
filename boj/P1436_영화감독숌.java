package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1436_영화감독숌 {
	
	static final String CHECKED_NUMBER = "666";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		int number = 665;
		while (cnt != N) {
			number++;
			
			if (String.valueOf(number).contains(CHECKED_NUMBER)) {
				cnt++;
			}
			
		}
		
		System.out.println(number);
	}
}
