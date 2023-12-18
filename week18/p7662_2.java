package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class p7662_2 {
	static int t;
	static ArrayList<Integer> pqList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			t = Integer.parseInt(br.readLine());
			pqList = new ArrayList<>();
			TreeMap<Integer, Integer> map = new TreeMap<>();
			for (int i = 0; i < t; i++) {
				st = new StringTokenizer(br.readLine());

				if (st.nextToken().equals("I")) {
					int number = Integer.parseInt(st.nextToken());
					map.put(number, map.getOrDefault(number, 0) + 1);
				} else { // 삭제
					int number = Integer.parseInt(st.nextToken());
//					System.out.println(map.firstKey() + " " + map.lastKey());
					if (map.size() > 0) {
						if (number == 1) { // 최대값 삭제
							if(map.get(map.lastKey()) > 1) {
//								System.out.println(map.get(map.lastKey()));
								map.put(map.lastKey(), map.get(map.lastKey()) - 1);
							} else {
								map.remove(map.lastKey());
							}
						} else { // 최솟값 삭제
							if(map.get(map.firstKey()) > 1) {
								map.put(map.firstKey(), map.get(map.firstKey()) - 1);
							} else {
								map.remove(map.firstKey());
							}
						}
					}

				}
			}
			if (map.size() > 0) {
				System.out.println(map.lastKey() + " " + map.firstKey());
			} else {
				System.out.println("EMPTY");
			}
		}

	}

}
