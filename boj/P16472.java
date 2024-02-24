package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P16472 {
	
	static int N;
	static String str;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		
		System.out.println(translate());
	}
	
	static int translate() {
		int[] alphabet = new int['z' - 'a' + 1];
		int left = 0;
		int right = 0;
		int max = 0;
		
		alphabet[str.charAt(0) - 'a']++;
		
		while (right < str.length()) {
			int sum = checkStr(alphabet);
			
			if (sum <= N) {
				right++;
				max = Math.max(max, Arrays.stream(alphabet).sum());
				
				if (right < str.length()) {
					alphabet[str.charAt(right) - 'a']++;
				}
				
			} else {
				alphabet[str.charAt(left) - 'a']--;
				left++;
			}
		}
		
		return max;
	}
	
	static int checkStr(int[] arr) {
		int sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] > 0) {
				sum++;
			}
		}
		
		return sum;
	}
}
