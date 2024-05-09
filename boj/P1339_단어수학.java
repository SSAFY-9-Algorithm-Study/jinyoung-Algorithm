package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1339_단어수학 {

	static int N;
	static String[] words;
	static int[] alphabet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		alphabet = new int['Z' - 'A' + 1];
		N = Integer.parseInt(br.readLine());
		words = new String[N];

		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}

		setAlphabetNum();

		System.out.println(sumWords());
	}

	private static int sumWords() {

		Arrays.sort(alphabet);
		
		int sum = 0;
		int number = 9;
		
		for (int i = alphabet.length - 1; i >= 0; i--) {
			if (alphabet[i] > 0) {
				sum += alphabet[i] * number--;
			}
		}

		return sum;
	}

	private static void setAlphabetNum() {

		for (int i = 0; i < N; i++) {

			for (int j = words[i].length(); j >= 1; j--) {
				int currIdx = words[i].length() - j;
				int currAlphabet = words[i].charAt(currIdx);

				alphabet[currAlphabet - 'A'] += (int) Math.pow(10, j - 1);
			}
		}

	}
}
