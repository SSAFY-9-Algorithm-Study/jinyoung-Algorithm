package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p20955 {
	static int N, M, result;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if (!union(u, v)) {
				result++;
			}
		}
		
		System.out.println(result);
	}
	
	static boolean union(int start, int end) {
		int startRoot = find(start);
		int endRoot = find(end);
		if (startRoot != endRoot) {
			parents[endRoot] = startRoot;
			return true;
		}
		return false;
	}
	
	static int find(int v) {
		if (parents[v] == v) {
			return v;
		}
		return parents[v] = find(parents[v]);
	}
 }
