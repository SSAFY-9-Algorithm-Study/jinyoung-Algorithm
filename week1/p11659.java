package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        String[] strNum = bf.readLine().split(" ");
        int[] num = Arrays.stream(strNum).mapToInt(Integer::parseInt).toArray();
        // 누적된 합을 저장
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
            num[i] = sum;
        }

        for (int i = 0; i < M; i++) {
            String[] range = bf.readLine().split(" "); // 구간 입력
            int range1 = Integer.parseInt(range[0]) - 1;
            int range2 = Integer.parseInt(range[1]) - 1;
            if (range1 == 0) {
                System.out.println(num[range2]);
            } else {
                System.out.println(num[range2] - num[range1 - 1]);
            }
        }
    }
}
