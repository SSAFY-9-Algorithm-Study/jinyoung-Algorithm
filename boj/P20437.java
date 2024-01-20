package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P20437 {
	
	static String W;
	static int K, min, max;
	static int[] strArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			if (K == 1) {
				System.out.println("1 1");
				continue;
			}
			
			countString();
			findString();
				
			if (min == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(min + " " + max);
			}
			
		}
	}
	
	static void countString() {
		strArr = new int['z' - 'a' + 1];
		
		for (int i = 0; i < W.length(); i++) {
			strArr[W.charAt(i) - 'a']++;
		}
	}
	
	static void findString() {
		
		for (int i = 0; i < W.length() - K; i++) {
			int currStr = W.charAt(i) - 'a';
			
			if (strArr[currStr] < K) continue;
			
			int cnt = 1;
			for (int j = i + 1; j < W.length(); j++) {
				int nextStr = W.charAt(j) - 'a';
				
				if (currStr == nextStr) {
					cnt++;
					
					if (cnt == K) {
						min = Math.min(min, j - i + 1);
						max = Math.max(max, j - i + 1);
						break;
					}
				}
			}
			
		}
	}
}
