package programmers;

import java.util.*;

class 순위검색 {

    Map<String, List<Integer>> map;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        map = new HashMap<>();
        StringTokenizer st;
        
        for (int i = 0; i < info.length; i++) {
            String[] currInfo = info[i].split(" ");

            dfs(currInfo, 0, "");
        }
    
        Set<String> keys = map.keySet();
        
        for (String key : keys) {
            Collections.sort(map.get(key));
        }
        
        for (int i = 0; i < query.length; i++) {
            String[] currQuery = query[i].split(" and ");
           
            String[] wordAndScore = currQuery[currQuery.length - 1].split(" ");
            currQuery[currQuery.length - 1] = wordAndScore[0];
            int score = Integer.parseInt(wordAndScore[1]);
            
            String searchQuery = currQuery[0] + currQuery[1] + currQuery[2] + currQuery[3];
            
            if (!map.containsKey(searchQuery)) {
                continue;
            }
            
            List<Integer> list = map.get(searchQuery);

            int count = binarySearch(list, score);
            answer[i] = list.size() - count;
        }
        

        
        return answer;
    }
    
    void dfs(String[] currInfo, int depth, String word) {
        
        if (depth == 4) {
            int score = Integer.parseInt(currInfo[currInfo.length - 1]);
            
            if (!map.containsKey(word)) {
                List<Integer> list = new ArrayList<>();
                list.add(score);
                map.put(word, list);
            } else {
                map.get(word).add(score);
            }
            
            return;
        }
        
        dfs(currInfo, depth + 1, word + "-");
        dfs(currInfo, depth + 1, word + currInfo[depth]); 
    }
    
    int binarySearch(List<Integer> list, int score) {
        
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (list.get(mid) >= score) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return right + 1;
    }
}
