package programmers;

class 이진변환반복하기 {
	public int[] solution(String s) {
		int[] answer = new int[2];
		int length = s.length();

		while (length != 1) {
			answer[0]++;

			boolean flag = false;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '0') {
					length--;
					answer[1]++;
				}
			}

			s = makeBinary(length);
			length = s.length();
		}

		return answer;
	}

	String makeBinary(int length) {

		String number = "";
		while (length > 1) {
			number += length % 2;
			length /= 2;
		}

		number += length;
		return number;
	}
}
