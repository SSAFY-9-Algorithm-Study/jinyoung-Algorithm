package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1477 {
	static int N, M, L;
	static int[] stores;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		stores = new int[N + 2];
		stores[0] = 0;
		stores[N + 1] = L;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			stores[i] = Integer.parseInt(st.nextToken());
		}
		
	}
}
