package programmers;

import java.util.*;

public class 야근지수 {
	
    public long solution(int n, int[] works) {
    	
        long answer = 0;
        
        Arrays.sort(works);

        int idx = works.length - 1;

        while (n > 0 && idx >= 0 && works[idx] > 0) {
            
            if (idx == works.length - 1) {
                n--;
                works[idx]--;
                idx--;
                continue;
            }
            
            if (works[idx] <= works[idx + 1]) {
                idx++;
                continue;
            }
            
            n--;
            works[idx]--;
            idx--;
            
            if (n > 0 && idx < 0) {
                idx = works.length - 1;
            }
        }

        
        for (int work : works) {
            answer += work * work;
        }
        
        
        return answer;
    }
}
