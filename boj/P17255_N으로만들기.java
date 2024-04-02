package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class P17255_N으로만들기 {

	static String N;
	static Set<String> pathSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = br.readLine();

		pathSet = new HashSet<>();

		for (int i = 0; i < N.length(); i++) {
			dfs(i, i, "", N.charAt(i) + "");
		}

		System.out.println(pathSet.size());
	}

	static void dfs(int left, int right, String path, String word) {

		if (word.equals(N)) {
			pathSet.add(path + word);
			return;
		}
		
		if (left > 0) {
			dfs(left - 1, right, path + word + " ", N.charAt(left - 1) + word);
		}
		
		if (right < N.length() - 1) {
			dfs(left, right + 1, path + word + " ", word + N.charAt(right + 1));
		}
	}
}
