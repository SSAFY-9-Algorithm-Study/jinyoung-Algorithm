package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1174_줄어드는수 {
	
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		findDescNum(1, 0);
	}
	
	static void findDescNum(int number, int result) {
		if (number == N) {
			
			return;
		}
		
		
	}
}
