package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15961 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] sushis = new int[N];
		
		for (int i = 0; i < N; i++) {
			sushis[i] = Integer.parseInt(br.readLine());
		}
		
		int[] visited = new int[d + 1];
		
		int cnt = 0;
		for (int i = 0; i < k; i++) {
			if (visited[sushis[i]] == 0) {
				cnt++;
			}
			visited[sushis[i]]++;
		}
		
		int max = cnt;
		
		if (visited[c] == 0) {
			max++;
		}
		
		for (int i = 1; i < N; i++) {
			if (visited[sushis[(i + k - 1) % N]] == 0) {
				cnt++;
			}
			visited[sushis[(i + k - 1) % N]]++;
			
			visited[sushis[i - 1]]--;
			if (visited[sushis[i - 1]] == 0) {
				cnt--;
			}
			
			if (visited[c] == 0) {
				max = Math.max(max, cnt + 1);
			} else {
				max = Math.max(max, cnt);
			}
			
		}
		
		System.out.println(max);
	}
}
