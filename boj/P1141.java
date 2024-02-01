package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1141 {
	
	static int N;
	static String[] words;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		words = new String[N];
		
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		
		Arrays.sort(words, (o1, o2) -> {
			return o1.length() - o2.length();
		});
		
//		System.out.println(Arrays.toString(words));
		
		System.out.println(find());
		
		
	}
	
	static int find() {
		int result = N;
		
		for (int i = 0; i < N - 1; i++) {
			String word = words[i];
			
			for (int j = i + 1; j < N; j++) {
				
				if (word.equals(words[j].substring(0, word.length()))) {
					result--;
					break;
				}
			}

		}
		
		return result;
	}
}
