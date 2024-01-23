package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class P2143 {
	
	static int T, n, m;
	static int[] arrN, arrM;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		n = Integer.parseInt(br.readLine());
		arrN = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arrN[i] = Integer.parseInt(st.nextToken());
		}
		
		m = Integer.parseInt(br.readLine());
		arrM = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			arrM[i] = Integer.parseInt(st.nextToken());
		}
		
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int sum = 0;
			
			for (int j = i; j < n; j++) {
				sum += arrN[j];
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}
		}
		
		int result = 0;
		for (int i = 0; i < m; i++) {
			int sum = 0;
			
			for (int j = i; j < m; j++) {
				sum += arrM[j];
				
				if (map.containsKey(T - sum)) {
					result += map.get(T - sum);
				}
			}
		}
		
		System.out.println(result);
		
	}
}
