package programmers;


public class 롤케이크자르기 {
    public int solution(int[] topping) {
        
        int N = topping.length;
        
        int[] types1 = new int[10001];
        int[] types2 = new int[10001];
        
        int[] arr1 = new int[topping.length];
        int[] arr2 = new int[topping.length];

        int index1 = 0;
        int index2 = 0;
        
        for (int i = 0; i < topping.length; i++) {
            
            if (types1[topping[i]] == 0) {
                arr1[i] = index1++;
            }
            
            if (types2[topping[N - i - 1]] == 0) {
                arr2[N - i - 1] = index2++;
            }
            arr1[i] = index1;
            types1[topping[i]]++;
            
            arr2[N - i - 1] = index2;
            types2[topping[N - i - 1]]++;
        }
        
        // System.out.println(Arrays.toString(arr1));
        // System.out.println(Arrays.toString(arr2));
        
        int answer = 0;
        for (int i = 0; i < topping.length - 1; i++) {
            
            if (arr1[i] == arr2[i + 1]) {
                answer++;
            }
        }

        return answer;
    }
}
