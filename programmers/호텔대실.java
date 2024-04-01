package programmers;

import java.util.*;

public class 호텔대실 {

	final int LAST_TIME = 24 * 60 + 10;

	public int solution(String[][] book_time) {

		int[] checkedTime = new int[LAST_TIME];

		int answer = 0;

		for (int i = 0; i < book_time.length; i++) {
			int startTime = makeHourToMinute(book_time[i][0]);
			int endTime = makeHourToMinute(book_time[i][1]) + 10;
			checkedTime[startTime]++;
			checkedTime[endTime]--;
		}

		for (int i = 1; i < checkedTime.length; i++) {
			checkedTime[i] += checkedTime[i - 1];
			answer = Math.max(answer, checkedTime[i]);
		}

		return answer;
	}

	int makeHourToMinute(String time) {
		String[] spltTime = time.split(":");
		int minuteTime = Integer.parseInt(spltTime[0]) * 60 + Integer.parseInt(spltTime[1]);

		return minuteTime;
	}

}
