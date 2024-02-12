package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class P1411 {
	
	static int N;
	static String[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new String[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		
		System.out.println(findSimilarWord());
		
		
	}
	
	static int findSimilarWord() {
		Map<Character, Character> map = new HashMap<>();
		int result = 0;
		
		for (int i = 0; i < N - 1; i++) {
			String curr = arr[i];
			
			
			for (int j = i + 1; j < N; j++) {
				map.clear();
				String next = arr[j];
			
				boolean flag = true;
				for (int k = 0; k < curr.length(); k++) {
					
					if (map.containsKey(curr.charAt(k))) {
						
						if (map.get(curr.charAt(k)) != next.charAt(k)) {
							
							flag = false;
						}
					} else {
						
						if (map.containsValue(next.charAt(k) )) {
							
							flag = false;
						}
						map.put(curr.charAt(k), next.charAt(k));
					}
					
				}
				
				if (flag) {
					result++;
				}
			}
		}
		
		return result;
	}
}
