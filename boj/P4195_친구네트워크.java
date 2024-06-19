package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P4195_친구네트워크 {

	static String[][] inputs;
	static Map<String, String> parents;
	static Map<String, Integer> friendCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int F = Integer.parseInt(br.readLine());
			inputs = new String[F][2];
			parents = new HashMap<>();
			friendCnt = new HashMap<>();

			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());

				inputs[i][0] = st.nextToken();
				inputs[i][1] = st.nextToken();

				parents.put(inputs[i][0], inputs[i][0]);
				parents.put(inputs[i][1], inputs[i][1]);
				friendCnt.put(inputs[i][0], 1);
				friendCnt.put(inputs[i][1], 1);
			}

			for (int i = 0; i < inputs.length; i++) {
				Arrays.sort(inputs[i]);
			}

			for (int i = 0; i < F; i++) {

				String friend1 = inputs[i][0];
				String friend2 = inputs[i][1];

				System.out.println(union(friend1, friend2));
			}
		}
	}

	private static int union(String friend1, String friend2) {

		String start = findParents(friend1);
		String end = findParents(friend2);

		if (start != end) {
			parents.put(end, start);
			friendCnt.put(start, friendCnt.get(start) + friendCnt.get(end));
		}

		return friendCnt.get(start);
	}

	private static String findParents(String v) {

		if (parents.get(v).equals(v)) {
			return parents.get(v);
		}

		parents.put(v, findParents(parents.get(v)));
		return parents.get(v);
	}
}
