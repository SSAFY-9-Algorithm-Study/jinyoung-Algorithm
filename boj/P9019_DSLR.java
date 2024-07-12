package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P9019_DSLR {

	static class Word {
		String word, command;

		Word(String word, String command) {
			this.word = word;
			this.command = command;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			String A = st.nextToken();
			String B = st.nextToken();

			System.out.println(bfs(A, B));
		}
	}

	private static String bfs(String A, String B) {

		Queue<Word> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[10_000];
		queue.offer(new Word(A, ""));

		while (!queue.isEmpty()) {
			Word curr = queue.poll();

			if (curr.word.equals(B)) {

				return curr.command;
			}

			int currWord = Integer.parseInt(curr.word);

			// command D
			int nextWord = currWord * 2 % 10_000;

			if (!visited[nextWord]) {
				queue.offer(new Word(String.valueOf(nextWord), curr.command + "D"));
				visited[nextWord] = true;
			}

			// command S
			nextWord = (currWord + 9999) % 10_000;

			if (!visited[nextWord]) {
				queue.offer(new Word(String.valueOf(nextWord), curr.command + "S"));
				visited[nextWord] = true;
			}

			// command L
			nextWord = calcCommandL(curr.word);

			if (!visited[nextWord]) {
				queue.offer(new Word(String.valueOf(nextWord), curr.command + "L"));
				visited[nextWord] = true;
			}

			// command R
			nextWord = calcCommandR(curr.word);

			if (!visited[nextWord]) {
				queue.offer(new Word(String.valueOf(nextWord), curr.command + "R"));
				visited[nextWord] = true;
			}
		}

		return "";
	}

	private static int calcCommandR(String word) {

		if (word.length() < 4) {
			int cnt = 4 - word.length();
			String newWord = "";

			for (int i = 0; i < cnt; i++) {
				newWord += "0";
			}
			word = newWord + word;
		}

		word = word.charAt(word.length() - 1) + word.substring(0, word.length() - 1);

		return Integer.parseInt(word);
	}

	private static int calcCommandL(String word) {

		if (word.length() < 4) {
			int cnt = 4 - word.length();
			String newWord = "";

			for (int i = 0; i < cnt; i++) {
				newWord += "0";
			}
			word = newWord + word;
		}

		word = word.substring(1, word.length()) + word.charAt(0);

		return Integer.parseInt(word);
	}

}
