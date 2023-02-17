package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class p1874 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		Deque<Integer> deque = new LinkedList<>();
		boolean chk = true;
		int offerNum = 1;
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());

			while (offerNum <= num) {
				deque.offerFirst(offerNum);
				sb.append("+").append("\n");
				offerNum++;
			}

			if (num == deque.peek()) {
				sb.append("-").append("\n");
				deque.pollFirst();
			} else {
				chk = false;
				break;
			}
		}
		if (chk) {
			System.out.println(sb);
		} else {
			System.out.println("NO");
		}
	}
}