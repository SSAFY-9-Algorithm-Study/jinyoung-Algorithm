package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class p1461 {
	static int N, M;
	static ArrayList<Integer> plus;
	static ArrayList<Integer> minus;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		plus = new ArrayList<>();
		minus = new ArrayList<>();
		int max = 0;
		boolean checkMaxPlus = false; // max값이 plus인지 minus인지 체크 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int book = Integer.parseInt(st.nextToken());
			if (book < 0) {
				minus.add(book * -1);
			} else {
				plus.add(book);
			}
			
			max = Math.max(max, Math.abs(book));
		}
		Collections.sort(plus);
		Collections.sort(minus);
		Collections.reverse(plus);
		Collections.reverse(minus);
//		System.out.println(plus);
//		System.out.println(minus);
//		
		if (plus.size() > 0 && max == plus.get(0)) {
			checkMaxPlus = true;
		}
		int result = 0;
		
		// + 
		for (int i = 0 ; i < plus.size(); i+=M) {
			// 현재 인덱스가 0이고 최대값 
			if (i == 0 && checkMaxPlus) {
				result += plus.get(i);
			} else {
				result += plus.get(i) * 2;
			}
		}
		
		// - 
		for (int i = 0 ; i < minus.size(); i+=M) {
			// 현재 인덱스가 0이고 최대값 
			if (i == 0 && !checkMaxPlus) {
				result += minus.get(i);
			} else {
				result += minus.get(i) * 2;
			}
		}
		
		System.out.println(result);
	}
}
