package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class p7662 {
	static int t;
	static ArrayList<Integer> pqList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			t = Integer.parseInt(br.readLine());
			pqList = new ArrayList<>();
			for (int i = 0; i < t; i++) {
				st = new StringTokenizer(br.readLine());

				Collections.sort(pqList);

				if (st.nextToken().equals("I")) {
					pqList.add(Integer.parseInt(st.nextToken()));
				} else { // 삭제
					int number = Integer.parseInt(st.nextToken());
					if (pqList.size() > 0) {
						if (number == 1) { // 최대값 삭제
							pqList.remove(pqList.size() - 1);
						} else { // 최솟값 삭제
							pqList.remove(0);
						}
					}

				}
			}
			if (pqList.size() > 0) {
				System.out.println(pqList.get(pqList.size() - 1) + " " + pqList.get(0));
			} else {
				System.out.println("EMPTY");
			}
		}

	}

}
