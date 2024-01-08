package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P14426 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 1; j <= input.length(); j++) {
				set.add(input.substring(0, j));
			}
		}
		
		int result = 0;
		for (int i = 0; i < M; i++) {
			String input = br.readLine();
			if (set.contains(input)) {
				result++;
			}
		}
		
		System.out.println(result);
	}
}
