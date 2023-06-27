package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class 플레이페어암호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputMessage = br.readLine().split("");
		String[] inputKey = br.readLine().split("");
		ArrayList<String> messageList = new ArrayList<>();
		for (int i = 0; i < inputMessage.length; i++) {
			messageList.add(inputMessage[i]);
		}

		String[][] encodingKey = encodeKey(inputKey);
		ArrayList<String> encodingMessage = encodeMessage(messageList);
//		System.out.println(encodingMessage);
		
		for (int m = 0; m < encodingMessage.size(); m+=2) {
			String msg1 = encodingMessage.get(m);
			String msg2 = encodingMessage.get(m + 1);
			int[] point1 = new int[2];
			int[] point2 = new int[2];
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (msg1.equals(encodingKey[i][j])) {
						point1[0] = i;
						point1[1] = j;
					}
					if (msg2.equals(encodingKey[i][j])) {
						point2[0] = i;
						point2[1] = j;
					}
				}
			}
			String text1 = "";
			String text2 = "";
			if (point1[0] == point2[0]) {
				if (point1[1] + 1 >= 5) {
					text1 = encodingKey[point1[0]][0];
				} else {
					text1 = encodingKey[point1[0]][point1[1] + 1];
				}
				
				if (point2[1] + 1 >= 5) {
					text2 = encodingKey[point2[0]][0];
				}else {
					text2 = encodingKey[point2[0]][point2[1] + 1];
				}
				
			} else if (point1[1] == point2[1]) {
				if (point1[0] + 1 >= 5) {
					text1 = encodingKey[0][point1[1]];
				} else {
					text1 = encodingKey[point1[0] + 1][point1[1]];
				}
				
				if (point2[0] + 1 >= 5) {
					text2 = encodingKey[0][point2[1]];
				}else {
					text2 = encodingKey[point2[0] + 1][point2[1]];
				}
			} else {
				text1 = encodingKey[point1[0]][point2[1]];
				text2 = encodingKey[point2[0]][point1[1]];
			}
//			System.out.println(encodingMessage);
//			System.out.println(text1 +" " + text2);
			encodingMessage.add(m, text1);
			encodingMessage.remove(m + 1);
			encodingMessage.add(m + 1, text2);
			encodingMessage.remove(m + 2);
			
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < encodingMessage.size(); i++) {
			sb.append(encodingMessage.get(i));
		}
//		System.out.println(encodingMessage);
		System.out.println(sb);

	}

	static String[][] encodeKey(String[] inputKey) {
		// 중복 제거
		LinkedHashSet<String> set = new LinkedHashSet<>();
		for (int i = 0; i < inputKey.length; i++) {
			set.add(inputKey[i]);
		}

		LinkedHashSet<String> setAlphabet = new LinkedHashSet<>();
		for (int i = 'A'; i <= 'Z'; i++) {
			if (i == 'J')
				continue;
			char a = (char) i;
			setAlphabet.add(Character.toString(a));
		}

		set.addAll(setAlphabet);
//		System.out.println(set);
		Object[] setArray = set.toArray();

		String[][] keyMap = new String[5][5];
		int idx = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				keyMap[i][j] = (String) setArray[idx++];
			}
		}
		return keyMap;
	}
	
	static ArrayList<String> encodeMessage(ArrayList<String> inputMessage) {
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i = 0; i < inputMessage.size() - 1; i+=2) {
				if (inputMessage.get(i).equals(inputMessage.get(i + 1))) {
					flag = true;
					if (inputMessage.get(i).equals("X")) {
						inputMessage.add(i + 1, "Q");
					} else {
						inputMessage.add(i + 1, "X");
					}
					
				}
			}
		}
		if (inputMessage.size() % 2 != 0) {
			inputMessage.add("X");
		}
		return inputMessage;
	}
}
