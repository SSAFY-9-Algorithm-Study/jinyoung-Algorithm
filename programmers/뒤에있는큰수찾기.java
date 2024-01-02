package programmers;

import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                // stack의 인덱스에 해당하는 뒷 큰수를 찾은 경우
                answer[stack.pop()] = numbers[i];
            }
            
            stack.push(i);
        }
        
        // stack에 남아있는 요소는 뒷 큰수가 없음
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        
        return answer;
    }
}
