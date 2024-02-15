package programmers;

public class 점찍기 {

    public long solution(int k, int d) {
        long answer = 0;
        
        for (int i = 0; i <= d; i+=k) {
            long x = (long)i * i;
            int maxY = (int) Math.sqrt((long)d * d - x);

            answer += maxY / k + 1;
        }
        
        return answer;
    }
}
