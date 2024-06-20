package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11509_풍선맞추기 {
	
	static int N, result;
	static int[] balloons;
	static int[] arrH;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		balloons = new int[N];
		arrH = new int[1_000_002];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			balloons[i] = Integer.parseInt(st.nextToken());
		}
		
		solve();
		System.out.println(result);
	}

	private static void solve() {
		
		for (int i = 0; i < N; i++) {
			int curr = balloons[i];
			
			if (arrH[curr + 1] > 0) {
				arrH[curr + 1]--;
				arrH[curr]++;
			} else {
				arrH[curr]++;
				result++;
			}
		}
		
	}
}
