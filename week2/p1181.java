package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class p1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashSet<String> setWords = new HashSet<>();
        for (int i = 0; i < n; i++) {
            setWords.add(br.readLine());
        }
        // Set -> Array
        String[] words = new String[setWords.size()];
        setWords.toArray(words);

        Arrays.sort(words, (word1, word2) -> {
            if (word1.length() == word2.length()) {
                return word1.compareTo(word2);
            } else {
                return word1.length() - word2.length();
            }
        });

        for (String word : words) {
            System.out.println(word);
        }
    }
}
