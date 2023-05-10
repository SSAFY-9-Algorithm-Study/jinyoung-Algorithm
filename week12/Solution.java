package week12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] genres = new String[] { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = new int[] { 500, 600, 150, 800, 2500 };
		int[] result = solution.solution(genres, plays);

	}

	public int[] solution(String[] genres, int[] plays) {
		// 각 장르의 노래의 총 재생 횟수 저장
		HashMap<String, Integer> totalPlayMap = new HashMap<>();
		// 각 장르의 노래의 고유번호, 재생횟수 저장
		HashMap<String, HashMap<Integer, Integer>> NumandPlayMap = new HashMap<>();

		for (int i = 0; i < genres.length; i++) {
			totalPlayMap.put(genres[i], totalPlayMap.getOrDefault(genres[i], 0) + plays[i]);
			HashMap<Integer, Integer> map = new HashMap<>();
			map.put(i, plays[i]);
			if (NumandPlayMap.containsKey(genres[i])) {
				NumandPlayMap.get(genres[i]).put(i, plays[i]);
			} else {
				NumandPlayMap.put(genres[i], map);
			}
		}
		
		
		ArrayList<Integer> answerList = new ArrayList<>();
		
		ArrayList<String> keySet = new ArrayList<>(totalPlayMap.keySet());
		Collections.sort(keySet, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return totalPlayMap.get(o2) - totalPlayMap.get(o1);
			}
		});

		for (String key : keySet) {
			HashMap<Integer, Integer> map = NumandPlayMap.get(key);
			ArrayList<Integer> genreskeySet = new ArrayList<>(map.keySet());
			Collections.sort(genreskeySet, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return map.get(o2) - map.get(o1);
				}
			});
			answerList.add(genreskeySet.get(0));
			if (genreskeySet.size() > 1) {
				answerList.add(genreskeySet.get(1));
			}
			
		}
		int[] answer = new int[answerList.size()];
		for (int i = 0; i < answerList.size(); i++) {
			answer[i] = answerList.get(i);
		}
		return answer;
	}
}
