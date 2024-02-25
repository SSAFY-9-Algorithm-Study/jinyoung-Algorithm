package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1593 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int g = Integer.parseInt(st.nextToken());
		int lengthS = Integer.parseInt(st.nextToken());
		String W = br.readLine();
		String S = br.readLine();
		
		int[] wArr = new int['z' - 'A' + 1];
		for (int i = 0; i < g; i++) {
			wArr[W.charAt(i) - 'A']++;
		}
		
		int[] currArr = new int['z' - 'A' + 1];
		int result = 0;
		for (int i = 0; i < g; i++) {
			currArr[S.charAt(i) - 'A']++;
		}
		
		if (Arrays.equals(wArr, currArr)) result++;
		
		for (int i = 0; i < lengthS - g; i++) {
			currArr[S.charAt(i) - 'A']--;
			currArr[S.charAt(i + g) - 'A']++;
			
			if (Arrays.equals(wArr, currArr)) result++;
		}
		
		System.out.println(result);
		
	}
}
