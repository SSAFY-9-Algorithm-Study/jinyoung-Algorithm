package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p8982 {
	static class Aquarium{
		int y, x;
		public Aquarium(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static class Hole{
		int x1, y1, x2, y2;
		public Hole(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	static int N, K;
	static Aquarium[] aquariums;
	static Hole[] holes;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		aquariums = new Aquarium[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			aquariums[i] = new Aquarium(y, x);
		}
		K = Integer.parseInt(br.readLine());
		holes = new Hole[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			holes[i] = new Hole(x1, y1, x2, y2);
		}
		
		
	}
}
