package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class p12605 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<String> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            String[] word = br.readLine().split(" ");
            for (int j = 0; j < word.length; j++) {
                stack.push(word[j]);
            }
            System.out.print("Case #" + i + ": ");
            for (int j = 0; j < word.length; j++) {
                System.out.print(stack.pop() + " ");
            }
            System.out.println();
        }
    }
}
