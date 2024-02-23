package programmers;

import java.util.*;

class 숫자카드나누기 {
    
    int answer = 0;
    
    public int solution(int[] arrayA, int[] arrayB) {
        
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        divideNumberCard(arrayA, arrayB);
        divideNumberCard(arrayB, arrayA);
        
        return answer;
    }
    
    public void divideNumberCard(int[] arrayA, int[] arrayB) {
        
        List<Integer> list = findDivisor(arrayA);
        list.remove(0);
        Collections.sort(list, Collections.reverseOrder());
        
        answer = Math.max(answer, findNotDivisor(list, arrayB));
    }
    
    public int findNotDivisor(List<Integer> list, int[] array) {
        
        int max = 0;
        
        for (int listNum : list) {
            boolean flag = true;
            
            for (int arrayNum : array) {
                
                if (arrayNum % listNum == 0) {
                    flag = false;
                    break;
                }
            }
            
            // 조건을 만족하는 경우 (현재 숫자가 상대의 카드를 나눌 수 없는 수)
            if (flag) {
                max = Math.max(max, listNum);
            }
            
        }
        
        return max;
    }
    
    // 배열의 최솟값의 약수를 찾는 함수
    public List<Integer> findDivisor(int[] arr) {
        
        List<Integer> list = new ArrayList<>();
        int min = arr[0];
        int sqrt = (int) Math.sqrt(min);
        
        for (int i = 1; i <= sqrt; i++) {
            
            if (min % i == 0) {
                
                if (isPossible(i, arr)) {
                    list.add(i);
                }
                
                if (i != min / i) {
                    
                    if (isPossible(min / i, arr)) {
                        list.add(min / i);
                    }
                    
                }
            }
            
        }
        
        return list;
    }
    
    // num이 배열의 모든 값의 약수인지 확인하는 함수
    public boolean isPossible(int num, int[] arr) {
        
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] % num != 0) {
                return false;
            }
        }
        
        return true;
    }
}
